'use strict';

const mock = {};
var fs = require('fs');
var path = require('path');
var shell = require('shelljs');

function endWith(src, str) {
  if (str == null || str == "" || src.length == 0 || str.length > src.length)
    return false;
  if (src.substring(src.length - str.length) == str)
    return true;
  else
    return false;
  return true;
}

function moveHtml(src, dist) {
  src = path.resolve(src);
  dist = path.resolve(dist);
  fs.readdirSync(src).forEach(function (subFile) {
    if (endWith(subFile, ".html")) {
      console.log("复制文件：" + path.join(src, subFile));
      var fileName = subFile.substring(0, subFile.length - 5);
      console.log(path.join(dist, fileName + ".ftl"));
      shell.mv(path.join(src, subFile), path.join(dist, fileName + ".ftl"));
    }
  });
}

console.log("build 完成，开始拷贝资源文件");
moveHtml("../resources/static", "../resources/templates");
console.log("拷贝完成");
module.exports = mock;
