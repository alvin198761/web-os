<template>
  <div></div>
</template>
<script>
  export default {

    data: function () {
      return {}
    },
    created: function () {

    },
    methods: {},
    mounted: function () {
      this.$store.dispatch("desktop/initContextMenu");
    }

  }
</script>
<style>

</style>
