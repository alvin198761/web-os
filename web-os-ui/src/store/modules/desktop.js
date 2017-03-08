/**
 * Created by tangzhichao on 2017/2/27.
 */
import {Message} from 'element-ui';
import JSComponent from '../bean/JSComponent';

import ClockTool from '../../components/tools/ClockTool.vue';
export default {
  state: {
    el: null,
    components: [],
    contentMenuData: [
      [{
        text: "显示桌面",
        func: function () {
          // Windows.showWindowDesk();
        }
      }, {
        text: "关闭所有",
        func: function () {
          // Windows.closeAllWindow();
        }
      }, {
        text: "锁屏",
        func: function () {

        }
      }], [{
        text: "系统设置",
        func: function () {

        }
      }, {
        text: "主题设置",
        func: function () {
          // Windows.openSys({
          //   id: 'themSetting',
          //   title: '设置主题',
          //   width: 650,
          //   height: 500,
          //   content: document.getElementById("themeSetting_wrap")
          // });
        }
      },
        {
          text: "图标设置",
          data: [[{
            text: "大图标",
            func: function () {
              // Deskpanel.desktopsContainer.removeClass("desktopSmallIcon");
            }
          }, {
            text: "小图标",
            func: function () {
              // Deskpanel.desktopsContainer.addClass("desktopSmallIcon");
            }
          }]]
        }],
      [{
        text: "注销",
        func: function () {

        }
      }]
    ],

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
        window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
      }

      function up(evt) {
        $(document).unbind('mousemove', move).unbind('mouseup', up);
      }

      $(document).bind('mousedown', function () {
        $(document).bind('mousemove', move).bind('mouseup', up);
      });
    },
    ['desktop/mainMenu'](state)    {
      $('#contentPane').smartMenu(state.contentMenuData, {name: "image"});
    },
    ['desktop/addComponent'](state, payload){
      state.components.push(new JSComponent(payload.component, payload.options, payload.userObject))
    },
    ['desktop/removeComponent'](state, payload){
      state.components.splice(payload, 1);
    },
  },
  actions: {
    ['desktop/init']({commit}){
      commit('desktop/initEvent');
      commit('desktop/bindEvent');
      commit('desktop/initComponent');
    },
    ['desktop/initContextMenu']({commit}){
      commit('desktop/mainMenu');
      commit('taskbar/taskMenu');
    },
    ['desktop/removeComponent']({commit}, payload){
      commit('desktop/removeComponent', payload.index)
    },
    ['desktop/initChildren']({commit, dispatch}){
      dispatch('note/initComponent')
      commit('desktop/addComponent', {
        component: ClockTool,
        options: {},
        userObjec: {}
      });
    }
  }
};
