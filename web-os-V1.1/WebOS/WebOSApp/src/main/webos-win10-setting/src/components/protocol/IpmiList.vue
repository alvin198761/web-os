<template>
  <div :id="getId">
    <el-row v-if="mode == 'list'" :gutter="10" v-loading="loading" element-loading-text="拼命加载中"
            style="overflow-y: auto ; margin: 5px 5px 5px 0px">
      <el-col :span="4" v-for="ipmi in ipmis" :key="ipmi.id" style="margin-bottom: 10px ;height: 175px">
        <el-card shadow="hover" style="text-align: center ;cursor: pointer;height: 175px">
          <img src="../../assets/os/ipmi.jpg" width="100%"/>
          <el-button-group>
            <el-button size="mini" type="danger" icon="fa fa-power-off" title="关机"
                       @click="doPowerOff(ipmi)"></el-button>
            <el-button size="mini" type="success" icon="fa fa-play-circle" title="开机"
                       @click="doPowerOn(ipmi)"></el-button>
            <el-button size="mini" type="warning" icon="fa fa-spinner" title="重启" @click="doReboot(ipmi)"></el-button>
            <el-dropdown>
              <el-button size="mini" type="primary" icon="fa fa-ellipsis-h" title="更多操作"></el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <div @click="doQueryStatus(ipmi)">刷新状态</div>
                </el-dropdown-item>
                <el-dropdown-item>
                  <div @click="doDelete(ipmi)">删除</div>
                </el-dropdown-item>
                <el-dropdown-item>
                  <div @click="doEdit(ipmi)">编辑</div>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-button-group>
        </el-card>
      </el-col>
      <el-col :span="4" style="height: 175px">
        <el-card shadow="hover" style="text-align: center ;cursor: pointer;height: 175px" title="添加Ipmi连接">
          <i class="fa fa-plus" style="font-size: 145px ;color: #7D7D7D" @click="doAdd"/>
        </el-card>
      </el-col>
    </el-row>
    <div v-if="mode=='edit'" style="width: 90% ;margin-top: 40px">
      <el-form :model="form" ref="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col>
            <el-form-item label="ip地址" prop="ip">
              <el-input placeholder="请输入ip地址" v-model="form.ip"></el-input>
            </el-form-item>
            <el-form-item label="端口" prop="port">
              <el-input-number placeholder="请输入端口" v-model="form.port"></el-input-number>
            </el-form-item>
            <el-form-item label="用户名" prop="user">
              <el-input placeholder="请输入用户名" v-model="form.user"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input placeholder="请输入密码" v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="状态" prop="status">
              <el-switch
                v-model="form.status"
                active-color="#13ce66"
                inactive-color="#ff4949"
                :active-value="1"
                :inactive-value="0"
              >
              </el-switch>
            </el-form-item>
            <el-form-item style="text-align: right">
              <el-button @click="()=>mode='list'">取消</el-button>
              <el-button type="primary" @click="save()">确定</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </div>
</template>
<script>
  import {getServer, impiLogin, getCurrentPowerState, setPowerAction} from '../../client/IpmiClient';
  import {queryList, saveOrUpdate, del} from '../../service/ProtocolService';
  export default{
    props: ["id"],
    data: function () {
      return {
        loading: false,
        ipmis: [],
        mode: 'list',
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        rules: {
          user: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
          ],
          ip: [
            {required: true, message: '请输入ip地址', trigger: 'blur'},
          ],
          port: [
            {required: true, message: '请输入端口', trigger: 'blur'},
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
          ],
        }
      }
    },
    computed: {
      getId(){
        return 'ipmi' + this.id
      }
    },
    created: function () {
      this.refresh();
    },
    methods: {
      refresh(){
        this.loading = true;
        const _this = this;
        queryList({type: 4}).then(function (res) {
          _this.ipmis = res.data;
          _this.loading = false;
        }).catch(function (res) {
          _this.ipmis = [];
          _this.loading = false;
        })
      },
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          saveOrUpdate(that.dialogMode,that.form).then(res => {
            that.show = false;
            that.$message.success(that.title + "成功!");
            that.refresh();
            that.mode = 'list';
          }).catch(res => {
            that.$message.error(that.title + "出错!" + res);
          });
        });
      },
      initForm() {//初始数据
        return {
          domain: null,
          user: null,
          ip: null,
          port: null,
          password: null,
          author: null,
          create_time: null,
          status: 1,
          type: 4,
          name: ''
        }
      },
      doEdit(item){
        this.mode = 'edit';
        this.dialogMode = 'update';
        this.form = {...item};
      },
      doAdd(){
        this.mode = 'edit';
        this.form = this.initForm();
        this.dialogMode = 'save';
      },
      doDelete(item){
        const that = this;
        this.$confirm('此操作将删除一个连接, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }).then(() => {
          that.deleteItem(item);
        }).catch(() => {

        });
      },
      deleteItem(item){
        const that = this;
        del([item.id]).then(res => {
          that.$message({
            type: 'success',
            message: '删除成功!'
          });
          that.refresh()
        }).catch(err => {
          that.$message({
            type: 'error',
            message: '删除出错!'
          });
        })
      },
      //关机
      doPowerOff(item){
        this.doSetStatus(item, 0);
      },
      doPowerOn(item){
        this.doSetStatus(item, 1);
      },
      doReboot(item){
        this.doSetStatus(item, 3);
      },
      doQueryStatus(item){
        var impi = getServer(item.ip, item.port, item.user, item.password);
        if (!impi) {
          this.$message({
            type: "error",
            message: "连接失败"
          });
        }
        impiLogin(impi, function (err) {
          if (err) {
            this.$message({
              type: "error",
              message: err
            });
            return;
          }
          getCurrentPowerState(impi, action, function (err) {
            if (err) {
              this.$message({
                type: "error",
                message: err
              });
              return;
            }
          })
        })
      },
      doSetStatus(item, action){
        var impi = getServer(item.ip, item.port, item.user, item.password);
        if (!impi) {
          this.$message({
            type: "error",
            message: "连接失败"
          });
        }
        impiLogin(impi, function (err) {
          if (err) {
            this.$message({
              type: "error",
              message: err
            });
            return;
          }
          setPowerAction(impi, action, function (err) {
            if (err) {
              this.$message({
                type: "error",
                message: err
              });
              return;
            }
          })
        })
      }
    },
    components: {}
  }
</script>
<style>

</style>
