/**
 * Created by Administrator on 2017/3/5.
 */
let Mock = require('mockjs');
let moment = require('moment')

module.exports = {
  "GET /api/setting/rdps": function (req, res) {

    let data = [{
      id: 1,
      domain: '',
      ip: '192.168.192.129',
      port: 3389,
      user: 'administrator',
      password: '1'
    }]

    setTimeout(function () {
      res.json(data)
    }, 500)
  },
  "GET /api/setting/sshs": function (req, res) {

    let data = [{
      id: 1,
      ip: '192.168.192.129',
      port: 22,
      user: 'root',
      password: '1'
    }]

    setTimeout(function () {
      res.json(data)
    }, 500)
  },
  "GET /api/setting/vncs": function (req, res) {

    let data = [{
      id: 1,
      ip: '192.168.192.129',
      port: 5901,
      user: 'root',
      password: '1'
    }]

    setTimeout(function () {
      res.json(data)
    }, 500)
  }
}
