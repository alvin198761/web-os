/**
 * Created by tangzhichao on 2017/1/9.
 */
module.exports = {
  "GET /api/sidebar/apps": function (req, res) {
    let data = [
      {
        id: 'site_001',
        type: 1,
        title: '远程桌面连接',
        icon: 'rdp.png',
        url: 'rdp'
      },
      {
        id: 'site_002',
        type: 1,
        title: 'VNC 连接',
        icon: 'vnc.png',
        url: 'vnc'
      },
      {
        id: 'site_003',
        type: 1,
        title: 'putty',
        icon: 'putty.png',
        url: 'putty'
      },
      {
        id: 'site_004',
        type: 1,
        title: 'ipmi',
        icon: 'ipmi.png',
        url: 'ipmi'
      },
      {
        id: 'site_005',
        type: 1,
        title: '邮箱',
        icon: 'mail.png',
        url: 'mail'
      },
      {
        id: 'site_006',
        type: 1,
        title: '时钟',
        icon: 'time.png',
        url: 'time'
      }
    ];
    setTimeout(function () {
      res.json(data)
    }, 500)
  }
};
