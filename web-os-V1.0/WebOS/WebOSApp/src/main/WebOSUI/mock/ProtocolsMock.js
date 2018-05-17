/*远程协议模拟数据,作者:高振中,日期:2018-05-12 16:51:08*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/protocols/queryPage': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        domain: "@word(5,10)",//ad域
        user: "@word(5,10)",//用户名
        ip: "@word(5,10)",//ip地址
        port: "@integer(100,200)",//端口
        password: "@word(5,10)",//密码
        author: "@integer(100,200)",//作者
        create_time: "@integer(100,200)",//时间
        status: "@integer(100,200)",//状态
        type: "@integer(100,200)",//协议类型
      }],
      "number": '@integer(100,200)',
      "size": 10,
      "totalElements": 500,
    });
    setTimeout(function () {
      res.json(data);
    }, 500);
  },
  'POST /api/protocols/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/protocols/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/protocols/queryList': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        domain: "@word(5,10)",//ad域
        user: "@word(5,10)",//用户名
        ip: "@word(5,10)",//ip地址
        port: "@integer(100,200)",//端口
        password: "@word(5,10)",//密码
        author: "@integer(100,200)",//作者
        create_time: "@integer(100,200)",//时间
        status: "@integer(100,200)",//状态
        type: "@integer(100,200)",//协议类型
      }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },
  'DELETE /api/protocols/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}