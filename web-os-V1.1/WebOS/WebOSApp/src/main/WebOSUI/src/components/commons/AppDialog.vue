<template>
  <Explorer :id="_dialogId" :title="userObject.title" :resize="false" :min="true" :max="false" :revert="false"
            :handleClose="handleClose">
    <div slot="content" class="loading-box" v-loading="loading" element-loading-text="正在加载...">
      <template v-if="apps.length === 0">
        没有子应用列表
      </template>
      <div class="app-grid" style="padding-top: 15px; padding-left: 15px" v-else>
        <ul>
          <AppButton v-for="app in apps" :app="app" :openApp="openApp"
                     style="float: left; margin-right: 50px"></AppButton>
        </ul>
      </div>
    </div>
  </Explorer>
</template>
<script>
  import {mapGetters} from 'vuex';
  import {APP_TYPE} from '../../constant';
  import Explorer from './Explorer.vue';
  import AppButton from './AppButton.vue';

  export default {
    props: ['userObject', 'options', 'index'],
    data: function () {
      return {
        apps: [],
        loading: false
      }
    },
    computed: {
      _dialogId: function () {
        return 'dialog_' + this.userObject.id + '_box';
      }
    },
    created: function () {
      this.loadSubApps();
    },
    methods: {
      loadSubApps(){
        let _this = this;
        _this.loading = true;
        _this.$http.get('/api/content/apps/' + this.userObject.id).then(function (res) {
          for (let i in res.data) {
            res.data[i].icon = require('../../assets/icon/' + res.data[i].icon)
          }
          _this.apps = res.data;
          _this.loading = false;
        }).catch(function (res) {
          _this.apps = []
          _this.loading = false;
        });
      },
      handleClose: function (e) {
        const _this = this;
        this.$store.dispatch('desktop/removeComponent', {
          task: _this.userObject,
          index: _this.index
        })
        this.$store.commit('taskbar/removeTask', this.userObject)
      },
      openApp: function () {
        console.log('open sub app')
      }
    },
    components: {
      Explorer, AppButton
    }
  }
</script>
<style>

</style>
