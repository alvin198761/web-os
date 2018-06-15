/**
 * Created by tangzhichao on 2017/2/27.
 */
/**
 * Created by tangzhichao on 2017/2/27.
 */
import {Notification} from 'element-ui';
export default {
  state: {
    zIndex: 999999,
    browsers: [],
    browserData: {},
    currentBrowser: null,
  },
  getters: {
    ['browser/_zIndex'](state){
      return state.zIndex;
    },
    ['browser/currentBrowserId'](state){
      return state.currentBrowser;
    }
  },
  mutations: {
    ['browser/zIndexAdd'](state, payload){
      state.zIndex++;
    },
    ['browser/initComponent'](state, payload){
      //添加浏览器元素
      state.browsers.push(payload);
      state.currentBrowser = payload;
    },
    ['browser/minActiveChange'](state, payload){
      let index = state.browsers.indexOf(payload.id);
      state.browsers.splice(index, 1);
      state.browsers.push(payload.id);
      //激活下一个窗口
      let obj = $(state.browsers[0]);
      $('.window-container').removeClass('window-current');
      obj.addClass('window-current').css({
        'z-index': state.zIndex + 1
      });
      state.zIndex++;
      state.currentBrowser = state.browsers[0];
    },
    ['browser/clickActiveChange'](state, payload){
      if (state.browsers.length === 1) {
        return;
      }
      let index = state.browsers.indexOf(payload.id);
      state.browsers.splice(index, 1);
      state.browsers.splice(0, 0, payload.id);
      //激活下一个窗口
      let obj = $(state.browsers[0]);
      $('.window-container').removeClass('window-current');
      obj.addClass('window-current').css({
        'z-index': state.zIndex + 1
      });
      state.zIndex++;
      state.currentBrowser = state.browsers[0];
    },
    ['browser/showBrowser'](state, payload){
      let obj = $(payload.id);
      obj.show();
    }
  },
  actions: {
    ['browser/remove']({commit}, payload){
      commit('taskbar/removeTask', payload);
    }
  }
};
