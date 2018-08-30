var url = "${pageContext.request.contextPath}";
    function show_box(id) {
    $('.widget-box.visible').removeClass('visible');
    $('#'+id).addClass('visible');
   }
function login(){
  if(logCheck()){
      debugger;
    var loginname = $('#loginname').val();
    var loginpassword = $('#loginpassword').val();
    var isRememberme = $("input[name='rememberme']:checkbox").val() == 1;
    $.ajax({
        type:"POST",
        url:"/oa/login",
        data:{username:loginname,password:$.md5(loginpassword),isRememberme:isRememberme,logintime:formateTime(new Date())},
        dataType:"json",
        cache:true,
        success:function(data){
             if(data != 0){
                 window.location.href = "/oa/main.html";
             }else if (data == 0) {
                 $('#log_msg').tips({
                     side:1,
                     msg:'登陆失败，请稍后再试！！！',
                     bg:'#F00',
                     time:3
                 });
                 show_box('login-box');
                 $('#loginname').focus();
                 return false;
             }
        },
        error:function(data){
           alert("网络异常");
        }
    });
  }
}
function logCheck(){
    //验证用户名
    if($('#loginname').val() == ""){
        $('#loginname').tips({
            side:3,
            msg:'用户名不能为空',
            bg:'#F00',
            time:2,
        })
        $('#loginname').focus();
        $('#loginname').val("");
        return false;
    }else{
        $('#loginname').val($.trim($('#loginname').val()));
    }

    //验证密码不能为空
    if($('#loginpassword').val() == ""){
        $('#loginpassword').tips({
            side:3,
            msg:'密码不能为空',
            bg:'#F00',
            time:2
        })
        $('#loginpassword').focus();
        $('#loginpassword').val("");
        return false;
    }else{
        $('#loginpassword').val($.trim($('#loginpassword').val()));
    }

    //验证协议
    var rememberme = $("input[name='rememberme']:checkbox").val();
    if(rememberme != 1){
        $('#rememberme').tips({
            side:3,
            msg:'请同意相关协议',
            bg:'#F00',
            time:3
        })
        return false;
    }
   return true;
}

function register() {
        if(regCheck()){
            var username = $('#username').val();
            var password = $('#password').val();
            var email = $('#email').val();
            var phone = $('#phone').val();
            var isChecked = $("input[name='service']:checkbox").val() == 1;
            var loginTime = formateTime(new Date());
            var registTime = formateTime(new Date());
          //  var url = '[[@{/register}]]';

            $.ajax({
                type:"POST",
                url:'/oa/register',
                data:{username:username,password:$.md5(password),email:email,phone:phone,isChecked:isChecked,loginTime:loginTime,registTime:registTime},
                dataType:'json',
                cache:false,
                success:function(data){
                    if(data.count != 0){
                        show_box('login-box');
                        $('#log_msg').tips({
                            side:1,
                            msg:'注册成功，请登录！！！',
                            bg:'#68B500',
                            time:3
                        })
                    }else {
                        show_box('signup-box');
                        $('#reg_msg').tips({
                            side:1,
                            msg:'注册失败，请重新注册！！！',
                            bg:'#F00',
                            time:3
                        })
                    }
                },
                error:function(){
                    alert("网络故障，请稍后重新测试");
                }
            });
        }
    }
function regCheck(){
        debugger
    var isChecked = $("input[name='service']:checkbox").val();
    //验证邮箱
    if($('#email').val() == ""){
        $('#email').tips({
            side:3,
            msg:'邮箱不能为空',
            bg:'#F00',
            time:2,
        })
        $('#email').focus();
        $('#email').val("");
        return false;
    }else if(!isEmail($('#email').val())) {
        $('#email').tips({
            side: 3,
            msg: '邮箱格式不正确',
            bg: '#F00',
            time: 3
        })
        $('#email').focus();
        $('#email').val($.trim($('#email').val()));
        return false;
    }else{
        $('#email').val($.trim($('#email').val()));
    }

    //验证手机号
    if($('#phone').val() == ""){
        $('#phone').tips({
            side:3,
            msg:'用户名不能为空',
            bg:'#F00',
            time:2,
        })
        $('#phone').focus();
        $('#phone').val("");
        return false;
    }else if(!isPhone($('#phone').val())){
        $('#phone').tips({
            side:3,
            msg:'手机号格式不正确',
            bg:'#F00',
            time:3
        })
        $('#phone').focus();
        $('#phone').val($.trim($('#phone').val()));
        return false;
    }else{
        $('#phone').val($.trim($('#phone').val()));
    }
    //验证用户名不为空
    if($('#username').val() == ""){
        $('#username').tips({
            side:3,
            msg:'用户名不能为空',
            bg:'#F00',
            time:2,
        })
        $('#username').focus();
        $('#username').val("");
        return false;
    }else{
        $('#usename').val($.trim($('#username').val()));
    }
    //验证密码不能为空
    if($('#password').val() == "" ) {
        $('#password').tips({
            side: 3,
            msg: '密码不能为空',
            bg: '#F00',
            time: 2,
        })
        $('#password').focus();
        $('#password').val("");
        return false;
    }else if($('#password').val().length < 6 ){
        $('#password').tips({
            side: 3,
            msg: '密码长度不得小于6位',
            bg: '#F00',
            time: 2
        })
        $('#password').focus();
        $('#password').val("");
        return false;
    }else{
        $('#password').val($.trim($('#password').val()));
    }

    //验证重复输入密码
    if($('#aginpwd').val() != $('#password').val()) {
        $('#aginpwd').tips({
            side: 3,
            msg: '两次密码输入不一致',
            bg: '#F00',
            time: 3,
        })
        $('#username').focus();
        return false;
    }

    //验证协议
    if(isChecked != 1){
        $('#service').tips({
            side:3,
            msg:'请同意相关协议',
            bg:'#F00',
            time:3
        })
        return false;
    }
    return true;
}

//手机号码正则
function  isPhone(phone) {
    return (new RegExp(/^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/).test(phone));
}

//邮箱正则
function  isEmail(email) {
    return (new RegExp(/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/).test(email));
}

//格式化时间
function formateTime(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h=h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    var second=date.getSeconds();
    second=second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
}



