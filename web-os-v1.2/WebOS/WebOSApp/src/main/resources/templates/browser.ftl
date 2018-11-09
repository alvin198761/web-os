<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="description" content="WebOs Browser">
  <meta name="keywords" content="WebOs">
  <!-- 引入样式 -->
  <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
  <!-- 引入组件库 -->
  <style>
    body, html, #app {
      margin: 0;
      width: 100%;
      height: 100%;
    }
  </style>
</head>
<body>
<div id="app">
  <el-input placeholder="请输入网址" v-model="url" style="border-radius: 0px !important ;margin: 0; padding: 0px "
            @keyup.stop.enter.native="refresh">
    <template slot="append">
      <el-button icon="el-icon-refresh" title="刷新" @click="refresh"></el-button>
    </template>
  </el-input>
  <iframe frameborder="0" style="width: 100% ; height: 100%; margin-top: 5px" ref="iframe"
          sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-top-navigation"></iframe>
</div>
</body>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
  new Vue({
    el: '#app',
    data: function () {
      return {url: 'http://www.baidu.com'}
    },
    mounted(){
        this.refresh();
    },
    methods: {
      refresh(){
        this.$refs["iframe"].src = this.url;
      }
    }
  })
</script>
</html>
