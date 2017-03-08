/**
 * Created by tangzhichao on 2017/2/27.
 */

module.exports = {
  "GET /api/note": function (req, res) {
    let data = [{
      id: '001',
      title: '便签',
      content: 'test'
    }];
    setTimeout(function () {
      res.json(data)
    }, 500)
  }
};
