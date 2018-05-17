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
      console.log(state)
      return state.menus;
    }
  },
  mutations: {
    ['fisheye/initComponent'](state){
      state.el = $('#dock');
    },
    ['fisheye/fetch'](state, store){
      http.get('/api/appIcons/fisheye').then(function (res) {
        let data = res.data;
        for (let i in data) {
          console.log(data[i])
          data[i].icon = require('../../assets/images/' + data[i].icon)
          let apps = data[i].apps;
          for (let j in apps) {
            data[i].apps[j].icon = require('../../assets/icon/' + apps[j].icon)
          }
        }
        console.log(data)
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
