<template>
  <el-row :gutter="10">
    <el-col span="10">
    <video id="video" width="320" height="200" autoplay="autoplay"></video>
    </el-col>
    <el-col span="2" style="padding-top: 80px">
    <Button id="snap">拍照</Button><br/><br/>
    <Button id="upload" @click="uploadPic">上传</Button>
    </el-col>
    <el-col span="10">
    <canvas id="canvas" width="280" height="200"></canvas>
    </el-col>
  </el-row>
</template>
<script>
  import {mapGetters} from 'vuex';
  export default{
    data: function () {
      return {
        localStream: null
      }
    },
    computed: {
      ...mapGetters({})
    },
    created: function () {
      const that = this;
      window.onbeforeunload = function () {
        closeCam(that);
      }
      // 设置事件监听，DOM内容加载完成，和jQuery的$.ready() 效果差不多。
      window.addEventListener("DOMContentLoaded", function () {
        console.log("loaded");
        webcam(that);
      }, false);
    },
    destroyed: function () {
      closeCam(this);
    },
    methods:{
      uploadPic(){
       alert('未实现！');
      }
    }
  }

  function webcam(that) {
    // canvas 元素将用于抓拍
    var canvas = document.getElementById("canvas");
    var context = canvas.getContext("2d");
    // video 元素，将用于接收并播放摄像头 的数据流
    var video = document.getElementById("video");
    var videoObj = {"video": true};
    // 一个出错的回调函数，在控制台打印出错信息
    var errBack = function (error) {
      if ("object" === typeof window.console) {
        console.log("Video capture error: ", error.code);
      }
    };

    // Put video listeners into place
    // 针对标准的浏览器
    if (navigator.getUserMedia) { // Standard
      navigator.getUserMedia(videoObj, function (stream) {
        video.src = window.URL.createObjectURL(stream);
        video.play();
        that.localStream = stream;
      }, errBack);
    } else if (navigator.webkitGetUserMedia) { // WebKit-prefixed
      navigator.webkitGetUserMedia(videoObj, function (stream) {
        video.src = window.webkitURL.createObjectURL(stream);
        video.play();
        that.localStream = stream;
      }, errBack);
    } else if (navigator.msGetUserMedia) {
      //future implementation over internet explorer
      console.log("is")
    }
    else {
//          video.src = stream;
      console.log("other")
    }
    // 对拍照按钮的事件监听
    document.getElementById("snap").addEventListener("click", function () {
      // 画到画布上
      context.drawImage(video, 0, 0, canvas.width, canvas.height);
    });
  }

  function closeCam(that) {
    if (that.localStream) {
      if (!that.localStream.stop && that.localStream.getTracks) {
        that.localStream.stop = function () {
          this.getTracks().forEach(function (track) {
            track.stop();
          });
        };
      }
      that.localStream.stop();
    }
  }
</script>
<style>
  #canvas {
    background: url("../../assets/nopic.jpg");
    background-repeat: no-repeat;
    background-size: 300px 300px;
  }

</style>
