/*用户信息模拟数据,作者:高振中,日期:2018-05-12 16:51:08*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/users/queryPage': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        login_name: "@word(5,10)",//登录名称
        nick_name: "@word(5,10)",//昵称
        password: "@word(5,10)",//密码
        auth: "@integer(100,200)",//身份
        create_time: "@integer(100,200)",//创建时间
        author: "@integer(100,200)",//创建人
        status: "@integer(100,200)",//状态
      }],
      "number": '@integer(100,200)',
      "size": 10,
      "totalElements": 500,
    });
    setTimeout(function () {
      res.json(data);
    }, 500);
  },
  'POST /api/users/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/users/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/users/queryList': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        login_name: "@word(5,10)",//登录名称
        nick_name: "@word(5,10)",//昵称
        password: "@word(5,10)",//密码
        auth: "@integer(100,200)",//身份
        create_time: "@integer(100,200)",//创建时间
        author: "@integer(100,200)",//创建人
        status: "@integer(100,200)",//状态
      }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },
  'DELETE /api/users/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}