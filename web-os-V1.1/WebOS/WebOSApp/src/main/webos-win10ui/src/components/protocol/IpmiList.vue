<template>
  <div :id="getId">This is a vue 2.x page</div>
</template>
<script>

  export default{
    props: ["id"],
    data: function () {
      return {}
    },
    computed: {
      getId(){
        return 'rdp' + this.id
      }
    },
    created: function () {

    },
    methods: {

    },
    components: {}
  }
</script>
<style>

</style>
