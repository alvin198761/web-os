<template>
  <div style="padding-left: 20px; padding-right: 20px; padding-top: 10px">
    <el-table :data="dataList" height="500" v-loading="loading" element-loading-text="正在加载......" style="width: 100%">
      <el-table-column prop="title" label="显示文本" width="100"></el-table-column>
      <el-table-column prop="tip" label="悬停提示"></el-table-column>
      <el-table-column prop="icon" label="图标地址"></el-table-column>
      <!--<el-table-column prop="publish_id" label="发布人"></el-table-column>-->
      <!--<el-table-column prop="create_time" label="创建时间"></el-table-column>-->
      <el-table-column prop="route_url" label="对应路由地址"></el-table-column>
      <!--<el-table-column prop="status" label="状态"></el-table-column>-->
      <!--<el-table-column prop="seq_num" label="顺序"></el-table-column>-->
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" @click="doDelete(props.row)">卸载</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-row style="margin-top: 20px">
      <el-col :span="14" :offset="5">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="显示文本" prop="title">
                <el-input placeholder="请输入显示文本" size="small" v-model="form.title"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="悬停提示" prop="tip">
                <el-input placeholder="请输入悬停提示" size="small" v-model="form.tip"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="图标链接" prop="icon">
            <el-input placeholder="请输入图标" size="small" v-model="form.icon"></el-input>
          </el-form-item>
          <el-row>
            <el-col :span="12">
              <el-form-item label="应用地址" prop="route_url">
                <el-input placeholder="请输入对应路由地址" size="small" v-model="form.route_url"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="状态" prop="status">
                <el-switch
                  v-model="form.status"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  :active-value="1"
                  :inactive-value="0"
                >
                </el-switch>
                <el-button style="margin-left: 30px" @click="save()" size="small">重置</el-button>
                <el-button type="primary" style="margin-left: 30px" @click="save()" size="small">确定</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import {queryList, saveOrUpdate, del} from '../../service/AppIconsService';
  export default{
    data: function () {
      return {
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        dataList: [],
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
    computed: {},
    created: function () {
      this.refresh();
    },
    methods: {
      refresh() {
        const that = this;
        that.loading = true;
        queryList({}).then(res => {
          that.loading = false;
          that.dataList = res.data;
        }).catch(res => {
          that.$message.error("获取应用程序图标列表失败：" + res);
          that.loading = false;
        });
      },
      save() {//新增及修改记录
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          const data = {...that.form, type: 1};
          saveOrUpdate(that.dialogMode, data).then(res => {
            that.show = false;
            that.$message.success("保存应用成功!");
            that.refresh();
            window.parent.store.commit("app/initDesktopApp");
            that.addDialog();
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
        this.dialogMode = "save";
        this.form = this.initForm();
      },
      doEdit(row) {//修改
        this.dialogMode = "update";
        this.form = {...row};
      },
      doDelete(row) {
        const that = this;
        this.$confirm('你确定要卸载该应用吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          del([row.id]).then(res => {
            this.$message.success("卸载成功");
            window.parent.store.commit("app/initDesktopApp");
            that.refresh();
          }).catch(res => {
            that.$message.error("卸载失败：" + res);
          });
        }).catch(() => {
        });
      }
    },
    components: {}
  }
</script>
<style>

</style>
