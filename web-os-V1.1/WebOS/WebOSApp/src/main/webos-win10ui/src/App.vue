<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  import RootPane from './components/RootPane.vue';

  export default {
    name: 'App',
    components: {RootPane},
    mounted(){
      this.$store.dispatch("app/initApp");
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    /*text-align: center;*/
    /*color: #2c3e50;*/
    /*margin-top: 60px;*/
    width: 100%;
    height: 100%;
  }
</style>
