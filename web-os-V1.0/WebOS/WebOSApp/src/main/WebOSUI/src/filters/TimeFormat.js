import moment from 'moment'

export default function (timestamp, format) {
  if (!timestamp) return '-';
  if (format) {
    format = format.replace(/'/g, '');
    return moment(timestamp).format(format);
  } else {
    return moment(timestamp).format('YYYY-MM-DD HH:mm:ss');
  }
};
