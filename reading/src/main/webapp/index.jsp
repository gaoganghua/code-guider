<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login...</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/sweet-alert.min.css" rel="stylesheet" />
    <script src="js/sweet-alert.min.js"></script>
</head>
<script>
    function dl() {

        var name = $("[name='name']").val();
        var pwd = $("[name='pwd']").val();

        if (name == ""||pwd == "") {
            swal("没输入账号或密码!", "检查密码!") ;

        } else {

            $.post("dl.do",
                $("form").serialize(),
                function(data) {
                    if (data.msg) {
                        swal("登陆成功!", "login... 进入首页!", "success");
                        setTimeout(function(){
                            location="go.do?yname="+encodeURI(encodeURI(data.yh.yname));

                        },1000);

                    } else {
                        swal("密码或账号错误!", "检查密码!")
                    }
                },
                "json");
        }

    }
</script>
<body>

<center>

    <div style="width: 300px;margin-top: 260px;">
        <div class="signin">
            <div class="signin-head,container">
                <img src="img/login.jpg" alt="" class="img-circle" style="width: 200px;height: 200px;">
            </div>
            <h3>阅文网站</h3>
            <form class="form-signin" role="form">
                <input class="form-control" placeholder="邮箱" required="" autofocus="" type="text" name="youxiang">
                <input class="form-control" placeholder="密码" required="" type="password" name="pwd">
                <button class="btn btn-lg btn-warning btn-block" type="button" onclick="dl()">登录</button>
                <label class="checkbox"> <input value="remember-me" type="checkbox"> &nbsp;记住我&nbsp;&nbsp;&nbsp;
                    <font color="black">还没有注册? &nbsp;<a href="zc.jsp">点击注册</a></font>
                </label>
            </form>
        </div>
    </div>
</center>

</body>
</html>