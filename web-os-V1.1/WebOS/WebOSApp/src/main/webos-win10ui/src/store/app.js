import {dsy} from '../utils/dsy.js';
import {nat} from '../utils/nat.js';

import {heartbeat, fetchInfo} from '../service/SessionService';
import {queryList} from '../service/AppIconsService';

export default {
  state: {
    version: '1.0',
    mode: 'prod',
    user: {
      name: 'alvin',
      theme: 0
    },//登录用户,
    desktopApps: []
  },
  getters: {
    ["app/_getNats"](state){
      var natArray = [];
      nat.map(item => {
        natArray.push({
          label: item.nat_name + " " + item.short_code + " " + item.long_code + " " + item.continent_code,
          value: item.id
        });
      });
      return natArray;
    },
    ["app/_getZones"](state){
      return dsy;
    },
    ["app/_getUser"](state){
      return state.user;
    },
    ['app/_getTheme'](state){
      return state.user.theme;
    },
    ["app/_getDesktopApps"](state){
      return state.desktopApps;
    }
  },
  mutations: {
    ['app/initUser'](state){
      //后台获得用户信息
      fetchInfo().then(res => state.user = res.data);
    },
    ['app/heartbeat'](state){
      //定时心跳监测
      setInterval(heartbeat, 1000 * 60 * 10)
    },
    ['app/initDesktopApp'](state){
      queryList({}).then(res => state.desktopApps = res.data);
    }
  },
  actions: {
    ["app/initApp"]({state, dispatch, commit}){
      //初始化用户信息
      commit("app/initUser");
      //初始化控件信息
      dispatch("app/initUI");
      //发送心跳数据
      commit("app/heartbeat");
    },
    ["app/initUI"]({state, dispatch, commit}){
      // 初始化桌面主题
      // 获取桌面图标列表
      commit("app/initDesktopApp")
      // 初始化任务栏图标
      // 初始化开始菜单
      // 初始化邮件菜单
      // 获取动态消息
      // 弹出新闻等
    }
  }
};
