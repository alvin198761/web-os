// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import 'string.prototype.startswith'
import promise from 'es6-promise'

import App from './App.vue';
import store from './store/index'
import installFilter from './filters/vue-filters';
import installPlugin from './plugins/vue-plugins';
import installRouter from './routes/vue-routers';
import {sync} from 'vuex-router-sync'

import 'assets/css/smartMenu.css';
import 'assets/js/jquery-smartMenu';

import 'assets/css/style.css';
import 'assets/js/interface';

import 'assets/css/main.css';
import '../node_modules/jquery-ui/themes/base/all.css';
import '../node_modules/jquery-ui/ui/widgets/draggable';
import '../node_modules/jquery-ui/ui/widgets/resizable';
import '../node_modules/jquery-ui/ui/widgets/sortable';
import './assets/js/jq-ie-plugin';

promise.polyfill();

// install vue plugin
installPlugin(Vue);

// install router
const router = installRouter(Vue);

// install filter
installFilter(Vue);

sync(store, router)

/* eslint-disable no-new */
new Vue({
  router: router,
  store: store,
  el: '#app',
  template: '<App/>',
  components: {App}
});
