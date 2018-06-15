/*远程协议新增与修改,作者:唐植超,日期:2018-05-12 16:51:08*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="ad域" prop="domain">
            <el-input placeholder="请输入ad域" v-model="form.domain"></el-input>
          </el-form-item>
          <el-form-item label="用户名" prop="user">
            <el-input placeholder="请输入用户名" v-model="form.user"></el-input>
          </el-form-item>
          <el-form-item label="ip地址" prop="ip">
            <el-input placeholder="请输入ip地址" v-model="form.ip"></el-input>
          </el-form-item>
          <el-form-item label="端口" prop="port">
            <el-input placeholder="请输入端口" v-model="form.port"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input placeholder="请输入密码" v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="作者" prop="author">
            <el-input placeholder="请输入作者" v-model="form.author"></el-input>
          </el-form-item>
          <el-form-item label="时间" prop="create_time">
            <el-input placeholder="请输入时间" v-model="form.create_time"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-input placeholder="请输入状态" v-model="form.status"></el-input>
          </el-form-item>
          <el-form-item label="协议类型" prop="type">
            <el-input placeholder="请输入协议类型" v-model="form.type"></el-input>
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
          domain: [
            {required: true, message: '请输入ad域', trigger: 'blur'},
            {min: 1, max: 10, message: 'ad域长度不正确', trigger: 'blur'},
          ],
          user: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {min: 1, max: 10, message: '用户名长度不正确', trigger: 'blur'},
          ],
          ip: [
            {required: true, message: '请输入ip地址', trigger: 'blur'},
            {min: 1, max: 10, message: 'ip地址长度不正确', trigger: 'blur'},
          ],
          port: [
            {required: true, message: '请输入端口', trigger: 'blur'},
            {min: 1, max: 10, message: '端口长度不正确', trigger: 'blur'},
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 1, max: 10, message: '密码长度不正确', trigger: 'blur'},
          ],
          author: [
            {required: true, message: '请输入作者', trigger: 'blur'},
            {min: 1, max: 10, message: '作者长度不正确', trigger: 'blur'},
          ],
          create_time: [
            {required: true, message: '请输入时间', trigger: 'blur'},
            {min: 1, max: 10, message: '时间长度不正确', trigger: 'blur'},
          ],
          status: [
            {required: true, message: '请输入状态', trigger: 'blur'},
            {min: 1, max: 10, message: '状态长度不正确', trigger: 'blur'},
          ],
          type: [
            {required: true, message: '请输入协议类型', trigger: 'blur'},
            {min: 1, max: 10, message: '协议类型长度不正确', trigger: 'blur'},
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
          that.$http.post("/api/protocols/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
          domain: null,
          user: null,
          ip: null,
          port: null,
          password: null,
          author: null,
          create_time: null,
          status: null,
          type: null,
          name: ''
        }
      },
      addDialog() {//新增
        this.title = "新增远程协议";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改远程协议";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>
