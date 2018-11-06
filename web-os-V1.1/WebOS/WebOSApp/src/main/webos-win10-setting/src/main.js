// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import promise from 'es6-promise'
import router from './router'
import store from './store/index'
import installPlugin from './utils/vue-plugins';
Vue.config.productionTip = false
promise.polyfill();

// install vue plugin
installPlugin(Vue);

window.store = store;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store: store,
  components: {App},
  template: '<App/>'
})
