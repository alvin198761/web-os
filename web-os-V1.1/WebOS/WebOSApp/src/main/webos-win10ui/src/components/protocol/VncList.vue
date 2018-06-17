<template>
  <div :id="getId">
    <el-row v-if="mode=='list'" :gutter="10" v-loading="loading" element-loading-text="拼命加载中"
            style="overflow-y: auto ; margin: 5px 5px 5px 0px">
      <el-col :span="4" v-for="(vnc,index) in vncs" :key='vnc.id' style="margin-bottom: 10px;height: 175px">
        <el-card shadow="hover" style="text-align: center ;cursor: pointer;height: 175px">
          <img src="../../assets/os/linux.jpg" width="100%"/>
          <el-button-group>
            <el-button size="mini" @click="handleConnect(index,vnc)">连接</el-button>
            <el-button size="mini" @click="doEdit(vnc)">编辑</el-button>
            <el-button size="mini" @click="doDelete(vnc)">删除</el-button>
          </el-button-group>
        </el-card>
      </el-col>
      <el-col :span="4" style="height: 175px;">
        <el-card shadow="hover" style="text-align: center ;cursor: pointer;height: 175px" title="添加VNC桌面">
          <i class="fa fa-plus" style="font-size: 145px ;color: #7D7D7D" @click="doAdd"/>
        </el-card>
      </el-col>
    </el-row>
    <div v-if="mode=='edit'" style="width: 90% ;margin-top: 40px">
      <el-form :model="form" ref="form" :rules="rules" label-width="100px">

        <el-form-item label="ip地址" prop="ip">
          <el-input placeholder="请输入ip地址" v-model="form.ip"></el-input>
        </el-form-item>
        <el-form-item label="端口" prop="port">
          <el-input-number placeholder="请输入端口" :min="5900" v-model="form.port"></el-input-number>
        </el-form-item>
        <el-form-item label="用户名" prop="user">
          <el-input placeholder="请输入用户名" v-model="form.user"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input placeholder="请输入密码" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="启/禁用" prop="status">
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

      </el-form>

    </div>
  </div>
</template>
<script>
  import {queryList, saveOrUpdate, del} from '../../service/ProtocolService';
  export default{
    props: ["id"],
    data: function () {
      return {
        loading: false,
        vncs: [],
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
    created: function () {
      this.refresh();
    },
    computed: {
      getId(){
        return 'vnc' + this.id
      }
    },

    methods: {
      refresh(){
        this.loading = true;
        const _this = this;
        queryList({type: 2}).then(function (res) {
          _this.vncs = res.data;
          _this.loading = false;
        }).catch(function (res) {
          _this.vncs = [];
          _this.loading = false;
        })
      },
      handleConnect: function (index, row) {
        const server = window.location.protocol + "//" + window.location.hostname + ':' + window.location.port + '/guacamole.html?id=' + row.id + "&type=vnc";
        Win10.openUrl(server, '远程 vnc');
      },
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          saveOrUpdate(that.dialogMode,that.form) .then(res => {
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
          type: 2,
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
      }
    },
    components: {}
  }
</script>
<style>

</style>
