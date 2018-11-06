<template>
    <div id="desktop" class="desktop">
      <div id="win10-shortcuts" class="shortcuts-hidden">
        <div class="shortcut" v-for="app in apps" :key="app.id" :title="app.tip"
             @click="openApp(app)">
          <img class="icon" :src="app.icon"/>
          <div class="title">{{app.title}}</div>
        </div>
      </div>
      <div id="win10-desktop-scene"></div>
    </div>
</template>

<script>
  import {mapGetters} from 'vuex';
  export default{
    data: function () {
      return {}
    },
    computed: {
      ...mapGetters({
        apps: 'app/_getDesktopApps'
      })
    },
    created: function () {

    },
    updated(){
      Win10.renderShortcuts();
    },
    methods: {
      openApp(app){
        Win10.openUrl(app.route_url, "<img class='icon' src=" + app.icon + " />" + app.title)
      }
    },
    components: {}
  }
</script>
<style>

</style>
