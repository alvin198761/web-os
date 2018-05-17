/*应用程序图标新增与修改,作者:唐植超,日期:2018-05-12 16:51:08*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <el-form-item label="图标类型" prop="type">
            <el-input placeholder="请输入图标类型" v-model="form.type"></el-input>
          </el-form-item>
          <el-form-item label="显示文本" prop="title">
            <el-input placeholder="请输入显示文本" v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="悬停提示" prop="tip">
            <el-input placeholder="请输入悬停提示" v-model="form.tip"></el-input>
          </el-form-item>
          <el-form-item label="图标" prop="icon">
            <el-input placeholder="请输入图标" v-model="form.icon"></el-input>
          </el-form-item>
          <el-form-item label="发布人" prop="publish_id">
            <el-input placeholder="请输入发布人" v-model="form.publish_id"></el-input>
          </el-form-item>
          <el-form-item label="创建时间" prop="create_time">
            <el-input placeholder="请输入创建时间" v-model="form.create_time"></el-input>
          </el-form-item>
          <el-form-item label="父节点" prop="parent_id">
            <el-input placeholder="请输入父节点" v-model="form.parent_id"></el-input>
          </el-form-item>
          <el-form-item label="对应路由地址" prop="route_url">
            <el-input placeholder="请输入对应路由地址" v-model="form.route_url"></el-input>
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
          type: [
            {required: true, message: '请输入图标类型', trigger: 'blur'},
            {min: 1, max: 10, message: '图标类型长度不正确', trigger: 'blur'},
          ],
          title: [
            {required: true, message: '请输入显示文本', trigger: 'blur'},
            {min: 1, max: 10, message: '显示文本长度不正确', trigger: 'blur'},
          ],
          tip: [
            {required: true, message: '请输入悬停提示', trigger: 'blur'},
            {min: 1, max: 10, message: '悬停提示长度不正确', trigger: 'blur'},
          ],
          icon: [
            {required: true, message: '请输入图标', trigger: 'blur'},
            {min: 1, max: 10, message: '图标长度不正确', trigger: 'blur'},
          ],
          publish_id: [
            {required: true, message: '请输入发布人', trigger: 'blur'},
            {min: 1, max: 10, message: '发布人长度不正确', trigger: 'blur'},
          ],
          create_time: [
            {required: true, message: '请输入创建时间', trigger: 'blur'},
            {min: 1, max: 10, message: '创建时间长度不正确', trigger: 'blur'},
          ],
          parent_id: [
            {required: true, message: '请输入父节点', trigger: 'blur'},
            {min: 1, max: 10, message: '父节点长度不正确', trigger: 'blur'},
          ],
          route_url: [
            {required: true, message: '请输入对应路由地址', trigger: 'blur'},
            {min: 1, max: 10, message: '对应路由地址长度不正确', trigger: 'blur'},
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
          that.$http.post("/api/appIcons/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
          type: null,
          title: null,
          tip: null,
          icon: null,
          publish_id: null,
          create_time: null,
          parent_id: null,
          route_url: null,
          status: null,
          name: ''
        }
      },
      addDialog() {//新增
        this.title = "新增应用程序图标";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改应用程序图标";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>
