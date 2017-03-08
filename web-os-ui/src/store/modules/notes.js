/**
 * Created by tangzhichao on 2017/2/27.
 */
import http from '../../plugins/http';
import Notes from '../../components/commons/Notes.vue';
export default {
  state: {
    notes: [],
    zIndex: 999
  },
  getters: {
    ['note/_notes'](state){
      return state.notes;
    }
  },
  mutations: {
    ['note/zIndex'](state){
      state.zIndex++;
    },
    ['note/initNotes'](state, payload){
      state.notes = payload
    }
  },
  actions: {
    ['note/initComponent']({commit}){
      http.get('/api/note').then(function (res) {
        commit('note/initNotes', res.data);
        res.data.map(function (item) {
          commit('desktop/addComponent', {
            component: Notes,
            options: {},
            userObject: item
          })
        })
      }).catch(function (res) {
        state.nodes = [];
      })
    }
  }
};
