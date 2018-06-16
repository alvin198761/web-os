/**
 * Created by tangzhichao on 2017/2/21.
 */
import http from './http'
// import VueECharts from 'vue-echarts'

const HttpPlugin = {
  install: function (Vue, options) {
    Vue.$http = http;
    Vue.prototype.$http = http;
  }
}
// import china from 'echarts/map/json/china.json'

export default function (Vue) {
  Vue.use(HttpPlugin);
  // VueECharts.registerMap('china', china);
  // Vue.component('chart', VueECharts);
}
