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
      http.get('/api/appIcons/fisheye').then(function (res) {
        let data = res.data;
        res.data.map(app => {
          app.icon = require('../../assets/images/' + app.icon);
        })
        state.menus = data;
        if (state.menus.length > 0) {
          store.commit('content/changeCard', {item: 0})
        }
      }).catch(function (res) {
        state.menus = [];
      });
    },
    ["fisheye/refreshCurrent"](state,payload){
       http.post("/api/appIcons/queryList",JSON.stringify({
         parent_id:payload
       })).then(res => {
         const menus = [];
          for(let i = 0 ; i < state.menus.length ; i++){
            if(state.menus[i].id == payload){
                state.menus[i].apps = res.data;
            }
            menus.push(state.menus[i])
          }
          state.menus = menus;
       }).catch(error => {

       })
    }
  },
  actions: {}
};
