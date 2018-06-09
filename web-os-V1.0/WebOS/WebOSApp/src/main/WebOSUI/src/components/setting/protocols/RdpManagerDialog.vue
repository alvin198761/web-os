<template>
  <Explorer :id="'rdp'" title="Rdp 远程连接" :resize="false" :min="false" :max="false" :handleClose="handleClose">
    <div slot="content">
      <el-row v-if="mode == 'list'" :gutter="10" v-loading="loading" element-loading-text="拼命加载中"
              style="overflow-y: auto ; margin: 5px 5px 5px 0px">
        <el-col :span="8" v-for="(rdp,index) in rdps" style="margin-bottom: 10px">
          <el-card shadow="hover" style="text-align: center ;cursor: pointer">
            <img src="../../../assets/os/windows.jpg" width="100%"/>
            <el-button-group>
              <el-button size="mini">连接</el-button>
              <el-button size="mini" @click="doEdit(rdp)">编辑</el-button>
              <el-button size="mini" @click="doDelete(rdp)">删除</el-button>
            </el-button-group>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" style="text-align: center ;cursor: pointer" title="添加远程桌面">
            <i class="fa fa-plus" style="font-size: 145px ;color: #7D7D7D" @click="doAdd"/>
          </el-card>
        </el-col>
      </el-row>
      <div v-if="mode=='edit'" style="width: 90% ;margin-top: 40px">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
          <el-form-item label="ad域" prop="domain">
            <el-input placeholder="请输入ad域" v-model="form.domain"></el-input>
          </el-form-item>
          <el-form-item label="远程地址" prop="ip">
            <el-input placeholder="请输入远程地址" v-model="form.ip"></el-input>
          </el-form-item>
          <el-form-item label="用户名" prop="user">
            <el-input placeholder="请输入用户名" v-model="form.user"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input placeholder="请输入密码" type="password" v-model="form.password"></el-input>
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
  </Explorer>
</template>
<script>

  import Explorer from '../../commons/Explorer.vue';
  import {mapGetters} from 'vuex';
  import http from '../../../plugins/http';
  export default {
    props: ['userObject', 'options', 'index'],
    data: function () {
      return {
        loading: false,
        rdps: [],
        text: '',
        mode: "list",

        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        rules: {
          user: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
          ],
          ip: [
            {required: true, message: '请输入远程地址', trigger: 'blur'},
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
          ]
        }
      }
    },
    computed: {
      ...mapGetters({})
    },
    created: function () {
      this.refresh();
    },
    mounted: function () {

    },
    methods: {
      refresh(){
        this.loading = true;
        const _this = this;
        this.$http.post('/api/protocols/queryList', JSON.stringify({
          type: 1
        })).then(function (res) {
          _this.rdps = res.data;
          _this.loading = false;
        }).catch(function (res) {
          _this.rdps = [];
          _this.loading = false;
        })
      },
      handleClose: function (e) {
        const _this = this;
        this.$store.dispatch('desktop/removeComponent', {
          task: _this.userObject,
          index: _this.index
        })
        this.$store.commit('taskbar/removeTask', this.userObject)
      },
      handleConnect: function (index, row) {
        console.log(row)
        var app = {
          id: 'rdp01',
          title: '远程 rdp',
          url: '/guacamole.html?id=' + row.id + "&type=rdp",
          icon: require('../../../assets/icon/sidebar/rdp.png')
        }
        this.$store.dispatch('taskbar/addBrowser', app);
      },
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.$http.post("/api/protocols/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
            that.$message.success(that.title + "成功!");
            that.mode = "list";
            that.refresh();
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
          port: 3389,
          password: null,
          author: null,
          create_time: null,
          status: 1,
          type: 1,
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
        this.$http.delete("/api/protocols/delete", {
          params: {ids: [item.id]}
        }).then(res => {
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
    components: {
      Explorer
    }

  }
</script>
<style>

</style>
