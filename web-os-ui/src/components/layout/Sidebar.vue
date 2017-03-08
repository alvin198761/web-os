<template>
  <div id="leftBar">
    <div id="dockContainer" class="dock_container  dock_pos_left" style="z-index: 10;">
      <div class="dock_middle ui-sortable">
        <div v-for="app in apps" @click="openBrowser(app)" type="2" class="appButton" :id="'sidebar_app'+app.id"
             :appid="app.id" fileid="1" :title="app.title" sonmenu="[]"
             :uid="'app_' + app.id">
          <div id="'sidebar_app'+app.id+'_icon_div'" class="appButton_appIcon">
            <img :alt="app.title" :src="app.icon" class="appButton_appIconImg" id="'sidebar_app'+app.id+'_img'"></div>
          <div class="appButton_appName">
            <div class="appButton_appName_inner" id="'sidebar_app'+app.id+'_name'">{{app.title}}</div>
            <div class="appButton_appName_inner_right"></div>
          </div>
        </div>
        <div class="dock_tool_list" id="dockToolList">
          <div class="dock_tool_item">
            <a title="输入法" cmd="Pinyin" class="dock_tool_icon dock_tool_pinyin"
               href="javascript:void(0)"></a>
            <a title="静音" cmd="Sound" class="dock_tool_icon dock_tool_sound"
               href="javascript:void(0)"></a>
          </div>
          <div class="dock_tool_item">
            <a @click="openSystemSettingDialog" title="系统设置" cmd="Setting" class="dock_tool_icon dock_tool_setting"
               href="javascript:void(0)"></a>
            <a title="主题设置" cmd="Theme" class="dock_tool_icon dock_tool_theme" href="javascript:void(0)"></a>
          </div>
          <div class="dock_tool_item"><a title="点击这里开始" class="dock_tool_icon dock_tool_start" href="#"></a></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import {mapGetters, mapMutations} from 'vuex';
  import SysSettingDialog from '../setting/SysSettingDialog';
  export default{
    data: function () {
      return {}
    },
    computed: {
      ...mapGetters({
        apps: 'sidebar/_apps'
      })
    },
    components: {},
    created: function () {
      this.$store.commit('sidebar/fetch');
    },
    mounted: function () {
      this.$store.commit('sidebar/initComponent')
    },
    methods: {
      ...mapMutations({}),
      openBrowser: function (app) {
         this.$store.dispatch('taskbar/open_'+app.url, app)
      },
      openSystemSettingDialog: function (e) {
        this.$store.commit('desktop/addComponent', {
          component: SysSettingDialog,
          options: {},
          userObject: {}
        })
      }
    }
  }
</script>
<style>

</style>
