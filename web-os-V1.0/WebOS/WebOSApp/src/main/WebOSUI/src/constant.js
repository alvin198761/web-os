/**
 * Created by tangzhichao on 2017/2/22.
 */
export const APP_TYPE = {
  APP: 1,
  DIR: 2
}

export function componentIndexOf(tasks, id) {
  for (let i = 0; i < tasks.length; i++) {
    if (tasks[i].el_id === id) {
      return i;
    }
  }
  return -1;
}
