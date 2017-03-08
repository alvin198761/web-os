<template>
  <div id="rootPanel">
    <FishEyeMenu></FishEyeMenu>
    <ContentPanel></ContentPanel>
    <Sidebar></Sidebar>
  </div>
</template>
<script>
  import Sidebar from './Sidebar.vue';
  import {mapGetters} from 'vuex';
  import FishEyeMenu from './FishEyeMenu.vue';
  import ContentPanel from './ContentPanel.vue';
  export default{
    data: function () {
      return {}
    },
    computed: {
      ...mapGetters({})
    },
    components: {
      Sidebar, FishEyeMenu, ContentPanel
    }
  }
</script>
<style>
</style>
