<template>
  <img id="inputMethodBox" src="../../assets/images/input.png"/>
</template>
<script>
  import {mapGetters} from 'vuex';
  export default {
    computed: {
      ...mapGetters({
        show: 'desktop/inputMethod'
      })
    },
    data: function () {
      return {}
    },
    created: function () {

    },
    methods: {},
    mounted: function () {
      $('#inputMethodBox').draggable()
    }

  }
</script>
<style>
  #inputMethodBox {
    position: absolute;
    z-index: 60;
    bottom: 120px;
    right: 20px;
    cursor: pointer;
  }
</style>
