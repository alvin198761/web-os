<template>
  <div class="notes" :id="'note_'+userObject.id">
    <div class="infotitl">{{userObject.title}}</div>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex';
  export default{
    props: ['userObject', 'options', 'index'],
    data: function () {
      return {}
    },
    computed: {
      ...mapGetters({}),
      _nodeId: function () {
        return 'note_' + this.userObject.id;
      }
    },
    mounted: function () {
      let _this = this;
      $("#" + this._nodeId).draggable({containment: "#desktop"});
      $("#" + this._nodeId).click(function () {
        var zIndex = _this.$store.state.notes.zIndex;
        $("#" + this._nodeId).css({"z-index": zIndex + 1});
        _this.$store.commit('note/zIndex');
      });
    },
    beforeDestroy: function () {
      let obj = $("#" + this._nodeId).remove()
    }
  }
</script>
<style>

  .notes {
    width: 161px;
    height: 164px;;
    position: absolute;
    z-index: 81;
    top: 200px;
    right: 50px;
    background-image: url(../../assets/images/memo_background.png);
  }

  .infotitl {
    width: 100%;
    height: 20px;
    line-height: 20px;
    font-size: 12px;
    text-align: center;
  }
</style>
