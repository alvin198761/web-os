/**
 * Created by tangzhichao on 2017/2/27.
 */
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
    },
    //删除所有窗体
    ["task/clean"](state,payload){
      state.tasks = [];
    }
  },
  actions: {
    //显示一个应用
    ['taskbar/addAppDialog']({rootState, commit, dispatch}, payload){
      let id = 'dialog_' + payload.id + '_box';
      if (componentIndexOf(rootState.taskbar.tasks, id) === -1) {
        commit('desktop/addComponent', {
          component: require('../../components/commons/AppDialog.vue'),
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
          component: require('../../components/commons/Browser.vue'),
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
          component: require('../../components/setting/rdp/RdpManagerDialog.vue'),
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
          component: require( '../../components/setting/ssh/SSHManagerDialog.vue'),
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
          component: require('../../components/setting/vnc/VncManagerDialog.vue'),
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
          component: require( '../../components/setting/appicons/AppIconsDialog.vue'),
          options: {},
          userObject: { ...payload ,parent_id : rootState.contentpane.currentMenu.id}
        })
        commit('taskbar/addTask', {
          id: payload.route_url,
          task: payload
        })
      }
      dispatch('taskbar/activeTask', payload.route_url)
    },
    //打开添加应用的窗口
    ["taskbar/addAppIconDir"]({rootState,commit,dispatch},payload){
      if (rootState.taskbar.tasks.indexOf(payload.route_url) === -1) {
        commit('desktop/addComponent', {
          component: require('../../components/setting/appicons/AppIconsDirDialog.vue'),
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
