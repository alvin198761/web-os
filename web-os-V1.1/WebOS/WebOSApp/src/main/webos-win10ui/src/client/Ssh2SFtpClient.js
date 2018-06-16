let Client = require('ssh2-sftp-client');

//获取ssh2的远程连接
export function getClientConnection({
  host, port, username, password
}) {
  return new Client().connect({
    host: host,
    port: port,
    username: username,
    password: port
  })

}
