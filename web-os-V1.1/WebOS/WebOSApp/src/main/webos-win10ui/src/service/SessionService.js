import http from '../utils/http';
//用户信息
export function fetchInfo() {
  return http.get("/api/session/info");

}
//心跳
export function heartbeat() {
  return http.get("/api/session/heartbeat");
}


