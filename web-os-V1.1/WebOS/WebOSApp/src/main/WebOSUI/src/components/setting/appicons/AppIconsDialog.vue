/*应用程序图标新增与修改,作者:唐植超,日期:2018-05-12 16:51:08*/
<template>
  <Explorer :id="userObject.route_url" title="添加应用" :resize="false" :min="false" :max="false"
            :handleClose="handleClose" :winHeight="420">
    <div slot="content" style="padding-left: 20px; padding-right: 20px; padding-top: 10px">
      <el-form :model="form" ref="form" :rules="rules" label-width="100px">
        <el-form-item label="显示文本" prop="title">
          <el-input placeholder="请输入显示文本" size="small" v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="悬停提示" prop="tip">
          <el-input placeholder="请输入悬停提示" size="small" v-model="form.tip"></el-input>
        </el-form-item>
        <el-form-item label="图标链接" prop="icon">
          <el-input placeholder="请输入图标" size="small" v-model="form.icon"></el-input>
        </el-form-item>
        <el-form-item label="应用地址" prop="route_url">
          <el-input placeholder="请输入对应路由地址" size="small" v-model="form.route_url"></el-input>
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
      </el-form>
      <div slot="footer" style="text-align: right">
        <el-button @click="handleClose" size="small">取消</el-button>
        <el-button type="primary" @click="save()" size="small">确定</el-button>
      </div>
    </div>
  </Explorer>
</template>
<script>
  import Explorer from '../../commons/Explorer.vue';
  import http from '../../../plugins/http';
  import  {APP_TYPE} from '../../../constant';
  export default {
    props: ['userObject', 'options', 'index'],
    data() {
      return {
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        rules: {
          title: [
            {required: true, message: '请输入显示文本', trigger: 'blur'},
          ],
          tip: [
            {required: true, message: '请输入悬停提示', trigger: 'blur'},
          ],
          icon: [
            {required: true, message: '请输入图标', trigger: 'blur'},
          ],
          route_url: [
            {required: true, message: '请输入对应路由地址', trigger: 'blur'},
          ],
          status: [
            {required: true, message: '请输入状态', trigger: 'blur'},
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
          const data = {...that.form, type: APP_TYPE.APP, parent_id: that.userObject.parent_id};
          that.$http.post("/api/appIcons/" + that.dialogMode, JSON.stringify(data)).then(res => {
            that.show = false;
            that.$message.success("保存应用成功!");
            that.refresh();
            that.handleClose();
          }).catch(res => {
            that.$message.error("保存应用出错!" + res);
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
          status: 1,
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
      refresh(){
        //找到当前页下的所有图标，替换原来的
        this.$store.commit("fisheye/refreshCurrent", this.userObject.parent_id);
      },
      handleClose: function (e) {
        const _this = this;
        this.$store.dispatch('desktop/removeComponent', {
          task: _this.userObject,
          index: _this.index
        })
        this.$store.commit('taskbar/removeTask', this.userObject)
      },
    },
    components: {Explorer}
  }
</script>
<style></style>
