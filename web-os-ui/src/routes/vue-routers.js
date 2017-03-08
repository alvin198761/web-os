import VueRouter from "vue-router";

const routes = [
  {path: '/', name: '桌面'},

];

export default function (Vue) {
  Vue.use(VueRouter);
  return new VueRouter({routes});
}
