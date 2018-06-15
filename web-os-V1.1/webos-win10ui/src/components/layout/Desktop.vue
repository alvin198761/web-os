<template>
        <div>
          <div id ="desktop" class="desktop">
            <div id="win10-shortcuts" class="shortcuts-hidden">
              <div class="shortcut"
                   onclick="Win10.openUrl('http://win10ui.yuri2.cn','<img class=\'icon\' src=\'../../assets/img/icon/win10.png\'/>Win10-UI官网')">
                <img class="icon" src="../../../static/img/icon/win10.png"/>
                <div class="title">Win10-UI官网</div>
              </div>
              <div class="shortcut"
                   onclick="Win10.openUrl('https://yuri2.cn','<img class=\'icon\' src=\'../../assets/img/icon/blogger.png\'/>尤里2号的博客')">
                <img class="icon" src="../../../static/img/icon/blogger.png"/>
                <div class="title">Yuri2's Blog</div>
              </div>
              <div class="shortcut"
                   onclick="Win10.openUrl('win10ui.yuri2.cn/src/doc.php','<img class=\'icon\' src=\'../../assets/img/icon/doc.png\'/>在线文档')">
                <img class="icon" src="../../../static/img/icon/doc.png"/>
                <div class="title">在线文档</div>
              </div>
              <div class="shortcut" onclick="window.open('https://github.com/yuri2peter/win10-ui')">
                <img class="icon" src="../../../static/img/icon/github.png"/>
                <div class="title">github</div>
              </div>
              <div class="shortcut"
                   onclick="Win10.openUrl('https://www.oschina.net/p/win10-ui','<img class=\'icon\' src=\'../../assets/img/icon/kyzg.png\'/>开源中国（求支持~）')">
                <img class="icon" src="../../../static/img/icon/kyzg.png"/>
                <div class="title">开源中国</div>
              </div>
              <div class="shortcut" onclick="window.open('https://github.com/yuri2peter/win10-ui/archive/master.zip')">
                <img class="icon" src="../../../static/img/icon/download.png"/>
                <div class="title">快速获取</div>
              </div>
              <div class="shortcut"
                   onclick='Win10.openUrl("./login.html","<i class=\"fa fa-user icon black-green\"></i>示例登录页")'>
                <i class="fa fa-user icon black-green"></i>
                <div class="title">示例登录页</div>
              </div>
              <div class="shortcut win10-open-window" data-url="win10ui.yuri2.cn/src/contributor.php">
                <i class="icon fa fa-fw fa-bank red"></i>
                <div class="title">名人堂</div>
              </div>

              <div class="shortcut"
                   onclick='Win10.openUrl("win10ui.yuri2.cn/src/workshop.php","<i class=\"fa fa-gears  icon purple\"></i>创意工坊")'>
                <i class="fa fa-gears  icon purple"></i>
                <div class="title">创意工坊</div>
              </div>
            </div>
            <div id="win10-desktop-scene"></div>
          </div>
        </div>
</template>

<script>

  export default{
    data: function () {
      return {}
    },
    computed: {},
    created: function () {

    },
    mounted(){
//      Win10.setContextMenu('#desktop',[
//        ['<i class="fa fa-fw fa-star"></i> 收藏本页',function () {
//          var url = window.location;
//          var title = document.title;
//          var ua = navigator.userAgent.toLowerCase();
//          if (ua.indexOf("360se") > -1) {
//            layer.alert(Win10.lang('您的浏览器不支持,请按 Ctrl+D 手动收藏!','Your browser does not support, please press Ctrl+D to manual collection!'));
//          }
//          else if (ua.indexOf("msie 8") > -1) {
//            window.external.AddToFavoritesBar(url, title); //IE8
//          }
//          else if (document.all) {
//            try{
//              window.external.addFavorite(url, title);
//            }catch(e){
//              layer.alert(Win10.lang('您的浏览器不支持,请按 Ctrl+D 手动收藏!','Your browser does not support, please press Ctrl+D to manual collection!'));
//            }
//          }
//          else if (window.sidebar) {
//            window.sidebar.addPanel(title, url, "");
//          }
//          else {
//            layer.alert(Win10.lang('您的浏览器不支持,请按 Ctrl+D 手动收藏!','Your browser does not support, please press Ctrl+D to manual collection!'));
//          }
//        }],
//        ['<i class="fa fa-fw fa-window-maximize"></i> '+Win10.lang('进入全屏','Enable Full Screen'),function () {Win10.enableFullScreen()}],
//        ['<i class="fa fa-fw fa-window-restore"></i> '+Win10.lang('退出全屏','Disable Full Screen'),function () {Win10.disableFullScreen()}],
//        '|',
//        ['<i class="fa fa-fw fa-info-circle"></i> '+Win10.lang('关于','About Us'),function () {Win10.aboutUs()}],
//      ]);
    },
    methods: {},
    components: {}
  }
</script>
<style>

</style>
