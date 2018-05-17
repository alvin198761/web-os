<template>
  <div id="desktop">
    <RootPanel></RootPanel>
    <TaskBar></TaskBar>
    <keep-alive v-for="(comp,index) in _components">
      <component :is="comp.component" :options="comp.options" :index="index" :userObject="comp.userObject"></component>
    </keep-alive>
  </div>
</template>
<script>
  import TaskBar from './TaskBar.vue';
  import RootPanel from './RootPanel.vue';
  import {mapGetters} from 'vuex';

  export default{
    data: function () {
      return {}
    },
    computed: {
      ...mapGetters({
        _components: 'desktop/_components'
      })
    },
    components: {
      TaskBar, RootPanel
    },
    mounted: function () {
      this.$store.dispatch("desktop/init");
    },
    created: function () {
      this.$store.dispatch('desktop/initChildren')
    }
  }
</script>
<style>
  #desktop {
    display: block;
    position: fixed;
    top: 0px;
    left: 0px;
    right: 0px;
    bottom: 0px;
    z-index: -100;
    background: url("../../assets/bg-1.jpg") repeat right bottom transparent;

    background-size: 100%;
    overflow: hidden;
  }

</style>
