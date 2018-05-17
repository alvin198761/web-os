<template>
  <canvas id="clockBox" style="z-index: 81; position: fixed;right: 10px;top: 5px;" width="100" height="100"></canvas>
</template>
<script>
  export default {

    data: function () {
      return {}
    },
    created: function () {

    },
    methods: {
      draw: function (canvas, context) {
        var x = canvas.width,
          y = canvas.height,
          r = Math.min(x / 2, y / 2);

        context.clearRect(0, 0, x, y); //清除绘画历史
        //绘画钟框
        context.fillStyle = "#f1f1f1";
        this.drawCircle(context, x, y, r);
        //绘画文字
        var tx = x / 2, ty = y / 2, tr = 0.8 * r;
        context.font = "bold 12px 微软雅黑";
        context.fillStyle = "#000";
        this.drawText(context, "1", tx + 0.5 * tr, ty - 0.866 * tr);
        this.drawText(context, "2", tx + 0.866 * tr, ty - 0.5 * tr);
        this.drawText(context, "3", tx + tr, ty);
        this.drawText(context, "4", tx + 0.866 * tr, ty + 0.5 * tr);
        this.drawText(context, "5", tx + 0.5 * tr, ty + 0.866 * tr);
        this.drawText(context, "6", tx, ty + tr);
        this.drawText(context, "7", tx - 0.5 * tr, ty + 0.866 * tr);
        this.drawText(context, "8", tx - 0.866 * tr, ty + 0.5 * tr);
        this.drawText(context, "9", tx - tr, ty);
        this.drawText(context, "10", tx - 0.866 * tr, ty - 0.5 * tr);
        this.drawText(context, "11", tx - 0.5 * tr, ty - 0.866 * tr);
        this.drawText(context, "12", tx, ty - tr);
        //获取当前时间
        var date = new Date(),
          h = date.getHours(),
          m = date.getMinutes(),
          s = date.getSeconds(),
          angleH = (360 / 12) * Math.PI / 180,
          angleM = (360 / 60) * Math.PI / 180
        context.strokeSyle = "#000";
        //绘制时刻度
        this.drawScale(context, x, y, r, angleH, -0.88 * r, -0.96 * r, 3);
        //绘制分刻度
        this.drawScale(context, x, y, r, angleM, -0.93 * r, -0.96 * r, 1);
        //绘画时分秒针
        this.drawCircle(context, x, y, 3);
        this.drawNeedle(context, x, y, r, h * angleH + m * angleM / 12, -0.5 * r);
        this.drawNeedle(context, x, y, r, m * angleM + s * angleM / 60, -0.6 * r);
        this.drawNeedle(context, x, y, r, s * angleM, -0.75 * r);
      },
      //绘画圆
      drawCircle: function (context, x, y, r) {
        context.save();
        context.beginPath();
        context.arc(x / 2, y / 2, r, 0, Math.PI * 2, 0);
        context.fill();
        context.closePath();
        context.restore();
      },
      //绘画文字方法
      drawText: function (context, text, x, y) {
        context.save();
        x -= (context.measureText(text).width / 2);
        y += 4;
        context.translate(x, y);
        context.fillText(text, 0, 0);
        context.restore();
      },
      //绘制刻度方法
      drawScale: function (context, x, y, r, rotate, start, end, lineWidth) {
        context.save();
        context.beginPath();
        context.translate(x / 2, y / 2);
        context.lineWidth = lineWidth;
        for (var i = 0; i < 60; i++) {
          context.rotate(rotate);
          context.moveTo(0, start);
          context.lineTo(0, end);
        }
        context.closePath();
        context.stroke();
        context.restore();
      },
      //绘画时分秒针方法
      drawNeedle: function (context, x, y, r, rotate, line) {
        context.save();
        context.translate(x / 2, y / 2);
        context.beginPath();
        context.rotate(rotate);
        context.moveTo(0, 0.1 * r);
        context.lineTo(0, line);
        context.closePath();
        context.stroke();
        context.restore();
      }
    },
    mounted: function () {
      const canvas = document.getElementById("clockBox");
      const context = canvas.getContext("2d");

      $("#clockBox").draggable({containment: "#desktop"});
      const _this = this;
      setInterval(function () {
        _this.draw(canvas, context)
      }, 1000);
    }

  }
</script>
<style>


</style>
