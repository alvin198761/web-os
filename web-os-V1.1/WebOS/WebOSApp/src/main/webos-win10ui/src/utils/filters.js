import moment from 'moment';
import {ROLES} from './constant';

export const genderFilter = (gender) => {
  if (gender == 1) return '男';
  if (gender == 2) return '女';
  if (gender == 3) return '未知';
  if (gender == 4) return '保密';
  return gender;
}

export const date_format = (time) => {
  if (time != null)
    return new moment(time).format("YYYY-MM-DD ")
}

export const date_time_format = (time) => {
  if (time != null)
    return new moment(time).format("YYYY-MM-DD HH:mm:ss")
}

export default function (Vue) {

  Vue.filter('gender_filter', genderFilter);
  Vue.filter('date_filter', date_format);
  Vue.filter('date_time_format', date_time_format);
}
