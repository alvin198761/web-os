/**
 * Created by tangzhichao on 2017/2/27.
 */
export default {
  state: {
    apps: []
  },
  getters: {

    ['gui/_apps'](state){
      return state.apps;
    }
  },
  mutations: {
    ['app/init'](state, payload){
      state.user = {
        name: 'alvin'
      }
    }
  },
  actions: {
    ['app/refresh']({commit}, payload){

    }
  }
};
