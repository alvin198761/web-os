<template>
  <div style="width: 740px; height: 500px; " class="window-container window-current"
       :id="id">
    <div style="height: 100%;">
      <div @dblclick="haDblClick" class="title-bar" @mousedown="haMouseDown">{{title}}
        <div class="title-handle">
          <a v-if="min" @click="haMin" class="ha-min" href="javascript:void(0);">最小化</a>
          <a v-if="max" @click="haMax" class="ha-max" href="javascript:void(0);">最大化</a>
          <a @click="haRevert" class="ha-revert" href="javascript:void(0);"
             style="display:none">还原</a>
          <a @click="handleClose" class="ha-close" href="javascript:void(0);" title="关闭">关闭</a>
        </div>
      </div>
      <div class="window-frame">
        <div style="z-index:9000000;background:none;height:100%;position:absolute;width:100%;filter:alpha(opacity=0);
          -moz-opacity:0;opacity:0;display:none"></div>
        <slot name="content"></slot>
      </div>
      <div class="set-bar">
      </div>
      <div
        style="position:absolute;overflow:hidden;display:block"
        resize="min_width"></div>
      <div
        style="position:absolute;overflow:hidden;display:block"
        resize="min_height"></div>
    </div>
  </div>
</template>
<script>
  import {Notification} from 'element-ui';
  import {APP_TYPE} from '../../constant';
  export default{
    props: {
      min: {
        type: Boolean,
        default: true
      },
      max: {
        type: Boolean,
        default: true
      },
      revert: {
        type: Boolean,
        default: true
      },
      resize: {
        type: Boolean,
        default: false
      },
      title: {
        type: String,
      },
      id: {
        type: String
      },
      handleClose: {
        type: Function,
        required: true,
      }
    },
    data: function () {
      return {
        box: {
          width: 0,
          height: 0,
          top: 0,
          left: 0
        },
        position: {
          x: 0,
          y: 0,
          sT: 0,
          sL: 0
        },
        drag: false
      }
    },
    computed: {},
    mounted: function () {
      this.$store.commit('browser/initComponent', "#" + this.id);
      let obj = $("#" + this.id);
      obj.find('.window-container').removeClass('window-current');
      obj.addClass('window-current').css({
        'z-index': this.$store.state.browser.zIndex + 1,
        position: 'absolute',
        top: '100px',
        left: '288px'
      });
      this.$store.commit('browser/zIndexAdd');
      obj.draggable({containment: "#desktop"});
      if (this.resize) {
        obj.resizable();
      }
    },
    beforeDestroy: function () {
      $("#" + this.id).remove()
    },
    methods: {
      //最小化
      haMin: function (e) {
        e.stopPropagation();
        let obj = $('#' + this.id);
        obj.hide();
        //改变任务栏样式
        $('.task-window li[window="' + obj.attr('window') + '"] b').removeClass('focus');
        this.$store.commit('browser/minActiveChange', {id: '#' + this.id});
      },
      // 最大化
      haMax: function (e) {
        e.stopPropagation();
        let obj = $('#' + this.id);
        this.box = {
          width: obj[0].offsetWidth,
          height: obj[0].offsetHeight,
          top: obj[0].offsetTop,
          left: obj[0].offsetLeft
        }
        obj.css({
          width: window.innerWidth,
          height: window.innerHeight,
          top: 0,
          left: 0,
          position: 'absolute'
        });
        obj.find(".ha-max").hide().next(".ha-revert").show();
      },
      // 还原
      haRevert: function (e) {
        e.stopPropagation();
        let obj = $('#' + this.id)
        obj.css({
          width: this.box.width + "px",
          height: this.box.height + "px",
          left: this.box.left + "px",
          top: this.box.top + "px"
        });
        obj.find('.ha-revert').hide().prev(".ha-max").show();
      },
      // 双击
      haDblClick: function (e) {
        if (!this.resize) {
          return;
        }
        e.stopPropagation();
        //判断当前窗口是否已经是最大化
        let obj = $("#" + this.id)
        if (obj.find(".ha-max").is(":visible")) {
          this.haMax(e)
        } else {
          this.haRevert(e)
        }
        this.haMouseDown(e)
      },
      haMouseDown: function (e) {
        this.$store.commit('browser/clickActiveChange', {id: '#' + this.id})
      }
    },
    components: {}
  }
</script>
<style>


</style>
