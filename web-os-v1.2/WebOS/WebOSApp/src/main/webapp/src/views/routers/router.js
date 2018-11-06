import Vue from "vue";
import Router from "vue-router";
import Home from "@/views/components/Home";
import RootPane from "@/views/components/RootPane";

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
