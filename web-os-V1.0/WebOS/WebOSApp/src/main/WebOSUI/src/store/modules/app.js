/**
 * Created by tangzhichao on 2017/2/21.
 */
export default {
  state: {
    version: '1.0',
    mode: 'dev',
    user: null
  },
  getters: {
  },
  mutations: {
    ['app/init'](state){
      state.user = {
        name: 'alvin'
      }
    }
  },
  actions: {

  }
};
