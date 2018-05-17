/*便签信息列表,作者:唐植超,日期:2018-05-12 16:51:08*/
<template>
  <div><h3>便签信息</h3>
    <hr/>
    <el-form :inline="true">
      <el-form-item label="主键">
        <el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>
      </el-form-item>
      <el-form-item label="标题">
        <el-input placeholder="请输入标题" size="small" v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input placeholder="请输入内容" size="small" v-model="form.content"></el-input>
      </el-form-item>
      <el-form-item label="最后修改时间">
        <el-input placeholder="请输入最后修改时间" size="small" v-model="form.last_time"></el-input>
      </el-form-item>
      <el-form-item label="作者">
        <el-input placeholder="请输入作者" size="small" v-model="form.author"></el-input>
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
                    <el-form-item label="标题">{{props.row.title}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="内容">{{props.row.content}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="最后修改时间">{{props.row.last_time}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="作者">{{props.row.author}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="状态">{{props.row.status}}</el-form-item>
                  </el-col>
              </el-row>
            </el-form>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="主键"></el-table-column>
      <el-table-column prop="title" label="标题"></el-table-column>
      <el-table-column prop="content" label="内容"></el-table-column>
      <el-table-column prop="last_time" label="最后修改时间"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
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
    <NotesDialog ref="dialog" :refresh="refresh"></NotesDialog>
  </div>
</template>
<script>
  import NotesDialog from './NotesDialog.vue';

  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        dataList: [],
        form: {
          id: null,
          title: null,
          content: null,
          last_time: null,
          author: null,
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
        that.$http.post("/api/notes/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.content;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取便签信息列表失败：" + res);
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
          that.$http.delete("/api/notes/delete", {
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
      NotesDialog
    }
  }
</script>
<style></style>
