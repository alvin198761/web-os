import http from '../utils/http';

export function queryList(payload) {
  return http.post("/api/protocols/queryList", JSON.stringify(payload));
}

export function saveOrUpdate(action,payload) {
  return http.post("/api/protocols/" + action, JSON.stringify(payload))
}

export function del(ids) {
  return http.delete("/api/protocols/delete", {
    params: {
      ids: ids
    }
  });
}
