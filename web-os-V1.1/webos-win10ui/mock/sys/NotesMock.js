/*便签信息模拟数据,作者:高振中,日期:2018-05-12 16:51:08*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/notes/queryPage': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        title: "@word(5,10)",//标题
        content: "@word(5,10)",//内容
        last_time: "@integer(100,200)",//最后修改时间
        author: "@integer(100,200)",//作者
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
  'POST /api/notes/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/notes/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/notes/queryList': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        title: "@word(5,10)",//标题
        content: "@word(5,10)",//内容
        last_time: "@integer(100,200)",//最后修改时间
        author: "@integer(100,200)",//作者
        status: "@integer(100,200)",//状态
      }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },
  'DELETE /api/notes/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}