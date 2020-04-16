$(function () {

    var InterValObj; //定义定时器
    //js轮询
    InterValObj = window.setInterval(getToken, 10000);
    //加载所有的导航菜单数据
    loadAllWebMenus();
    //获取令牌
    getToken();
    function loadAllWebMenus() {
        $.ajax({
            type:"POST",
            url:"/webmenu/loadAll",
            dataType:"JSON",
            success:function (data) {
                var webMenuStr = '';
                $.each(data,function (i,webMenu) {
                    webMenuStr += '<li><a href="'+webMenu.url+'">'+webMenu.title+'</a></li>';
                });
                $(".menu_r").html(webMenuStr);
            },
            error:function () {
                alert("服务器异常！！")
            }
        });
    }

    function getToken() {
        console.log("获取令牌");
        $.ajax({
            type:"POST",
            xhrFields:{withCredentials:true},
            url:"http://localhost:8085/webusers/getToken",
            success:function (data) {
                console.log(data);
                if(data!=''){
                    //关掉js轮询
                    window.clearInterval(InterValObj);
                    //拿着令牌去redis中找用户数据
                    getRedisLoginUser(data);
                }
            },
            error:function () {
                alert('服务器异常！！!');
            }
        });
    }
    //获取到了令牌，拿着令牌去redis中找用户数据
    function getRedisLoginUser(token) {
        $.ajax({
            type:"POST",
            url:"http://localhost:8085/webusers/getRedisLoginUser",
            data:{"token":token},
            success:function (data) {
                console.log(data);
                if(data!=""){

                    $("#loginHeader").replaceWith('<span class="fl" id="loginHeader">你好，欢迎&nbsp;'+data.uname+'&nbsp;<img src="'+data.userheader+'" width="30px;" height="30px;"/>&nbsp;<span><a href="javascript:void(0)" onclick="exitUser()">注销</a></span>|&nbsp;<a href="#">我的订单</a>&nbsp;|</span>');
                }else{
                    InterValObj = window.setInterval(getToken, 10000);
                }
            },
            error:function () {
                alert("服务器异常！！")
            }
        });
    }
    //注销
    exitUser = function () {
        $.ajax({
            type:"POST",
            xhrFields:{withCredentials:true},
            url:"http://localhost:8085/webusers/exitUser",
            success:function (data) {
                console.log(data);
                if(data){
                    $("#loginHeader").replaceWith('<span class="fl" id="loginHeader">你好，请<a href="Login.html">登录</a>&nbsp; <a href="Regist.html" style="color:#ff4e00;">免费注册</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|</span>');
                }
            },
            error:function () {
                alert('服务器异常！！!');
            }
        });
    }
});