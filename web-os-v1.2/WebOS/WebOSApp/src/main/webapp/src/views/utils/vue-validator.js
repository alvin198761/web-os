//手机号验证
export const poneNoFormatValidator = (rule, value, callback) => {
  var reg = /^1([0-9]{10})$/;
  if (!reg.test(value)) {
    callback("手机号格式必须正确");
  } else {
    callback();
  }
}
export const idValidator = (rule, value, callback) => {
  var reg = /^1([0-9]{10})$/;
// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
  if (!reg.test(value)) {
    callback("身份证格式必须正确");
  } else {
    callback();
  }
}

export function checkMail(rule,value,callback) => {
 var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(mail)
  if (!regex.test(value)) {
    callback("邮件格式不正确");
  } else {
    callback();
  }
}
