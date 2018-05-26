/**
 * Created by tangzhichao on 2017/2/27.
 */
import AppDialog from '../../components/commons/AppDialog.vue';
import Browser from '../../components/commons/Browser.vue';
import RdpManagerDialog from '../../components/setting/rdp/RdpManagerDialog.vue';
import SSHManagerDialog from '../../components/setting/ssh/SSHManagerDialog.vue';
import VncManagerDialog from '../../components/setting/vnc/VncManagerDialog.vue';
import AppIconsDialog from '../../components/setting/appicons/AppIconsDialog.vue';

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
    //初始化应用
    ['taskbar/initComponent'](state){
      state.el = $('#bottomBar')
    },
    //任务栏右键菜单
    ['taskbar/taskMenu'](state){
      state.el.smartMenu(state.contextMenuData, {
        name: "task_bar",
      });
    },
    //删除一个窗体
    ['taskbar/removeTask'](state, payload){
      let index = state.tasks.indexOf(payload.task);
      state.tasks.splice(index, 1);
    },
    //添加一个窗体
    ['taskbar/addTask'](state, payload){
      state.tasks.push({
        el_id: payload.id,
        task: payload.task
      })
    }
  },
  actions: {
    //显示一个应用
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
    //显示一个浏览器
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
    //激活一个应该用
    ['taskbar/activeTask']({commit}, payload){
      commit('browser/showBrowser', {id: '#' + payload});
      commit('browser/clickActiveChange', {id: '#' + payload})
    },
    //打开rdp窗口
    ['taskbar/open_rdp']({rootState, commit, dispatch}, payload){
      if (rootState.taskbar.tasks.indexOf(payload.route_url) === -1) {
        commit('desktop/addComponent', {
          component: RdpManagerDialog,
          options: {},
          userObject: payload
        })
        commit('taskbar/addTask', {
          id: 'rdp',
          task: payload
        })
      }
      dispatch('taskbar/activeTask', payload.route_url)
    },
    //打开putty 窗口
    ['taskbar/open_putty']({rootState, commit, dispatch}, payload){
      if (rootState.taskbar.tasks.indexOf(payload.route_url) === -1) {
        commit('desktop/addComponent', {
          component: SSHManagerDialog,
          options: {},
          userObject: payload
        })
        commit('taskbar/addTask', {
          id: 'ssh',
          task: payload
        })
      }
      dispatch('taskbar/activeTask', payload.route_url)
    },
    //打开vnc窗口
    ['taskbar/open_vnc']({rootState, commit, dispatch}, payload){
      if (rootState.taskbar.tasks.indexOf(payload.route_url) === -1) {
        commit('desktop/addComponent', {
          component: VncManagerDialog,
          options: {},
          userObject: payload
        })
        commit('taskbar/addTask', {
          id: 'vnc',
          task: payload
        })
      }
      dispatch('taskbar/activeTask', payload.route_url)
    },
    //打开添加应用的窗口
    ["taskbar/addAppIcon"]({rootState,commit,dispatch},payload){
      if (rootState.taskbar.tasks.indexOf(payload.route_url) === -1) {
        commit('desktop/addComponent', {
          component: AppIconsDialog,
          options: {},
          userObject: { ...payload ,parent_id : rootState.contentpane.currentMenu.id}
        })
        commit('taskbar/addTask', {
          id: payload.route_url,
          task: payload
        })
      }
      dispatch('taskbar/activeTask', payload.route_url)
    }

  }
};
