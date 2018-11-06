<template>
  <div class="shortcut" :title="app.tip"
       onclick="openApp">
    <img class="icon" :src="app.icon"/>
    <div class="title">{{app.title}}</div>
  </div>
</template>
<script>

  export default{
    props: ['app'],
    data: function () {
      return {}
    },
    computed: {},
    created: function () {

    },
    methods: {
      openApp(){
        Win10.openUrl(this.app.route_url, '<img class=\'icon\' src=\'' + this.app.icon + '\'/>' + this.app.title)
      }
    },
    components: {}
  }
</script>
<style>

</style>
