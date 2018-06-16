<template>
  <div id="win10-menu" class="hidden">
    <div class="list win10-menu-hidden animated animated-slideOutLeft">
      <div class="item"><i class="red icon fa fa-wrench fa-fw"></i><span>运维工具</span></div>
      <div class="sub-item" @click="openApp('/protocol/rdp','Windows 远程桌面管理')">Windows 远程桌面</div>
      <div class="sub-item" @click="openApp('/protocol/vnc','Linux VNC 远程桌面管理')">Linux VNC 桌面管理</div>
      <div class="sub-item" @click="openApp('/protocol/ssh','Linux SSH管理')">Linux SSH管理</div>
      <div class="sub-item" @click="openApp('/protocol/sftp','Linux SFTP管理')">Linux SFTP管理</div>
      <div class="sub-item" @click="openApp('/protocol/ipmi','Linux IPMI管理')">Linux IPMI管理</div>
      <!--<div class="sub-item" onclick="Win10.commandCenterOpen()">打开消息中心</div>-->
      <!--<div class="sub-item"-->
      <!--onclick="Win10.newMsg('API测试','通过API可以发送消息至消息中心，自定义标题与内容(点击我试试)',function() {alert('click')})">发送带回调的消息-->
      <!--</div>-->
      <!--<div class="sub-item" onclick="Win10.menuClose()">关闭菜单</div>-->
      <div class="item"><i class="blue icon fa fa-gavel fa-fw"></i>辅助工具</div>
      <div class="sub-item" onclick="Win10.openUrl('win10ui.yuri2.cn/src/tools/builder-shortcut.html','图标代码生成器')">
       我的相册
      </div>
      <div class="sub-item" onclick="Win10.openUrl('win10ui.yuri2.cn/src/tools/builder-tile.html','磁贴代码生成器')">
        我的便签
      </div>
      <div class="sub-item" onclick="Win10.openUrl('win10ui.yuri2.cn/src/tools/builder-menu.html','菜单代码生成器')">
        我的日程
      </div>
      <div class="item" onclick="Win10.aboutUs()"><i class="purple icon fa fa-info-circle fa-fw"></i>关于</div>
      <div class="item" onclick="layer.open({
                title:'支持作者',
                type: 1,
                area:'300px',
                offset:'50px',
                shadeClose:true,
                content: '<img width=\'300\' src=\'../assets/img/presentation/donation.jpg\' />'
            })"><i class="green icon fa fa-thumbs-up fa-fw"></i>捐赠
      </div>
      <div class="item" onclick=" Win10.exit();"><i class="black icon fa fa-power-off fa-fw"></i>关闭</div>
    </div>
    <div class="blocks">
      <div class="menu_group">
        <div class="title">
          应用示例
        </div>
        <div loc="1,1" size="6,3" class="block">
          <div class="content red detail">
            <iframe style="margin-top: 10px" frameborder="no" border="0" marginwidth="0" marginheight="0" width=264
                    height=110 src="//music.163.com/outchain/player?type=2&id=110771&auto=0&height=90"></iframe>
          </div>
          <div class="content red cover">
            <img width="264" style="position: relative;top: -50px;"
                 src="../../../static/img/presentation/wangyiyun.gif"/>
          </div>
        </div>
        <div loc="1,4" size="4,4" class="block">
          <div class="content blue cover" style="font-size: 30px;line-height: 176px;text-align: center">
            <i class="fa fa-cloud fa-fw"></i> 天气
          </div>
          <div class="content detail" style="background-color: rgb(46,147,217)">
            <iframe
              src="//www.seniverse.com/weather/weather.aspx?uid=U43DF172E7&cid=CHBJ000000&l=&p=SMART&a=1&u=C&s=13&m=2&x=1&d=1&fc=&bgc=2E93D9&bc=&ti=0&in=0&li="
              frameborder="0" scrolling="no" width="200" height="300" allowTransparency="true"></iframe>
          </div>
        </div>
        <div loc="5,4" size="2,2" class="block">
          <div class="content">
            <img style="display: inline-block;border-radius: 4px" width="88px"
                 src="http://q2.qlogo.cn/headimg_dl?bs=824831811&dst_uin=824831811&src_uin=824831811&fid=824831811&spec=100&url_enc=0&referer=bu_interface&term_type=PC"/>
          </div>
        </div>
        <div loc="5,6" size="2,2" class="block" onclick="win10_forgive_me()">
          <div class="content red">
            <div style="text-align: center;font-size: 30px;line-height: 44px"><i class="fa fa-bug fa-fw"></i></div>
            <div style="text-align: center;font-size: 16px;line-height: 44px">别点我</div>
          </div>
        </div>
      </div>

      <div class="menu_group">
        <div class="title">
          常用场景
        </div>
        <div loc="1,1" size="4,3" class="block">
          <div class="content" style="background-color: orangered">
            <div class="win10-block-content-text" style="font-size: 26px;line-height: 88px">WIN10-UI</div>
            <div class="win10-block-content-text">显示信息</div>
          </div>
        </div>
        <div loc="5,1" size="2,1" class="block">
          <div class="content" style="background-color: green">
            <div class="win10-block-content-text">文字按钮</div>
          </div>
        </div>
        <div loc="5,2" size="2,2" class="block">
          <div class="content">
            <img style="width: 40px;height: 40px;margin: 5px auto;display: block"
                 src="../../../static/img/icon/baidu.png">
            <div class="win10-block-content-text">图标按钮</div>
          </div>
        </div>
        <div loc="1,4" size="6,3" class="block">
          <div class="content" style="background: url('../../../static/img/presentation/1.png');background-size: auto">
            <div style="line-height:132px;text-align: center;">显示图片</div>
          </div>
        </div>
        <div loc="1,7" size="2,1" class="block">
          <div class="content" style="background-color: grey" onclick="Win10.openUrl('//#')">
            <div class="win10-block-content-text">内部链接</div>
          </div>
        </div>
        <div loc="3,7" size="2,1" class="block">
          <div class="content" style="background-color: blue" onclick="window.open('#')">
            <div class="win10-block-content-text">外部链接</div>
          </div>
        </div>
      </div>
    </div>
    <div id="win10-menu-switcher"></div>
  </div>
</template>
<script>
  import RDPList from '../protocol/RDPList.vue';
  export default{
    data: function () {
      return {}
    },
    computed: {},
    created: function () {
//      console.log(window.location.protocol + "//" + window.location.hostname + ':' + window.location.port + '/#')
    },
    methods: {
      openApp(url, title){
        Win10.menuClose();
        const server = window.location.protocol + "//" + window.location.hostname + ':' + window.location.port + '/#' + url;
        Win10.openUrl(server, title);
      }
    },
    components: {RDPList}
  }
</script>
<style>

</style>
