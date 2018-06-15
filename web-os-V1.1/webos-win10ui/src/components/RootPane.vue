<template>
  <div id="win10" style="background: url('../../static/img/wallpapers/main.jpg')">
    <Desktop v-if="theme == null || theme == 0"></Desktop>
    <DesktopSunshine v-if="theme == 1"></DesktopSunshine>
    <DesktopUniverse v-if="theme == 2"></DesktopUniverse>
    <!--<keep-alive v-if="theme">-->
    <!--<component :is="theme"></component>-->
    <!--</keep-alive>-->
    <StartMenu></StartMenu>
    <CommandCenter></CommandCenter>
    <TaskBar></TaskBar>
  </div>
</template>

<script>
  import Desktop from './layout/Desktop.vue';
  import DesktopSunshine from './layout/DesktopSunshine.vue';
  import DesktopUniverse from './layout/DesktopUniverse.vue';
  import {mapGetters} from 'vuex';
  import StartMenu from './layout/StartMenu.vue';
  import CommandCenter from './layout/CommandCenter.vue';
  import TaskBar from './layout/TaskBar.vue';
  export default {
    name: 'RootPane',
    data () {
      return {}
    },
    computed: {
      ...mapGetters({
        'theme': '_app/getTheme'
      }),
    },
    created(){
    },
    mounted(){
      this.alertQQ();
      this.alertNews();
    },
    methods: {
      setBackGround(){
      },
      alertQQ(){
        setTimeout(function () {
          Win10.newMsg('官方交流一群', '欢迎各位大侠加入讨论：<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=4Er0u8i">[点击加入]205546163</a>')
        }, 2500);
      },
      alertNews(){
        setTimeout(function () {
          Win10.openUrl('http://www.163.com', '<i class="fa fa-newspaper-o icon red"></i>最新资讯', [['300px', '380px'], 'rt'])
        }, 2000);
      }
    },
    components: {
      DesktopUniverse, Desktop, DesktopSunshine,
      StartMenu, CommandCenter, TaskBar
    }
  }
</script>

<style>
  * {
    font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti, MingLiu
  }

  /*磁贴自定义样式*/
  .win10-block-content-text {
    line-height: 44px;
    text-align: center;
    font-size: 16px;
  }
</style>
