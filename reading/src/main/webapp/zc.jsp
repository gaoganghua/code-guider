<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册页面</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/sweet-alert.min.css" rel="stylesheet" />
    <script src="js/sweet-alert.min.js"></script>
</head>
<script type="text/javascript">
    $(function(){

        $.post(

            "getS.do",
            function(data){
                for (var   i= 0; i< data.length; i++) {
                    $("#s").append("<option value='"+data[i].codeid+"'>"+data[i].cityname+"</option>");
                }
            },
            "json"

        );
    });

    var getSs=function(){
        var codeid=$("#s").val();
        $.post(
            "getS.do",
            {codeId:codeid},
            function(data){
                for (var  i= 0; i< data.length; i++) {

                    $("#ss").append("<option value='"+data[i].codeid+"'>"+data[i].cityname+"</option>");
                }
            },
            "json"

        );
    }

    var getSss=function(){
        $("#sss").html("");
        var codeid=$("#ss").val();
        $.post(
            "gs.do",
            {codeId:codeid},
            function(data){
                for (var  i= 0; i< data.length; i++) {
                    $("#sss").append("<option value='"+data[i].codeid+"'>"+data[i].cityname+"</option>");
                }
            },
            "json"
        );
    }
</script>
<body style="background: url(img/5B9D159F26A7528DBFA1161E95FCB18E_319852205-1377395662040.jpeg);">
<center>
    <div >
        <div class="col-md-6 col-md-offset-3" style="margin-top: 200px;">
            <form action="http://wwww.baidu.com" class="">

                <div class="form-group has-feedback">
                    <label for="username">邮箱</label>
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span> <input  id="username"
                                                                                                                      class="form-control" placeholder="请推荐输入 邮箱格式" maxlength="20"
                                                                                                                      type="text" name="youxiang" onblur="wy()"><font color="red" ><span id="wy"></span></font>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label>用户名:</label>
                    <div class="input-group">
							<span class="input-group-addon"><span
                                    class="glyphicon glyphicon-lock"></span></span> <input
                            class="form-control" placeholder="请输入1至 5位任意字符 " maxlength="20"
                            name="yname">
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="password">密码</label>
                    <div class="input-group">
							<span class="input-group-addon"><span
                                    class="glyphicon glyphicon-lock"></span></span> <input id="password"
                                                                                           class="form-control" placeholder="请输入任意字符" maxlength="20"
                                                                                           type="password" >
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="password">确认密码</label>
                    <div class="input-group">
							<span class="input-group-addon"><span
                                    class="glyphicon glyphicon-lock"></span></span> <input
                            class="form-control" placeholder="请再输入密码,确认密码" maxlength="20"
                            type="password" name="pwd" onblur="yz()"><font color="red"><span id="yz"></span></font>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <label for="name"><font size="5">选择城市:</font></label>
                    </div>
                    <div class="col-md-3">
                        <select class="form-control" style="width: 200px;" id="s" onchange="getSs()">
                            <option  >--选择省--</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <select class="form-control" style="width: 200px;" id="ss" onchange="getSss()">
                            <option  value="wx">--选择城市--</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <select class="form-control" style="width: 200px;" id="sss" name="qid"  onchange="qc()">
                            <option value="wx">--选择区--</option>
                        </select>
                        <font color="red" size="4"><span id="wx"></span></font>
                    </div>
                </div>

                <div class="form-group">
                    <input class="form-control btn btn-primary" id="submit"
                           value="立&nbsp;&nbsp;即&nbsp;&nbsp;注&nbsp;&nbsp;册" type="button" onclick="zc()">
                </div>

                <div class="form-group">
                    <input value="重置" onclick="cz()" class="form-control btn btn-danger"
                           type="button">
                </div>
            </form>
        </div>
    </div>
</center>
</body>
<script type="text/javascript">
    function qc(){
        var sss=$("#sss").val();
        if(sss!="wx"){ $("#wx").html("");
        }
    }


    function yz(){

        var m1=$("[name='pwd']").val();
        var m2=$("#password").val();
        if(m1!=m2){ $("#yz").html("  两次密码不一样,请检查密码!"); }else{$("#yz").html("");}

    }

    function wy(){
        $("#wy").html("");3
        var yx=$("[name='youxiang']").val();
        $.post(
            "wy.do",
            {youxiang:yx},
            function(data){
                if(data!=false){
                    $("#wy").append("  此邮箱已绑定,请更换邮箱");
                }else{ $("#wy").append("  此邮箱可以绑定");}
            },
            "json"
        );
    }


    function zc(){

        var ss=$("#ss").val();
        var sss=$("#sss").val();
        if(ss=="wx"&&sss=="wx"){$("#wx").text("请选择城镇");}else{$("#wx").html("");

            $.post(
                "zc.do",
                $("form").serialize(),
                function(data){
                    if (data) {
                        alert("注册成功!", "2秒后  login... 进入登陆页面!");
                        setTimeout(function(){
                            location="index.jsp";
                        },1000);

                    }else{ alert("注册失败!", "服务器爆炸!"); } },
                "json"
            );}
    }

    function cz(){location.reload(); }

</script>
</html>