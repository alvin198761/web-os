<template>
  <div>
    <div class='bottomBarBg'>
      <div class="bottomBarBgTask"></div>
    </div>
    <div class='bottomBarBgTask'></div>
    <div id="bottomBar" class="bottomBar" style="z-index: 12;">
      <div id="taskNextBox" class="taskNextBox" _olddisplay="" style="display: none;"><a id="taskNext" class="taskNext"
                                                                                         hidefocus="true" href="#"></a>
      </div>
      <div id="taskContainer" class="taskContainer" style="margin-left: 100px;width: 90%;">
        <div id="taskContainerInner" class="taskContainerInner" style="margin-right: 0px; width: 90%;">
          <div v-for="t in tasks" @click="activeTask(t)" class="taskGroup taskGroupAnaWidth"
               :class="'#'+t.el_id === currentBrowserId ? 'taskCurrent':''"
               :id="'taskGroup_'+t.el_id">
            <div class="taskItemBox">
              <a class="taskItem fistTaskItem" href="#" :id="'taskItem_'+t.el_id"
                 :title="t.el_id"
                 :tid="t.el_id" :appid="t.el_id.id+'_'+t.el_id">
                <div class="taskItemIcon"><img :src="t.task.icon"/>
                  <div class="taskItemIconState"></div>
                </div>
                <div class="taskItemTxt">{{t.task.title}}</div>
              </a></div>
          </div>
        </div>
      </div>
      <div id="taskPreBox" class="taskPreBox" _olddisplay="" style="display: none;"><a id="taskPre" class="taskPre"
                                                                                       hidefocus="true" href="#"></a>
      </div>
    </div>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex';
  export default{
    data: function () {
      return {}
    },
    computed: {
      ...mapGetters({
        tasks: 'taskbar/_tasks',
        currentBrowserId: 'browser/currentBrowserId'
      })
    },
    mounted: function () {
      this.$store.commit('taskbar/initComponent')
    },
    methods: {
      activeTask: function (t) {
        this.$store.dispatch('taskbar/activeTask', t.el_id)
      }
    }
  }
</script>
<style>


</style>
