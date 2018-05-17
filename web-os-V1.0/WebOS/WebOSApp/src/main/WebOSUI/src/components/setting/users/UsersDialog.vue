/*用户信息新增与修改,作者:唐植超,日期:2018-05-12 16:51:08*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="登录名称" prop="login_name">
            <el-input placeholder="请输入登录名称" v-model="form.login_name"></el-input>
          </el-form-item>
          <el-form-item label="昵称" prop="nick_name">
            <el-input placeholder="请输入昵称" v-model="form.nick_name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input placeholder="请输入密码" v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="身份" prop="auth">
            <el-input placeholder="请输入身份" v-model="form.auth"></el-input>
          </el-form-item>
          <el-form-item label="创建时间" prop="create_time">
            <el-input placeholder="请输入创建时间" v-model="form.create_time"></el-input>
          </el-form-item>
          <el-form-item label="创建人" prop="author">
            <el-input placeholder="请输入创建人" v-model="form.author"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-input placeholder="请输入状态" v-model="form.status"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" style="text-align: right">
      <el-button @click="show = false">取消</el-button>
      <el-button type="primary" @click="save()">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
  export default {
    props: ["refresh"],
    data() {
      return {
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        rules: {
          login_name: [
            {required: true, message: '请输入登录名称', trigger: 'blur'},
            {min: 1, max: 10, message: '登录名称长度不正确', trigger: 'blur'},
          ],
          nick_name: [
            {required: true, message: '请输入昵称', trigger: 'blur'},
            {min: 1, max: 10, message: '昵称长度不正确', trigger: 'blur'},
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 1, max: 10, message: '密码长度不正确', trigger: 'blur'},
          ],
          auth: [
            {required: true, message: '请输入身份', trigger: 'blur'},
            {min: 1, max: 10, message: '身份长度不正确', trigger: 'blur'},
          ],
          create_time: [
            {required: true, message: '请输入创建时间', trigger: 'blur'},
            {min: 1, max: 10, message: '创建时间长度不正确', trigger: 'blur'},
          ],
          author: [
            {required: true, message: '请输入创建人', trigger: 'blur'},
            {min: 1, max: 10, message: '创建人长度不正确', trigger: 'blur'},
          ],
          status: [
            {required: true, message: '请输入状态', trigger: 'blur'},
            {min: 1, max: 10, message: '状态长度不正确', trigger: 'blur'},
          ],
        }
      }
    },
    methods: {
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.$http.post("/api/users/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
            that.show = false;
            that.$message.success(that.title + "成功!");
            that.refresh();
          }).catch(res => {
            that.$message.error(that.title + "出错!" + res);
          });
        });
      },
      initForm() {//初始数据
        return {
          login_name: null,
          nick_name: null,
          password: null,
          auth: null,
          create_time: null,
          author: null,
          status: null,
          name: ''
        }
      },
      addDialog() {//新增
        this.title = "新增用户信息";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改用户信息";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>
