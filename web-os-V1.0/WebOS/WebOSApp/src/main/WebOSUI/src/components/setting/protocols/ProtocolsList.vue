/*远程协议列表,作者:唐植超,日期:2018-05-12 16:51:08*/
<template>
  <div><h3>远程协议</h3>
    <hr/>
    <el-form :inline="true">
      <el-form-item label="主键">
        <el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>
      </el-form-item>
      <el-form-item label="ad域">
        <el-input placeholder="请输入ad域" size="small" v-model="form.domain"></el-input>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input placeholder="请输入用户名" size="small" v-model="form.user"></el-input>
      </el-form-item>
      <el-form-item label="ip地址">
        <el-input placeholder="请输入ip地址" size="small" v-model="form.ip"></el-input>
      </el-form-item>
      <el-form-item label="端口">
        <el-input placeholder="请输入端口" size="small" v-model="form.port"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input placeholder="请输入密码" size="small" v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="作者">
        <el-input placeholder="请输入作者" size="small" v-model="form.author"></el-input>
      </el-form-item>
      <el-form-item label="时间">
        <el-input placeholder="请输入时间" size="small" v-model="form.create_time"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-input placeholder="请输入状态" size="small" v-model="form.status"></el-input>
      </el-form-item>
      <el-form-item label="协议类型">
        <el-input placeholder="请输入协议类型" size="small" v-model="form.type"></el-input>
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
                    <el-form-item label="ad域">{{props.row.domain}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="用户名">{{props.row.user}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="ip地址">{{props.row.ip}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="端口">{{props.row.port}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="密码">{{props.row.password}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="作者">{{props.row.author}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="时间">{{props.row.create_time}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="状态">{{props.row.status}}</el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="协议类型">{{props.row.type}}</el-form-item>
                  </el-col>
              </el-row>
            </el-form>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="主键"></el-table-column>
      <el-table-column prop="domain" label="ad域"></el-table-column>
      <el-table-column prop="user" label="用户名"></el-table-column>
      <el-table-column prop="ip" label="ip地址"></el-table-column>
      <el-table-column prop="port" label="端口"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="create_time" label="时间"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
      <el-table-column prop="type" label="协议类型"></el-table-column>
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
    <ProtocolsDialog ref="dialog" :refresh="refresh"></ProtocolsDialog>
  </div>
</template>
<script>
  import ProtocolsDialog from './ProtocolsDialog.vue';

  export default {
    data: function () {
      return {
        total: 0,
        page: 1,
        dataList: [],
        form: {
          id: null,
          domain: null,
          user: null,
          ip: null,
          port: null,
          password: null,
          author: null,
          create_time: null,
          status: null,
          type: null,
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
        that.$http.post("/api/protocols/queryPage", JSON.stringify(requestData)).then(res => {
          that.loading = false;
          that.dataList = res.data.content;
          that.total = res.data.totalElements;
        }).catch(res => {
          that.$message.error("获取远程协议列表失败：" + res);
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
          that.$http.delete("/api/protocols/delete", {
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
      ProtocolsDialog
    }
  }
</script>
<style></style>
