<!DOCTYPE html><html><head><meta charset=utf-8><title>WebOS V 1.1 -- win10 ui</title><meta charset=utf-8><meta http-equiv=X-UA-Compatible content="IE=edge"><meta name=viewport content="width=device-width,minimum-scale=1,maximum-scale=1,user-scalable=no"><script type=text/javascript src=static/jslib/jquery-2.2.4.min.js></script><link href=static/csslibs/animate.css rel=stylesheet><script type=text/javascript src=./static/component/layer-v3.0.3/layer/layer.js></script><link rel=stylesheet href=./static/component/font-awesome-4.7.0/css/font-awesome.min.css><link href=static/csslibs/default.css rel=stylesheet><script type=text/javascript src=static/jslib/win10.js></script><link href=static/csslibs/header/component.css rel=stylesheet><link href=static/csslibs/header/normalize.css rel=stylesheet><link href=static/csslibs/header/universe.css rel=stylesheet><script src=static/jslib/TweenLite.min.js></script><script src=static/jslib/EasePack.min.js></script><script type=text/javascript>//      //该函数可删除 Orz
//      function win10_forgive_me() {
//        Win10.enableFullScreen();
//        layer.alert('点击展示下一版本特性', {}, function(index){
//          var blue=$('<img src="./img/presentation/bluescreen.jpg" style="position: fixed;width: 100%;height:100%;top:0;z-index:9999999999" />');
//          setTimeout(function () {
//            $('body').append(blue);
//          },3000);
//          setTimeout(function () {
//            blue.remove();
//            Win10.disableFullScreen();
//            setTimeout(function () {
//              layer.msg('开个玩笑，别打我');
//
//            },1000);
//          },7000);
//          layer.close(index);
//        });
//
//      }

      Win10.onReady(function () {

        //设置壁纸
        Win10.setBgUrl({
          main:'./static/img/wallpapers/main.jpg',
          mobile:'./static/img/wallpapers/mobile.jpg',
        });

        Win10.setAnimated([
          'animated flip',
          'animated bounceIn',
        ], 0.01);

      });</script><link href=/css/app.c73fdc330700ac2c8ddda5e631d01737.css rel=stylesheet></head><body><div id=app></div><script type=text/javascript src=/js/manifest.1766e3c7586055624ca0.js></script><script type=text/javascript src=/js/vendor.404e73eb42ffbb178f73.js></script><script type=text/javascript src=/js/app.0bf065abcd303ab90bd8.js></script></body></html>