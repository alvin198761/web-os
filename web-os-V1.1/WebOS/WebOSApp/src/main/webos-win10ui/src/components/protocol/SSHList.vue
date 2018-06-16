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
      showDialog(){
        const that = this;
        layer.open({
          title: '111111111',
          type: 1,
          area: '300px',
          offset: '50px',
          shadeClose: true,
          content: '<div>12312312</div>'
        });
      }
    },
    components: {}
  }
</script>
<style>

</style>
