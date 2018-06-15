/*用户信息列表,作者:唐植超,日期:2018-05-12 16:51:08*/
<template>
  <div><h3>用户信息</h3>
    <hr/>
    <el-form :inline="true">
      <el-form-item label="主键">
        <el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>
      </el-form-item>
      <el-form-item label="登录名称">
        <el-input placeholder="请输入登录名称" size="small" v-model="form.login_name"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input placeholder="请输入昵称" size="small" v-model="form.nick_name"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input placeholder="请输入密码" size="small" v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="身份">
        <el-input placeholder="请输入身份" size="small" v-model="form.auth"></el-input>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input placeholder="请输入创建时间" size="small" v-model="form.create_time"></el-input>
      </el-form-item>
      <el-form-item label="创建人">
        <el-input placeholder="请输入创建人" size="small" v-model="form.author"></el-input>
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
                    <el-form-item label="登录名称">{{props.row.login_name}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="昵称">{{props.row.nick_name}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="密码">{{props.row.password}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="身份">{{props.row.auth}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="创建时间">{{props.row.create_time}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="创建人">{{props.row.author}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="状态">{{props.row.status}}</el-form-item>
                  </el-col>
              </el-row>
            </el-form>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="主键"></el-table-column>
      <el-table-column prop="login_name" label="登录名称"></el-table-column>
      <el-table-column prop="nick_name" label="昵称"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
      <el-table-column prop="auth" label="身份"></el-table-column>
      <el-table-column prop="create_time" label="创建时间"></el-table-column>
      <el-table-column prop="author" label="创建人"></el-table-column>
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
    <UsersDialog ref="dialog" :refresh="refresh"></UsersDialog>
  </div>
</template>
<script>
  import UsersDialog from './UsersDialog.vue';

  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        dataList: [],
        form: {
          id: null,
          login_name: null,
          nick_name: null,
          password: null,
          auth: null,
          create_time: null,
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
        that.$http.post("/api/users/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.content;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取用户信息列表失败：" + res);
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
          that.$http.delete("/api/users/delete", {
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
      UsersDialog
    }
  }
</script>
<style></style>
