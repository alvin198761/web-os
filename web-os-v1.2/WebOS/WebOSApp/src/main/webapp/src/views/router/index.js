import Vue from "vue";
import Router from "vue-router";
import Home from "@/components/Home";
import RootPane from "@/components/RootPane";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Home,
      redirect: '/app',
      children: [
        {
          path: 'app',
          component: RootPane
        },
      ]
    }

  ]
})
