/**
 * Created by tangzhichao on 2017/2/27.
 */
import {Message} from 'element-ui';
import JSComponent from '../bean/JSComponent';
import {desktopContextMenu} from '../../constant';
export default {
  state: {
    el: null,
    components: [],
    contentMenuData: [],

  },
  getters: {
    ['desktop/_components'](state){
      return state.components;
    }
  },
  mutations: {
    ['desktop/initComponent'](state){
      state.el = $('#desktop');
    },
    ['desktop/initEvent'](state){
      document.oncontextmenu = function () {//屏蔽浏览器右键事件
        return false;
      };
      $(document).bind('mousemove', function (e) {
        var area = $(window).width() - 50;
        if (e.pageX > area) {
          e.pageX = area;
        }
      });
      $(window).bind('resize', function () {
        if ($(window).width() < 800 || $(window).height() < 400) {
          if (state.alert === false) {
            Message.info({
              message: "浏览器当前窗口过小，可能会影响正常操作！"
            });
          }
          state.alert = true;
        } else {
          state.alert = false;
        }

      })
    },
    ['desktop/bindEvent'](state)    {
      function move(evt) {
        // window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
      }

      function up(evt) {
        $(document).unbind('mousemove', move).unbind('mouseup', up);
      }

      $(document).bind('mousedown', function () {
        $(document).bind('mousemove', move).bind('mouseup', up);
      });
    },
    ['desktop/mainMenu'](state, payload)    {
      state.contentMenuData = payload;
      $('#contentPane').smartMenu(state.contentMenuData, {name: "image"});
    },
    ['desktop/addComponent'](state, payload){
      state.components.push(new JSComponent(payload.component, payload.options, payload.userObject))
    },
    ['desktop/removeComponent'](state, payload){
      state.components.splice(payload, 1);
    },
    ['desktop/setDesktopContextMenus'](state, payload){
      state.contentMenuData = payload;
    },
    ['desktop/clean'](state){
      state.components = [];
    }
  },
  actions: {
    ['desktop/init']({commit}){
      commit('desktop/initEvent');
      commit('desktop/bindEvent');
      commit('desktop/initComponent');
    },
    ['desktop/initContextMenu']({dispatch, commit}){
      commit('desktop/mainMenu', desktopContextMenu(dispatch, commit));
      commit('taskbar/taskMenu');
    },
    ['desktop/removeComponent']({commit}, payload){
      commit('desktop/removeComponent', payload.index)
    },
    ['desktop/initChildren']({commit, dispatch}){
      dispatch('note/initComponent')
      commit('desktop/addComponent', {
        component: require('../../components/tools/ClockTool.vue'),
        options: {},
        userObjec: {}
      });
    }
  }
};
