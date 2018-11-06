let Client = require('ssh2-sftp-client');
//创建一个客户端对象
export function getClient() {
  const sftp = new Client();
  sftp.on('close', (data) => console.log("close:" + data))
  sftp.on('end', (data) => console.log("end:" + data))
  sftp.on('error', (err) => console.log("err:" + err))
}
//获取ssh2的远程连接
function getClientConnection(sftp, {
  host, port, username, password
}) {
  return sftp.connect({
    host: host,
    port: port,
    username: username,
    password: password
  })
}

//文件列表
export function list(sftp, config, dir) {
  getClientConnection(sftp, config).then(() => {
    sftp.list(dir);
  }).then((data) => {
    console.log(data);
  }).catch((err) => {
    console.log(err)
  })
}

// sftp.get(remoteFilePath, [useCompression], [encoding], [addtionalOptions]);
// sftp.put(localFilePath, remoteFilePath, [useCompression], [encoding], [addtionalOptions]);
// sftp.mkdir(remoteFilePath, recursive);
// sftp.rmdir(localPath, recursive);
// sftp.delete(remoteFilePath);
// sftp.rename(remoteSourcePath, remoteDestPath);
// sftp.chmod(remoteDestPath, mode);


export function closeClient(sftp) {
  sftp.end();
}
