/**
 * Created by tangzhichao on 2017/2/27.
 */
export default {
  state: {
    el: null,
    currentCard: 0,
    cardPane: null
  },
  getters: {},
  mutations: {
    ['content/initComponent'](state, payload){
      state.el = $('#contentPane');
      state.cardPane = payload;
    },
    ['content/changeCard'](state, payload){
      state.currentCard = payload.item;
      if (state.cardPane) {
        state.cardPane.setActiveItem(state.currentCard)
      }
    }
  },
  actions: {}
};
