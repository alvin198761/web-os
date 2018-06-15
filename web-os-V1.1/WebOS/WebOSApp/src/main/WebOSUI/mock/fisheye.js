/**
 * Created by tangzhichao on 2017/2/27.
 */

let Mock = require('mockjs');

module.exports = {
  "GET /api/fisheye/apps": function (req, res) {
    let apps = [];
    for (let i = 0; i < 14; i++) {
      let pic = i % 6 + 1;
      apps.push({
        id: '00' + i,
        type: Mock.mock('@integer(1,2)'),
        title: '测试应用' + i,
        icon: pic + '.png',
        url: 'https://www.iviewui.com/docs/guide/install',
        msgCount: Mock.mock('@integer(0, 3)')
      })
    }
    let data = [
      {
        id: '001',
        type: 'home',
        title: 'Home',
        icon: 'home.png',
        apps: apps
      }, {
        id: '002',
        type: 'contact',
        title: 'Contact',
        icon: 'email.png',
        apps: apps
      }, {
        id: '003',
        type: 'portfolio',
        title: 'Portfolio',
        icon: 'portfolio.png',
        apps: apps
      }, {
        id: '004',
        type: 'music',
        title: 'Music',
        icon: 'music.png',
        apps: apps
      }, {
        id: '005',
        type: 'video',
        title: 'Video',
        icon: 'video.png',
        apps: apps
      }, {
        id: '006',
        type: 'history',
        title: 'History',
        icon: 'history.png',
        apps: apps
      }, {
        id: '007',
        type: 'calendar',
        title: 'Calendar',
        icon: 'calendar.png',
        apps: apps
      }, {
        id: '008',
        type: 'rss',
        title: 'Rss',
        icon: 'rss.png',
        apps: apps
      }
    ];
    setTimeout(function () {
      res.json(data)
    }, 500)
  },
  "GET /api/content/apps/:id": function (req, res) {
    let data = [];
    for (let i = 0; i < 14; i++) {
      let pic = i % 6 + 1;
      data.push({
        id: '00' + i,
        type: 1,
        title: '子应用' + i,
        icon: pic + '.png',
        url: 'www.baidu.com',
        msgCount: Mock.mock('@integer(0, 3)')
      })
    }
    setTimeout(function () {
      res.json(data)
    }, 500)
  }
};
