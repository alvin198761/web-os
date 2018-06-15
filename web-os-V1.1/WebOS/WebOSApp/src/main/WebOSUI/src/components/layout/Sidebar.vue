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
            <a title="我的相册" class="dock_tool_icon dock_tool_pinyin"
               href="javascript:void(0)" @click="openCapture"></a>
            <a title="声音" cmd="Sound" class="dock_tool_icon dock_tool_sound"
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
        this.$store.dispatch('taskbar/open_' + app.route_url, app)
      },
      openSystemSettingDialog: function (e) {
        this.$store.commit('desktop/addComponent', {
          component: require('../setting/SysSettingDialog'),
          options: {},
          userObject: {}
        })
      },
      openCapture: function () {
//        const app = {
//          "id": -2,
//          "type": 1,
//          "title": "我的相册",
//          "tip": "打开我的相册",
//          "icon": require('../../assets/icon/1.png'),
//          "publish_id": 0,
//          "create_time": new Date(),
//          "parent_id": 0,
//          "route_url": "capture",
//          "status": 1
//        };

        this.$store.commit('desktop/addComponent', {
          component: require('../setting/MyPhoto.vue'),
          options: {},
          userObject: {}
        })

      }
    }
  }
</script>
<style>

</style>
