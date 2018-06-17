import http from '../utils/http';
//获取列表
export function queryList(payload) {
  return http.post("/api/appicons/queryList", JSON.stringify(payload));
}
//获取分页列表
export function queryPage(payload) {
  return http.post("/api/appicons/queryPage", JSON.stringify(payload));
}
//保存或者修改
export function saveOrUpdate(action, payload) {
  return http.post("/api/appicons/" + action, JSON.stringify(payload))
}
//删除
export function del(ids) {
  return http.delete("/api/appicons/delete", {
    params: {
      ids: ids
    }
  });
}
