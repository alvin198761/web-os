<template>
  <Explorer :id="'vnc'" title="Vnc 远程列表" :resize="false" :min="false" :max="false" :handleClose="handleClose">
      <div slot="content" >
        <el-row :gutter="10" v-loading="loading" element-loading-text="拼命加载中" style="overflow-y: auto">
          <el-col :span="8" v-for="vnc in vncs">
            <el-card shadow="hover" style="text-align: center ;cursor: pointer">
              <img src="../../../assets/os/linux.jpg" width="100%"/>
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
        vncs: []
      }
    },
    created: function () {
      this.loading = true;
      const _this = this;
      this.$http.post('/api/protocols/queryList',JSON.stringify({
        type:2
      })).then(function (res) {
        _this.vncs = res.data;
        _this.loading = false;
      }).catch(function (res) {
        _this.vncs = [];
        _this.loading = false;
      })
    },
    methods: {
      handleClose: function (e) {
        const _this = this;
        this.$store.dispatch('desktop/removeComponent', {
          task: _this.userObject,
          index: _this.index
        })
        this.$store.commit('taskbar/removeTask', this.userObject)
      },
      handleConnect: function (index, row) {
        var app = {
          id: 'vnc01',
          title: '远程 vnc',
          url: '/guacamole.html?id=' + row.id + "&type=vnc",
          icon: require('../../../assets/icon/sidebar/vnc.png')
        }
        this.$store.dispatch('taskbar/addBrowser', app);
      }
    },
    components: {
      Explorer
    }
  }
</script>
<style>

</style>
