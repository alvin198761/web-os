'use strict';

const mock = {};
var fs = require('fs');
var path = require('path');

function forEachFile(file) {
  //这里考虑到如果判断目录，会走异步代码，会导致导出的数据为空，所以做了一个蹩脚的判断
  if(file.indexOf(".js") != -1){
    console.log(file);
    Object.assign(mock, require('.' + file));
  }else {
    fs.readdirSync(path.join(__dirname + file)).forEach(function (subFile) {
      forEachFile(file + "/" + subFile);
    });
  }
}

forEachFile("/mock");
module.exports = mock;
