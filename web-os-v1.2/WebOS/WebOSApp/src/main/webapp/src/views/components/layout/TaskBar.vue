<template>
  <div id="win10_task_bar">
    <div id="win10_btn_group_left" class="btn_group">
      <div id="win10_btn_win" class="btn"><span class="fa fa-windows"></span></div>
      <div class="btn" @click="showBrowser"><span class="fa fa-internet-explorer"></span></div>
    </div>
    <div id="win10_btn_group_middle" class="btn_group"></div>
    <div id="win10_btn_group_right" class="btn_group">
      <div class="btn" id="win10_btn_time">
        <!--0:00<br/>-->
        <!--1993/8/13-->
      </div>
      <div class="btn" id="win10_btn_command"><span id="win10-msg-nof" class="fa fa-comment-o"></span></div>
      <div class="btn" id="win10_btn_show_desktop"></div>
    </div>
  </div>
</template>
<script>

  export default{
    data: function () {
      return {}
    },
    computed: {},
    created: function () {

    },
    methods: {
      showBrowser(){
        Win10.openUrl("/browser", "浏览器")
      }
    },
    components: {}
  }
</script>
<style>

</style>
