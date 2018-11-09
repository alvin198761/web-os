import Vue from 'vue'
import App from './App.vue'
import router from './routers/setting-router'
import store from './store'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = true
Vue.use(ElementUI);

window.store = window.parent.store;
window.Win10 = window.parent.Win10;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
