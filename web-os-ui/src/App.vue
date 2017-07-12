<template>
  <div>
    <Desktop></Desktop>
    <ContextMenu></ContextMenu>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import Desktop from './components/layout/Desktop.vue';
  import ContextMenu from './components/layout/ContextMenu.vue';
  export default {
    name: 'app',
    data: function () {
      return {}
    },
    computed: {
      ...mapGetters({}),
    },
    mounted: function () {
      this.$store.commit("app/init");
    },
    components: {Desktop, ContextMenu}
  }
</script>

<style lang="less" rel="stylesheet/less">
  body {
    margin: 0;
    background-color: #f9f9f9;
  }

  #app {
    font-family: 'Microsoft YaHei', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    font-size: 14px;
  }

  input, textarea, keygen, select, button, body {
    font-family: 'Microsoft YaHei', Helvetica, Arial, sans-serif;
  }

  input {
    // 消除IE浏览器下input自带的清空符号
    &::-ms-clear {
      display: none;
    }
  }

  .el-pagination--small .el-pager li {
    margin-left: 10px;
    &:last-child {
      margin-right: 10px;
    }
  }

  img {
    border: none;
  }

  a {
    color: #45649E;
    font: 12px/1.5 tahoma, helvetica, clean, sans-serif;
  }

  :link, *:visited {
    text-decoration: none;
  }

  a:hover {
    text-decoration: none;
  }

  a:focus {
    outline-style: none;
  }
</style>
