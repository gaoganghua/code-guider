<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><%@taglib prefix="c"
                uri="http://java.sun.com/jsp/jstl/core"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文章发布网</title>
</head>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">

<link href="css/sweet-alert.min.css" rel="stylesheet" />
<script src="js/sweet-alert.min.js"></script>
</head>
<script type="text/javascript">
    $(function(){
        $.post(
            "fenlei.do",
            function(data){
                for (var i = 0; i <data.length; i++) {
                    $("#fenlei").append("<li> <a href='go.do?fid="+data[i].fid+"'>"+data[i].fname+"</a> </li>");
                }
            },
            "json"
        );

    });

</script>
<body style="background:url(img/wb3.jpg); ">
<center>
    </br> <font face="楷体" ; color="blank">
    <div class="container" style="width: 1600px;">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle"
                                data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span> <span
                                class="icon-bar"></span> <span class="icon-bar"></span><span
                                class="icon-bar"> </span>
                        </button>
                        <a class="navbar-brand" href="#datu"><h2>导航图</h2></a>
                    </div>

                    <div class="collapse navbar-collapse"
                         id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="#leirong"><h2>最新文章</h2></a></li>
                            <li><a href="#end-tel"><h2>联系我们</h2></a></li>
                            <li class="dropdown"><a href="#" class="dropdown-toggle"
                                                    data-toggle="dropdown"><h2>图书分类</h2>
                                <strong class="caret"></strong></a>
                                <ul class="dropdown-menu" id="fenlei">
                                </ul></li>
                        </ul>
                        <form class="navbar-form navbar-left" role="search"
                              action="go.do" method="post">
                            <div class="form-group">
                                <input class="form-control" type="text" name="biaoti"
                                       value="${biaoti }" />
                            </div>
                            <button type="submit" class="btn btn-default">
                                <h4>查询书籍</h4>
                            </button>
                        </form>
                        <ul class="nav navbar-nav navbar-right">

                            <li class="dropdown"><a href="#" class="dropdown-toggle"
                                                    data-toggle="dropdown"><h2>hello ! ${name }</h2>
                                <strong class="caret"></strong></a>
                                <ul class="dropdown-menu">
                                    <li><font size="5"> <a href="tisp.do">发布书籍</a></font></li>
                                    <li><font size="5"> <a href="ht.do?name=">进入后台</a>
                                    </font></li>
                                    <li><font size="5"> <a href="index.jsp">注销</a>
                                    </font></li>

                                </ul></li>
                        </ul>
                    </div>

                </nav>
                <div class="row clearfix" id="datu">
                    <div class="col-md-12 column">
                        <div class="carousel slide" id="carousel-61053">
                            <ol class="carousel-indicators">
                                <c:forEach items="${jx }" var="j" varStatus="ind">
                                    <li class="active" data-slide-to="0"
                                        data-target="#carousel-61053"></li>
                                </c:forEach>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <a href="go.do?fid=5"> <img alt="" src="img/know3.jpg"
                                                                style="width: 1800px; height: 500px;" /></a>
                                    <div class="carousel-caption">
                                        <font size="6px" color="gree"> 不知道看啥? </font>
                                        <p></p>
                                    </div>
                                </div>
                                <img>
                                <c:forEach items="${jx }" var="j">
                                    <div class="item">
                                        <a href="go.do?fid=${j.fid }"><img alt="" src="img/${j.jpic }" style="width: 1800px; height: 500px;" /></a>
                                        <div class="carousel-caption">
                                            <font size="6px" color="gree"> ${j.jname } </font>
                                        </div>
                                    </div>
                                </c:forEach>
                                </img>
                            </div>
                            <a class="left carousel-control" href="#carousel-61053"
                               data-slide="prev"><span
                                    class="glyphicon glyphicon-chevron-left"></span></a> <a
                                class="right carousel-control" href="#carousel-61053"
                                data-slide="next"><span
                                class="glyphicon glyphicon-chevron-right"></span></a>
                        </div>
                    </div>
                </div>
                <div class="row clearfix" id="leirong">
                    <div class="jumbotron">
                        <h1>最新文章:</h1>
                    </div>
                    <h2>${msg }</h2>
                    <c:forEach items="${wengzhang }" var="w" varStatus="status">
                        <c:if test="${status.index<4 }">
                            <div class="col-md-4 column">
                                <h1>
                                    <a href="ck.do?wid=${w.wid }"> 《&nbsp;${w.biaoti }&nbsp; 》
                                </h1>
                                <p>
                                    <img alt="" src="img/${w.pic }"
                                         style="width: 500px; height: 300px;" />
                                </p>
                                </a>
                                <div class="collapse navbar-collapse">
                                    <p class="text-muted"></p>
                                </div>
                                <p>
                                <div class="modal fade" id="myModal2" tabindex="-1"
                                     role="dialog" aria-labelledby="myModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-hidden="true">×</button>
                                                <h4 class="modal-title" id="myModalLabel">
                                                    标题:	<input type="text" value="" name="biaoti">
                                                </h4>
                                                <h4 class="modal-title" id="myModalLabel">
                                                    <br />

                                                    <textarea rows="20" cols="50" name="leirong"></textarea>
                                                </h4>
                                            </div>
                                            <div class="modal-body">按下 ESC 按钮退出。</div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default"
                                                        data-dismiss="modal">关闭</button>
                                            </div>
                                        </div>
                                        <!-- /.modal-content -->
                                    </div>
                                    <!-- /.modal-dialog -->
                                </div>
                                <script type="text/javascript">
                                    function xg(wids){
                                        $("[name='leirong']").html("");

                                        $.post(
                                            "huxian.do",
                                            {wid:wids},
                                            function(data){

                                                $("[name='biaoti']").attr("value","  "+data.biaoti);
                                                $("[name='leirong']").append(data.leirong);
                                            },
                                            "json"
                                        );

                                        $('#myModal2').modal({
                                            keyboard: true
                                        })}
                                </script>
                                <input value=" 查看文章内容 »" onclick="xg(${w.wid})"
                                       class="form-control btn btn-danger" type="button">

                                </p>

                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="jumbotron">
                <h1>
                    Hello, world!
                </h1>
                <p>
                    This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.
                </p>
                <p>
                    <a class="btn btn-primary btn-large" href="#">Learn more</a>
                </p>
            </div>
        </div>
    </div>

    <div id="end-tel" style="width: 1000px;height: 100px;background-color:;">


        <center>
            <h3>tel:003-13-321-32</h3>
            <h2>地址 无名区-2888街道-111号</h2>
        </center>
    </div>
</font>

</center>
</body>
</html>