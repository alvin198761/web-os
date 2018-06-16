<template>
  <div class="container demo-2">
    <div class="content">
      <div id="large-header" class="large-header">
        <canvas id="demo-canvas" style="z-index: 0"></canvas>
        <div style="height: 100%">
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

      </div>
    </div>
  </div>

</template>

<script>
  var width, height, largeHeader, canvas, ctx, circles, target, animateHeader = true;

  function initHeader() {
    width = window.innerWidth;
    height = window.innerHeight;
    target = {x: 0, y: height};
//    console.log(document.getElementById('large-header'))
    largeHeader = document.getElementById('large-header');
    largeHeader.style.height = height + 'px';

    canvas = document.getElementById('demo-canvas');
    canvas.width = width;
    canvas.height = height;
    ctx = canvas.getContext('2d');

    // create particles
    circles = [];
    for (var x = 0; x < width * 0.5; x++) {
      var c = new Circle();
      circles.push(c);
    }
    animate();
  }
  // Event handling
  function addListeners() {
    window.addEventListener('scroll', scrollCheck);
    window.addEventListener('resize', resize);
  }

  function scrollCheck() {
    if (document.body.scrollTop > height) animateHeader = false;
    else animateHeader = true;
  }

  function resize() {
    width = window.innerWidth;
    height = window.innerHeight;
    largeHeader.style.height = height + 'px';
    canvas.width = width;
    canvas.height = height;
  }

  function animate() {
    if (animateHeader) {
      ctx.clearRect(0, 0, width, height);
      for (var i in circles) {
        circles[i].draw();
      }
    }
    requestAnimationFrame(animate);
  }

  // Canvas manipulation
  function Circle() {
    var _this = this;

    // constructor
    (function () {
      _this.pos = {};
      init();
//      console.log(_this);
    })();

    function init() {
      _this.pos.x = Math.random() * width;
      _this.pos.y = height + Math.random() * 100;
      _this.alpha = 0.1 + Math.random() * 0.3;
      _this.scale = 0.1 + Math.random() * 0.3;
      _this.velocity = Math.random();
    }

    this.draw = function () {
      if (_this.alpha <= 0) {
        init();
      }
      _this.pos.y -= _this.velocity;
      _this.alpha -= 0.0005;
      ctx.beginPath();
      ctx.arc(_this.pos.x, _this.pos.y, _this.scale * 10, 0, 2 * Math.PI, false);
      ctx.fillStyle = 'rgba(255,255,255,' + _this.alpha + ')';
      ctx.fill();
    };
  }
  export default{
    data: function () {
      return {}
    },
    computed: {},
    created: function () {

    },
    mounted(){
      initHeader();
      addListeners();

    },
    methods: {},
    components: {}
  }
</script>
<style>

</style>
