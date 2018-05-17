/*应用程序图标列表,作者:唐植超,日期:2018-05-12 16:51:08*/
<template>
  <div><h3>应用程序图标</h3>
    <hr/>
    <el-form :inline="true">
      <el-form-item label="主键">
        <el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>
      </el-form-item>
      <el-form-item label="图标类型">
        <el-input placeholder="请输入图标类型" size="small" v-model="form.type"></el-input>
      </el-form-item>
      <el-form-item label="显示文本">
        <el-input placeholder="请输入显示文本" size="small" v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="悬停提示">
        <el-input placeholder="请输入悬停提示" size="small" v-model="form.tip"></el-input>
      </el-form-item>
      <el-form-item label="图标">
        <el-input placeholder="请输入图标" size="small" v-model="form.icon"></el-input>
      </el-form-item>
      <el-form-item label="发布人">
        <el-input placeholder="请输入发布人" size="small" v-model="form.publish_id"></el-input>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input placeholder="请输入创建时间" size="small" v-model="form.create_time"></el-input>
      </el-form-item>
      <el-form-item label="父节点">
        <el-input placeholder="请输入父节点" size="small" v-model="form.parent_id"></el-input>
      </el-form-item>
      <el-form-item label="对应路由地址">
        <el-input placeholder="请输入对应路由地址" size="small" v-model="form.route_url"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-input placeholder="请输入状态" size="small" v-model="form.status"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
            <el-form>
              <el-row :gutter="10">
                  <el-col :span="6">
                    <el-form-item label="主键">{{props.row.id}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="图标类型">{{props.row.type}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="显示文本">{{props.row.title}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="悬停提示">{{props.row.tip}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="图标">{{props.row.icon}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="发布人">{{props.row.publish_id}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="创建时间">{{props.row.create_time}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="父节点">{{props.row.parent_id}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="对应路由地址">{{props.row.route_url}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="状态">{{props.row.status}}</el-form-item>
                  </el-col>
              </el-row>
            </el-form>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="主键"></el-table-column>
      <el-table-column prop="type" label="图标类型"></el-table-column>
      <el-table-column prop="title" label="显示文本"></el-table-column>
      <el-table-column prop="tip" label="悬停提示"></el-table-column>
      <el-table-column prop="icon" label="图标"></el-table-column>
      <el-table-column prop="publish_id" label="发布人"></el-table-column>
      <el-table-column prop="create_time" label="创建时间"></el-table-column>
      <el-table-column prop="parent_id" label="父节点"></el-table-column>
      <el-table-column prop="route_url" label="对应路由地址"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" @click="doDelete(props.row)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <div style="text-align: right" v-if="total > 0">
      <el-pagination small layout="prev, pager, next" :current-page="page" :total="total"
                     @current-change="(curr) => {this.page = curr ; this.refresh();}"></el-pagination>
    </div>
    <AppIconsDialog ref="dialog" :refresh="refresh"></AppIconsDialog>
  </div>
</template>
<script>
  import AppIconsDialog from './AppIconsDialog.vue';

  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        dataList: [],
        form: {
          id: null,
          type: null,
          title: null,
          tip: null,
          icon: null,
          publish_id: null,
          create_time: null,
          parent_id: null,
          route_url: null,
          status: null,
        },
        loading: false
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
        const requestData = {...that.form, page: that.page - 1};
        that.$http.post("/api/appIcons/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.content;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取应用程序图标列表失败：" + res);
          that.loading = false;
        });
      },
      doAdd() {
        this.$refs["dialog"].addDialog();
      },
      doEdit(row) {
        this.$refs["dialog"].editDialog(row);
      },
      doDelete(row) {
        const that = this;
        this.$confirm('你确定要删除吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          that.$http.delete("/api/appIcons/delete", {
            params: {ids: [row.id]}
          }).then(res => {
            this.$message.success("删除成功");
            that.refresh();
          }).catch(res => {
            that.$message.error("删除失败：" + res);
          });
        }).catch(() => {
        });
      }
    },
    components: {
      AppIconsDialog
    }
  }
</script>
<style></style>
