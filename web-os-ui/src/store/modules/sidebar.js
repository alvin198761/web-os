/**
 * Created by tangzhichao on 2017/2/27.
 */
import http from '../../plugins/http';
export default {
  state: {
    el: null,
    apps: []
  },
  getters: {
    ['sidebar/_apps'](state){
      return state.apps;
    }
  },
  mutations: {
    ['sidebar/initComponent'](state){
      state.el = $('#leftBar');
    },
    ['sidebar/fetch'](state){
      http.get('/api/sidebar/apps').then(function (res) {
        let data = res.data;
        for (let i in data) {
          data[i].icon = require('../../assets/icon/sidebar/' + data[i].icon)
        }
        state.apps = data;
      }).catch(function (res) {
        state.apps = [];
      })
    }
  },
  actions: {}
};
