import Vue from 'vue'
import Router from 'vue-router'
import RootPane from '@/components/RootPane'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: RootPane
    }
  ]
})
