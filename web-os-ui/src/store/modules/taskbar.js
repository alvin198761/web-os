/**
 * Created by tangzhichao on 2017/2/27.
 */
import AppDialog from '../../components/commons/AppDialog.vue';
import Browser from '../../components/commons/Browser.vue';
import RdpManagerDialog from '../../components/setting/rdp/RdpManagerDialog.vue';
import SSHManagerDialog from '../../components/setting/ssh/SSHManagerDialog.vue';
import VncManagerDialog from '../../components/setting/vnc/VncManagerDialog.vue';
import {componentIndexOf} from '../../constant';
export default {
  state: {
    el: null,
    contextMenuData: [[
      {
        text: "关闭窗口",
        func: function () {
        }
      },
      {
        text: "工具栏"
      }, {
        text: "任务管理器"
      }, {
        text: "显示桌面"
      }], [{
      text: "属性"
    }]],
    tasks: []
  },
  getters: {
    ['taskbar/_tasks'](state){
      return state.tasks;
    }
  },
  mutations: {
    ['taskbar/initComponent'](state){
      state.el = $('#bottomBar')
    },
    ['taskbar/taskMenu'](state){
      state.el.smartMenu(state.contextMenuData, {
        name: "task_bar",
      });
    },
    ['taskbar/removeTask'](state, payload){
      let index = state.tasks.indexOf(payload.task);
      state.tasks.splice(index, 1);
    },
    ['taskbar/addTask'](state, payload){
      state.tasks.push({
        el_id: payload.id,
        task: payload.task
      })
    }
  },
  actions: {
    ['taskbar/addAppDialog']({rootState, commit, dispatch}, payload){
      let id = 'dialog_' + payload.id + '_box';
      if (componentIndexOf(rootState.taskbar.tasks, id) === -1) {
        commit('desktop/addComponent', {
          component: AppDialog,
          options: {},
          userObject: payload
        })
        commit('taskbar/addTask', {
          id: id,
          task: payload
        })
        return;
      }
      dispatch('taskbar/activeTask', id)
    },
    ['taskbar/addBrowser']({rootState, commit, dispatch}, payload){
      let id = 'browser_' + payload.id + '_box'
      if (componentIndexOf(rootState.taskbar.tasks, id) === -1) {
        commit('desktop/addComponent', {
          component: Browser,
          options: {},
          userObject: payload
        })
        commit('taskbar/addTask', {
          id: id,
          task: payload
        })
        return;
      }
      dispatch('taskbar/activeTask', id)
    },
    ['taskbar/activeTask']({commit}, payload){
      commit('browser/showBrowser', {id: '#' + payload});
      commit('browser/clickActiveChange', {id: '#' + payload})
    },
    ['taskbar/open_rdp']({rootState, commit, dispatch}, payload){
      if (rootState.taskbar.tasks.indexOf(payload.url) === -1) {
        commit('desktop/addComponent', {
          component: RdpManagerDialog,
          options: {},
          userObject: payload
        })
        commit('taskbar/addTask', {
          id: 'rdp',
          task: payload
        })
        return;
      }
      dispatch('taskbar/activeTask', payload.url)
    },
    ['taskbar/open_putty']({rootState, commit, dispatch}, payload){
      if (rootState.taskbar.tasks.indexOf(payload.url) === -1) {
        commit('desktop/addComponent', {
          component: SSHManagerDialog,
          options: {},
          userObject: payload
        })
        commit('taskbar/addTask', {
          id: 'ssh',
          task: payload
        })
        return;
      }
      dispatch('taskbar/activeTask', payload.url)
    },
    ['taskbar/open_vnc']({rootState, commit, dispatch}, payload){
      if (rootState.taskbar.tasks.indexOf(payload.url) === -1) {
        commit('desktop/addComponent', {
          component: VncManagerDialog,
          options: {},
          userObject: payload
        })
        commit('taskbar/addTask', {
          id: 'vnc',
          task: payload
        })
        return;
      }
      dispatch('taskbar/activeTask', payload.url)
    }

  }
};
