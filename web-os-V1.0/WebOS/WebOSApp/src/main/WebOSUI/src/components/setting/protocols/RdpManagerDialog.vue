<template>
  <Explorer :id="'rdp'" title="Rdp 远程连接" :resize="false" :min="false" :max="false" :handleClose="handleClose">
    <div slot="content" >
      <el-row :gutter="10" v-loading="loading" element-loading-text="拼命加载中" style="overflow-y: auto">
        <el-col :span="8" v-for="rdp in rdps">
          <el-card shadow="hover" style="text-align: center ;cursor: pointer">
            <img src="../../../assets/os/windows.jpg" width="100%"/>
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
  import {mapGetters} from 'vuex';
  import http from '../../../plugins/http';
  export default {
    props: ['userObject', 'options', 'index'],
    data: function () {
      return {
        loading: false,
        rdps: [],
        text: ''
      }
    },
    computed: {
      ...mapGetters({})
    },
    created: function () {
      this.loading = true;
      const _this = this;
      this.$http.post('/api/protocols/queryList',JSON.stringify({
          type:1
      })).then(function (res) {
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
