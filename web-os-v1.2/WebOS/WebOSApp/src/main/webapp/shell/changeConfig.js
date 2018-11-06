var options = process.argv;
var shell = require('shelljs')
console.log("start change config index.js")
console.log("change to :" + options[2])
console.log("----------------------------")
shell.cp("vue.config.js."+ options[2],"vue.config.js");
console.log("change config index.js finished")
