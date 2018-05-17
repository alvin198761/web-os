<template>
  <li :id="id" :shortcut="app.id">
    <div class="app-box">
      <el-badge :value="app.msgCount" class="item">
        <img style="cursor: pointer;" @click="openApp(app)" :src="app.icon"><span>{{app.title}}</span> <em
        v-if="false"></em>
      </el-badge>
    </div>
  </li>
</template>
<script>
  export default {
    props: ['app', 'openApp'],
    data: function () {
      return {
        icon: null
      }
    },
    created: function () {
    },
    computed: {
      id: function () {
        return 'app_button_' + this.app.id;
      }
    },
    mounted: function () {
      $("#" + this.id).sortable();
    }
  }
</script>
<style>

</style>
