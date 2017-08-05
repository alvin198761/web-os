import VueRouter from "vue-router";
import Main from '../components/layout/Main.vue';
import Caputre from '../components/setting/Capture.vue';
const routes = [
  {path: '/', name: '首页', redirect: '/main'},
  {path: '/main', name: '桌面', component: Main},
  {path: '/capture', name: '照相机', component: Caputre},
];

export default function (Vue) {
  Vue.use(VueRouter);
  return new VueRouter({routes});
}
