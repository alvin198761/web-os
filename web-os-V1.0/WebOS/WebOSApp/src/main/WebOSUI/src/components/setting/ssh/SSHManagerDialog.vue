<template>
  <Explorer :id="'ssh'" title="ssh 远程列表" :resize="false" :min="false" :max="false" :handleClose="handleClose">
    <el-table slot="content" v-loading="loading" element-loading-text="拼命加载中"
              :data="sshs"
              style="width: 100%">
      <el-table-column
        prop="ip"
        label="Ip"
      >
      </el-table-column>
      <el-table-column
        prop="port"
        label="端口">
      </el-table-column>
      <el-table-column
        prop="user"
        label="用户名">
      </el-table-column>
      <el-table-column
        label="操作">
        <template scope="scope">
          <el-button
            size="small" type="text"
            @click="handleConnect(scope.$index, scope.row)">连接
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </Explorer>
</template>
<script>
  import Explorer from '../../commons/Explorer.vue';
  import http from '../../../plugins/http';
  export default {
    props: ['userObject', 'options', 'index'],
    data: function () {
      return {
        loading: false,
        sshs: []
      }
    },
    created: function () {
      this.loading = true;
      const _this = this;
      http.get("/api/setting/sshs").then(function (res) {
        _this.sshs = res.data;
        _this.loading = false;
      }).catch(function (res) {
        _this.sshs = [];
        _this.loading = false;
      });
    },
    methods: {
      handleConnect: function (index, row) {
        var app = {
          id: 'ssh01',
          title: '远程ssh',
          url: '/guacamole.html?id=' + row.id + "&type=ssh",
          icon: require('../../../assets/icon/sidebar/putty.png')
        }
        this.$store.dispatch('taskbar/addBrowser', app);
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
    mounted: function () {

    },
    components: {
      Explorer
    }
  }
</script>
<style>

</style>
