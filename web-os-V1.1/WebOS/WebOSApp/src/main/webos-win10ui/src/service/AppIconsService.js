import http from '../utils/http';

export function queryList(payload) {
  return http.post("/api/appicons/queryList", JSON.stringify(payload));
}

export function save(payload) {
  return http.post("/api/appicons/save", JSON.stringify(payload))
}

export function del(ids) {
  return http.delete("/api/appicons/delete", {
    params: {
      ids: ids
    }
  });
}
