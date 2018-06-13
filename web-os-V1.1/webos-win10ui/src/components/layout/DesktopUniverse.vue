<template>
  <div class="container demo-1">
    <div class="content">
      <div id="large-header" class="large-header">
        <canvas id="demo-canvas" style="z-index: 0"></canvas>
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

      </div>
    </div>
  </div>

</template>

<script>
  var width, height, largeHeader, canvas, ctx, points, target, animateHeader = true;

  // Main
  function initHeader() {
    width = window.innerWidth;
    height = window.innerHeight;
    target = {x: width/2, y: height/2};

    largeHeader = document.getElementById('large-header');
    largeHeader.style.height = height+'px';

    canvas = document.getElementById('demo-canvas');
    canvas.width = width;
    canvas.height = height;
    ctx = canvas.getContext('2d');

    // create points
    points = [];
    for(var x = 0; x < width; x = x + width/20) {
      for(var y = 0; y < height; y = y + height/20) {
        var px = x + Math.random()*width/20;
        var py = y + Math.random()*height/20;
        var p = {x: px, originX: px, y: py, originY: py };
        points.push(p);
      }
    }

    // for each point find the 5 closest points
    for(var i = 0; i < points.length; i++) {
      var closest = [];
      var p1 = points[i];
      for(var j = 0; j < points.length; j++) {
        var p2 = points[j]
        if(!(p1 == p2)) {
          var placed = false;
          for(var k = 0; k < 5; k++) {
            if(!placed) {
              if(closest[k] == undefined) {
                closest[k] = p2;
                placed = true;
              }
            }
          }

          for(var k = 0; k < 5; k++) {
            if(!placed) {
              if(getDistance(p1, p2) < getDistance(p1, closest[k])) {
                closest[k] = p2;
                placed = true;
              }
            }
          }
        }
      }
      p1.closest = closest;
    }

    // assign a circle to each point
    for(var i in points) {
      var c = new Circle(points[i], 2+Math.random()*2, 'rgba(255,255,255,0.3)');
      points[i].circle = c;
    }
  }

  // Event handling
  function addListeners() {
    if(!('ontouchstart' in window)) {
      window.addEventListener('mousemove', mouseMove);
    }
    window.addEventListener('scroll', scrollCheck);
    window.addEventListener('resize', resize);
  }

  function mouseMove(e) {
    var posx = posy = 0;
    if (e.pageX || e.pageY) {
      posx = e.pageX;
      posy = e.pageY;
    }
    else if (e.clientX || e.clientY)    {
      posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
      posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
    }
    target.x = posx;
    target.y = posy;
  }

  function scrollCheck() {
    if(document.body.scrollTop > height) animateHeader = false;
    else animateHeader = true;
  }

  function resize() {
    width = window.innerWidth;
    height = window.innerHeight;
    largeHeader.style.height = height+'px';
    canvas.width = width;
    canvas.height = height;
  }

  // animation
  function initAnimation() {
    animate();
    for(var i in points) {
      shiftPoint(points[i]);
    }
  }

  function animate() {
    if(animateHeader) {
      ctx.clearRect(0,0,width,height);
      for(var i in points) {
        // detect points in range
        if(Math.abs(getDistance(target, points[i])) < 4000) {
          points[i].active = 0.3;
          points[i].circle.active = 0.6;
        } else if(Math.abs(getDistance(target, points[i])) < 20000) {
          points[i].active = 0.1;
          points[i].circle.active = 0.3;
        } else if(Math.abs(getDistance(target, points[i])) < 40000) {
          points[i].active = 0.02;
          points[i].circle.active = 0.1;
        } else {
          points[i].active = 0;
          points[i].circle.active = 0;
        }

        drawLines(points[i]);
        points[i].circle.draw();
      }
    }
    requestAnimationFrame(animate);
  }

  function shiftPoint(p) {
    TweenLite.to(p, 1+1*Math.random(), {x:p.originX-50+Math.random()*100,
      y: p.originY-50+Math.random()*100, ease:Circ.easeInOut,
      onComplete: function() {
        shiftPoint(p);
      }});
  }

  // Canvas manipulation
  function drawLines(p) {
    if(!p.active) return;
    for(var i in p.closest) {
      ctx.beginPath();
      ctx.moveTo(p.x, p.y);
      ctx.lineTo(p.closest[i].x, p.closest[i].y);
      ctx.strokeStyle = 'rgba(156,217,249,'+ p.active+')';
      ctx.stroke();
    }
  }

  function Circle(pos,rad,color) {
    var _this = this;

    // constructor
    (function() {
      _this.pos = pos || null;
      _this.radius = rad || null;
      _this.color = color || null;
    })();

    this.draw = function() {
      if(!_this.active) return;
      ctx.beginPath();
      ctx.arc(_this.pos.x, _this.pos.y, _this.radius, 0, 2 * Math.PI, false);
      ctx.fillStyle = 'rgba(156,217,249,'+ _this.active+')';
      ctx.fill();
    };
  }

  // Util
  function getDistance(p1, p2) {
    return Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
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
      initAnimation();
      addListeners();
      Win10.setContextMenu('#desktop',[
        ['<i class="fa fa-fw fa-star"></i> 收藏本页',function () {
          var url = window.location;
          var title = document.title;
          var ua = navigator.userAgent.toLowerCase();
          if (ua.indexOf("360se") > -1) {
            layer.alert(Win10.lang('您的浏览器不支持,请按 Ctrl+D 手动收藏!','Your browser does not support, please press Ctrl+D to manual collection!'));
          }
          else if (ua.indexOf("msie 8") > -1) {
            window.external.AddToFavoritesBar(url, title); //IE8
          }
          else if (document.all) {
            try{
              window.external.addFavorite(url, title);
            }catch(e){
              layer.alert(Win10.lang('您的浏览器不支持,请按 Ctrl+D 手动收藏!','Your browser does not support, please press Ctrl+D to manual collection!'));
            }
          }
          else if (window.sidebar) {
            window.sidebar.addPanel(title, url, "");
          }
          else {
            layer.alert(Win10.lang('您的浏览器不支持,请按 Ctrl+D 手动收藏!','Your browser does not support, please press Ctrl+D to manual collection!'));
          }
        }],
        ['<i class="fa fa-fw fa-window-maximize"></i> '+Win10.lang('设置主题','Enable Full Screen'),function () { console.log('主题')}],
        ['<i class="fa fa-fw fa-window-maximize"></i> '+Win10.lang('进入全屏','Enable Full Screen'),function () {Win10.enableFullScreen()}],
        ['<i class="fa fa-fw fa-window-restore"></i> '+Win10.lang('退出全屏','Disable Full Screen'),function () {Win10.disableFullScreen()}],
        '|',
        ['<i class="fa fa-fw fa-info-circle"></i> '+Win10.lang('关于','About Us'),function () {Win10.aboutUs()}],
      ]);
    },
    methods: {},
    components: {}
  }
</script>
<style>

</style>
