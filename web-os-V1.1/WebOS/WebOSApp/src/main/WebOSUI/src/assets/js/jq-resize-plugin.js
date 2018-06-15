/**
 * Created by tangzhichao on 2017/3/1.
 */
/*
 * jQuery.Resize by wuxinxi007
 * Date: 2011-5-14
 * blog : http://wuxinxi007.cnblogs.com/
 */
(function ($) {
  const resizeMap = {
    "t": "left:0;top:-3px;width:100%;height:5px;z-index:1;cursor:n-resize",
    "r": "right:-3px;top:0;width:5px;height:100%;z-index:1;cursor:e-resize",
    "b": "left:0;bottom:-3px;width:100%;height:5px;z-index:1;cursor:s-resize",
    "l": "left:-3px;top:0;width:5px;height:100%;z-index:1;cursor:w-resize",
    "rt": "right:-3px;top:-3px;width:10px;height:10px;z-index:2;cursor:ne-resize",
    "rb": "right:-3px;bottom:-3px;width:10px;height:10px;z-index:2;cursor:se-resize",
    "lt": "left:-3px;top:-3px;width:10px;height:10px;z-index:2;cursor:nw-resize",
    "lb": "left:-3px;bottom:-3px;width:10px;height:10px;z-index:2;cursor:sw-resize"
  };
  $.resizeable = $.noop;
  $.fn.resizeable = function () {
    var bound = {
      x: this.offsetX,
      y: this.offsetY,
      w: this.offsetWidth,
      h: this.offsetHeight
    }
    var obj = this;
    for (var i in resizeMap) {
      obj.find("div[resize='" + i + "']").mousemove(function () {
        var bound = {
          x: obj.offsetX,
          y: obj.offsetY,
          w: obj.offsetWidth,
          h: obj.offsetHeight
        }

        obj.unbind("mousemove").bind("mousemove",function(e){

        });
      });
    }

    this.mousedown(function (e) {
      //按下元素后，计算当前鼠标与对象计算后的坐标
      bound = {
        x: obj.offsetX,
        y: obj.offsetY,
        w: obj.offsetWidth,
        h: obj.offsetHeight
      }

      obj.setCapture ? (
        obj.setCapture(),
          obj.onmousemove = function (ev) {
            mouseMove(ev || event);
          },
          obj.onmouseup = mouseUp
      ) : (
        $(document).bind("mousemove", mouseMove).bind("mouseup", mouseUp)
      );
      e.preventDefault();
    });
    //移动事件
    function mouseMove(e) {
      obj.css({
        width: e.clientX - bound.x + 'px',
        height: e.clientY - bound.y + 'px'
      })
    }

    //停止事件
    function mouseUp() {
      //在支持 releaseCapture 做些东东
      obj.releaseCapture ? (
        //释放焦点
        obj.releaseCapture(),
          //移除事件
          obj.onmousemove = obj.onmouseup = null
      ) : (
        //卸载事件
        $(document).unbind("mousemove", mouseMove).unbind("mouseup", mouseUp)
      );
    }
  }
})(jQuery);
