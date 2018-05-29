<template>
  <Explorer :id="'ssh'" title="ssh 远程列表" :resize="false" :min="false" :max="false" :handleClose="handleClose">
    <div slot="content" >
      <el-row :gutter="10" v-loading="loading" element-loading-text="拼命加载中" style="overflow-y: auto">
        <el-col :span="8" v-for="ssh in sshs">
          <el-card shadow="hover" style="text-align: center ;cursor: pointer">
            <img src="../../../assets/os/ssh.jpg" width="100%"/>
            <el-button-group >
              <el-button size="mini">连接</el-button>
              <el-button size="mini">编辑</el-button>
              <el-button size="mini">删除</el-button>
            </el-button-group>
          </el-card>
        </el-col>
      </el-row>
    </div>
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
      this.$http.post('/api/protocols/queryList',JSON.stringify({
        type:3
      })).then(function (res) {
        _this.sshs = res.data;
        _this.loading = false;
      }).catch(function (res) {
        _this.sshs = [];
        _this.loading = false;
      })
    },
    methods: {
      handleConnect: function (index, row) {
        var app = {
          id: 'ssh01',
          title: '远程ssh',
          route_url: '/guacamole.html?id=' + row.id + "&type=ssh",
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
