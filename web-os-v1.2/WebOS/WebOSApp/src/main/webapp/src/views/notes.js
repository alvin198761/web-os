// import '../../node_modules/@webcomponents/webcomponentsjs/webcomponents-loader';
// import '../../node_modules/roughjs';
// import '../../node_modules/wired-elements/wired-elements';
import Vue from "vue";
import store from "./store";
import Router from "vue-router";
import Home from "./components/Home";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import Notes from "./notes/Notes.vue";



Vue.config.productionTip = true
Vue.use(ElementUI);

window.store = window.parent.store;
window.Win10 = window.parent.Win10;

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/',
      component: Home,
      redirect: '/notes',
      children: [
        {
          path: 'notes',
          component: Notes
        }
      ]
    }

  ]
})

new Vue({
  router,
  store,
  render: h => h(Home)
}).$mount('#app')
