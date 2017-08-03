var HttpUtil;
HttpUtil = {
      success_code:"200",
      error_code:"500"
};

/*手机验证*/
function IsMoble(num) {
      var phone = num || 0;
      phone = $.trim(phone);
      var Format = /^1[3,4,5,7,8]\d{9}$/;
      var Is = Format.test(phone) ? true : false;
      return Is;
}

/*email验证*/
function IsEmail(str) {
      if (str.indexOf("@") < 1 || str.indexOf(".") < 1) {
            return false;
      }
      var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (reg.test(str)) {
            return true;
      } else {
            return false;
      }
}
/*263对啊邮箱验证*/
function IsDuiaEmail(str){
      var flag = false;
      if(IsEmail(str)){
            var emailArr = str.split("@");
            if(emailArr == null || emailArr.length!=2){
                  flag = false;
            }else if(emailArr[1]!="163.com"){
                  flag = false;
            }else{
                  flag =  true;
            }
      }

      return flag;
}

function ExceptionDialog(data) {
      var flag = true;
      if(null != data && null != data.code){
            if (data.code == HttpUtil.error_code) {
                  var message = data.msg;//异常信息
                  var excepitonDialog = BootstrapDialog.confirm({
                        title: '系统异常',
                        message: message,
                        type: BootstrapDialog.TYPE_DANGER,
                        closable: true,
                        btnCancelLabel: '取消',
                        btnOKLabel: '确认',
                        btnOKClass: 'btn-danger',
                        callback: function (result) {
                              if (result) {
                                    excepitonDialog.close();
                                    flag =  false;
                              }
                        }
                  });
                  flag =  false;
            }
            if (data.code == HttpUtil.success_code) {
                  flag = true;
            }
      }
      return flag;
}

//数字转大写 1 = 一
/**
 * @title 数字转大写类
 * @note 本类一律违规验证返回false
 * @author {boonyachengdu@gmail.com}
 * @date 2013-07-01
 * @formatter "2013-07-01 00:00:00" , "2013-07-01"
 */
var numberUpperCase = {
      ary0:["零", "一", "二", "三", "四", "五", "六", "七", "八", "九"],
      ary1:["", "十", "百", "千"],
      ary2:["", "万", "亿", "兆"],
      init:function (name) {
            this.name = name;
      },
      strrev:function () {
            var ary = []
            for (var i = this.name.length; i >= 0; i--) {
                  ary.push(this.name[i])
            }
            return ary.join("");
      }, //倒转字符串。
      pri_ary:function () {
            var $this = this
            var ary = this.strrev();
            var zero = ""
            var newary = ""
            var i4 = -1
            for (var i = 0; i < ary.length; i++) {
                  if (i % 4 == 0) { //首先判断万级单位，每隔四个字符就让万级单位数组索引号递增
                        i4++;
                        newary = this.ary2[i4] + newary; //将万级单位存入该字符的读法中去，它肯定是放在当前字符读法的末尾，所以首先将它叠加入$r中，
                        zero = ""; //在万级单位位置的“0”肯定是不用的读的，所以设置零的读法为空

                  }
                  //关于0的处理与判断。
                  if (ary[i] == '0') { //如果读出的字符是“0”，执行如下判断这个“0”是否读作“零”
                        switch (i % 4) {
                              case 0:
                                    break;
                              //如果位置索引能被4整除，表示它所处位置是万级单位位置，这个位置的0的读法在前面就已经设置好了，所以这里直接跳过
                              case 1:
                              case 2:
                              case 3:
                                    if (ary[i - 1] != '0') {
                                          zero = "零"
                                    }
                                    ; //如果不被4整除，那么都执行这段判断代码：如果它的下一位数字（针对当前字符串来说是上一个字符，因为之前执行了反转）也是0，那么跳过，否则读作“零”
                                    break;

                        }

                        newary = zero + newary;
                        zero = '';
                  }
                  else { //如果不是“0”
                        newary = this.ary0[parseInt(ary[i])] + this.ary1[i % 4] + newary; //就将该当字符转换成数值型,并作为数组ary0的索引号,以得到与之对应的中文读法，其后再跟上它的的一级单位（空、十、百还是千）最后再加上前面已存入的读法内容。
                  }

            }
            if (newary.indexOf("零") == 0) {
                  newary = newary.substr(1)
            }//处理前面的0
            return newary;
      }
}


/**
 * 将表单序列化为json对象
 * @param 表单的id
 * @returns {{}}
 */
function formToJson(form) {
      var result = {};
      var fieldArray = $('#' + form).serializeArray();
      for (var i = 0; i < fieldArray.length; i++) {
            var field = fieldArray[i];
            if (field.name in result) {
                  result[field.name] += ',' + field.value;
            } else {
                  result[field.name] = field.value;
            }
      }
      return result;
}

//简化ajax响应处理
function disposeResponse(response,successCallback) {
      if (response.code == '200') {
            successCallback();
      } else {
            BootstrapDialog.alert(response.msg);
      }
}
//获得URL 参数
function getRequestParam(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      var r = window.location.search.substr(1).match(reg);
      if (r != null) return unescape(r[2]); return null;
}