<template>
  <Explorer :id="'rdp'" title="Rdp 远程连接" :resize="false" :min="false" :max="false" :handleClose="handleClose">
    <el-table slot="content" v-loading="loading" element-loading-text="拼命加载中"
              :data="rdps"
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
        prop="domain"
        label="域名">
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
  import {mapGetters} from 'vuex';
  import http from '../../../plugins/http';
  export default {
    props: ['userObject', 'options', 'index'],
    data: function () {
      return {
        loading: false,
        rdps: []
      }
    },
    computed: {
      ...mapGetters({})
    },
    created: function () {
      this.loading = true;
      const _this = this;
      http.get('/api/setting/rdps').then(function (res) {
        _this.rdps = res.data;
        _this.loading = false;
      }).catch(function (res) {
        _this.rdps = [];
        _this.loading = false;
      })
    },
    mounted: function () {

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
        console.log(row)
        var app = {
          id: 'rdp01',
          title: '远程 rdp',
          url: '/guacamole.html?id=' + row.id + "&type=rdp",
          icon: require('../../../assets/icon/sidebar/rdp.png')
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
