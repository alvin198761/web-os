<template>
  <Explorer :id="_browserId" :title="userObject.title" :resize="true" :handleClose="handleClose">
    <iframe slot="content" frameborder="0" :src="userObject.url"></iframe>
  </Explorer>
</template>
<script>
  import {mapGetters} from 'vuex';
  import Explorer from './Explorer.vue';
  export default{
    props: ['userObject', 'options', 'index'],
    data: function () {
      return {}
    },
    computed: {
      ...mapGetters({}),
      _browserId: function () {
        return 'browser_' + this.userObject.id + '_box'
      }
    },
    methods: {
      handleClose: function (e) {
        const _this = this;
        this.$store.dispatch('desktop/removeComponent', {
          task: _this.userObject,
          index: _this.index
        })
        this.$store.commit('taskbar/removeTask',this.userObject)
      }
    },
    components: {
      Explorer
    }
  }
</script>
<style>


</style>
