/*便签信息新增与修改,作者:唐植超,日期:2018-05-12 16:51:08*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="标题" prop="title">
            <el-input placeholder="请输入标题" v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="内容" prop="content">
            <el-input placeholder="请输入内容" v-model="form.content"></el-input>
          </el-form-item>
          <el-form-item label="最后修改时间" prop="last_time">
            <el-input placeholder="请输入最后修改时间" v-model="form.last_time"></el-input>
          </el-form-item>
          <el-form-item label="作者" prop="author">
            <el-input placeholder="请输入作者" v-model="form.author"></el-input>
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
          title: [
            {required: true, message: '请输入标题', trigger: 'blur'},
            {min: 1, max: 10, message: '标题长度不正确', trigger: 'blur'},
          ],
          content: [
            {required: true, message: '请输入内容', trigger: 'blur'},
            {min: 1, max: 10, message: '内容长度不正确', trigger: 'blur'},
          ],
          last_time: [
            {required: true, message: '请输入最后修改时间', trigger: 'blur'},
            {min: 1, max: 10, message: '最后修改时间长度不正确', trigger: 'blur'},
          ],
          author: [
            {required: true, message: '请输入作者', trigger: 'blur'},
            {min: 1, max: 10, message: '作者长度不正确', trigger: 'blur'},
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
          that.$http.post("/api/notes/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
          title: null,
          content: null,
          last_time: null,
          author: null,
          status: null,
          name: ''
        }
      },
      addDialog() {//新增
        this.title = "新增便签信息";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改便签信息";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>
