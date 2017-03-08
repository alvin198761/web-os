/**
 * Created by tangzhichao on 2017/2/21.
 */
import http from './http'
import Element from 'element-ui'
import 'element-ui/lib/theme-default/index.css'

const HttpPlugin = {
  install: function (Vue, options) {
    Vue.$http = http;
    Vue.prototype.$http = http;
  }
}

export default function (Vue) {

  Vue.use(HttpPlugin);
  Vue.use(Element);

  // install echarts
// import VueECharts from 'vue-echarts'
// Vue.component('chart', VueECharts);

}
