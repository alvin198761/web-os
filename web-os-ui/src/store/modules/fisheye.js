/**
 * Created by tangzhichao on 2017/2/27.
 */
import http from '../../plugins/http';
export default {
  state: {
    menus: [],
    el: null
  },
  getters: {
    ['fisheye/_menus'](state){
      return state.menus;
    }
  },
  mutations: {
    ['fisheye/initComponent'](state){
      state.el = $('#dock');
    },
    ['fisheye/fetch'](state, store){
      http.get('/api/fisheye/apps').then(function (res) {
        let data = res.data;
        for (let i in data) {
          data[i].icon = require('../../assets/images/' + data[i].icon)
          let apps = data[i].apps;
          for (let j in apps) {
            data[i].apps[j].icon = require('../../assets/icon/' + apps[j].icon)
          }
        }
        state.menus = data;
        if (state.menus.length > 0) {
          store.commit('content/changeCard', {item: 0})
        }
      }).catch(function (res) {
        state.menus = [];
      });
    }
  },
  actions: {}
};
