<template>
  <div :id="getId">未实现</div>
</template>
<script>
//  import {getClient, closeClient, list} from '../../client/Ssh2SFtpClient';
  export default{
    props: ["id"],
    data: function () {
      return {}
    },
    computed: {
      getId(){
        return 'sftp' + this.id
      }
    },
    created: function () {

    },
    methods: {},
    components: {}
  }
</script>
<style>

</style>
