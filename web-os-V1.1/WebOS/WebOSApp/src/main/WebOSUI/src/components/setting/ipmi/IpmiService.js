import {ipmi} from 'supermicro-ipmi/lib/app';

//创建ipmi 服务配置
export function getServer(host, port, username, password) {
  return new ipmi({host: host, port: port, username: username, password: password});
}
//ipmi 登录
export function impiLogin(server, callback) {
  server.login(callback || function (err) {
      if (err) {// err 为空 则成功
        console.log(err)
        return;
      }
    });
}
//获取电源状态
export function getCurrentPowerState(server, callback) {
  server.getCurrentPowerState(callback || function (err, state) {
      if (err) {
        console.log(err);
        return;
      }
      if (state == 1) {
        console.log('The server is currently online');
      } else {
        console.log('The server is currently offline');
      }
    });
}
/* 电源状态  https://github.com/phillipsnick/supermicro-ipmi
 State	powerAction
 3	RESET
 0	OFF_IMMEDIATE
 5	OFF_ORDERLY
 1	ON
 2	CYCLE
 */
//操作电源状态
export  function setPowerAction (server,action,callback) {
  server.setPowerAction(action,callback || function(err) {
      if (err) {
        console.log(err);
        return;
      }
      console.log('Successfully sent request to power server on');
    });
}



