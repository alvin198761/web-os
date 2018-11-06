/**
 * Created by tangzhichao on 2017/2/21.
 */
import http from './http'

const HttpPlugin = {
  install: function (Vue, options) {
    Vue.$http = http;
    Vue.prototype.$http = http;
  }
}

export default function (Vue) {
  Vue.use(HttpPlugin);
}
