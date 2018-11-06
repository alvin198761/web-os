<template>
  <!--<div class="desktop">-->
  <!--<div id="win10-shortcuts" class="shortcuts-hidden">-->
  <!--<div class="shortcut"-->
  <!--onclick="Win10.openUrl('http://win10ui.yuri2.cn',-->
  <!--'<img class=\'icon\' src=\'/static/img/icon/win10.png\'/>Win10-UI官网')">-->
  <!--<img class="icon" src="/static/img/icon/win10.png"/>-->
  <!--<div class="title">Win10-UI官网</div>-->
  <!--</div>-->
  <!--<div class="shortcut" onclick="window.open('/static/demo.html')">-->
  <!--<img class="icon" src="/static/img/icon/demo.png"/>-->
  <!--<div class="title">查看DEMO</div>-->
  <!--</div>-->
  <!--</div>-->
  <!--<div id="win10-desktop-scene"></div>-->
  <!--</div>-->
  <!--<div id="win10-menu" class="hidden">-->
  <!--<div class="list win10-menu-hidden animated animated-slideOutLeft">-->
  <!--<div class="item"-->
  <!--onclick="Win10.exit()">-->
  <!--<i class="black icon fa fa-power-off fa-fw"></i><span class="title">关闭</span>-->
  <!--</div>-->
  <!--</div>-->
  <!--<div class="blocks">-->
  <!--<div class="menu_group">-->
  <!--<div class="title">Welcome</div>-->
  <!--<div class="block" loc="1,1" size="6,4">-->
  <!--<div class="content">-->
  <!--<div style="font-size:100px;line-height: 132px;margin: 0 auto ;display: block"-->
  <!--class="fa fa-fw fa-windows win10-block-content-text"></div>-->
  <!--<div class="win10-block-content-text" style="font-size: 22px">欢迎使用 Win10-UI</div>-->
  <!--</div>-->
  <!--</div>-->
  <!--</div>-->
  <!--</div>-->
  <!--<div id="win10-menu-switcher"></div>-->
  <!--</div>-->
  <!--<div id="win10_command_center" class="hidden_right">-->
  <!--<div class="title">-->
  <!--<h4 style="float: left">消息中心 </h4>-->
  <!--<span id="win10_btn_command_center_clean_all">全部清除</span>-->
  <!--</div>-->
  <!--<div class="msgs"></div>-->
  <!--</div>-->
  <!--<div id="win10_task_bar">-->
  <!--<div id="win10_btn_group_left" class="btn_group">-->
  <!--<div id="win10_btn_win" class="btn"><span class="fa fa-windows"></span></div>-->
  <!--<div class="btn" id="win10-btn-browser"><span class="fa fa-internet-explorer"></span></div>-->
  <!--</div>-->
  <!--<div id="win10_btn_group_middle" class="btn_group"></div>-->
  <!--<div id="win10_btn_group_right" class="btn_group">-->
  <!--<div class="btn" id="win10_btn_time"></div>-->
  <!--<div class="btn" id="win10_btn_command"><span id="win10-msg-nof" class="fa fa-comment-o"></span></div>-->
  <!--<div class="btn" id="win10_btn_show_desktop"></div>-->
  <!--</div>-->
  <!--</div>-->
  <div id="win10"  >
    <Desktop  ></Desktop>
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
        'theme': 'app/_getTheme'
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
//        setTimeout(function () {
//          Win10.newMsg('官方交流一群', '欢迎各位大侠加入讨论：<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=4Er0u8i">[点击加入]205546163</a>')
//        }, 2500);
      },
      alertNews(){
//        setTimeout(function () {
//          Win10.openUrl('https://www.163.com', '<i class="fa fa-newspaper-o icon red"></i>最新资讯', [['300px', '380px'], 'rt'])
//        }, 2000);
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
