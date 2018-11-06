/*便签信息模拟数据,作者:高振中,日期:2018-05-12 16:51:08*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {
  //心跳
  'GET /api/session/heartbeat': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  //用户数据
  'GET /api/session/info': function (req, res, next) {
    setTimeout(function () {
      res.json({
        name: 'alvin',
        theme: 2,//0,1,2
      });
    }, 500);
  },
}
