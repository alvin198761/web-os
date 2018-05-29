/*应用程序图标模拟数据,作者:高振中,日期:2018-05-12 16:51:08*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/appIcons/queryPage': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        type: "@integer(100,200)",//图标类型
        title: "@word(5,10)",//显示文本
        tip: "@word(5,10)",//悬停提示
        icon: "@word(5,10)",//图标
        publish_id: "@integer(100,200)",//发布人
        create_time: "@integer(100,200)",//创建时间
        parent_id: "@integer(100,200)",//父节点
        route_url: "@word(5,10)",//对应路由地址
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
  'POST /api/appIcons/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/appIcons/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/appIcons/queryList': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        type: "@integer(100,200)",//图标类型
        title: "@word(5,10)",//显示文本
        tip: "@word(5,10)",//悬停提示
        icon: "@word(5,10)",//图标
        publish_id: "@integer(100,200)",//发布人
        create_time: "@integer(100,200)",//创建时间
        parent_id: "@integer(100,200)",//父节点
        route_url: "@word(5,10)",//对应路由地址
        status: "@integer(100,200)",//状态
      }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },
  'DELETE /api/appIcons/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}