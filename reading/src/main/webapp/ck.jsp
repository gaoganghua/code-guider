<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<head><%@taglib prefix="c"
                uri="http://java.sun.com/jsp/jstl/core"%>
    <meta charset="UTF-8">
    <title>查看文章</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <link href="css/sweet-alert.min.css" rel="stylesheet" />
    <script src="js/sweet-alert.min.js"></script>
</head>
<body style="background:url(img/wb3.jpg); " >
<center><br><br>
    <div style=" width: 900px;">
        <table>
            <c:forEach items="${wengzhang }" var="l">
            <c:if test="${l.wid==id }">
            <tr><td>
                <a href="go.do"> <font size="6px">← </font  size="10px">返回首页<font size="8px"></a>
                <img alt="" src="img/${l.pic }"  style="width: 700px;height: 300px;"/>
                <h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    《 &nbsp; ${l.biaoti } &nbsp; 》</h2>
            </td><tr>
            <tr><td><br>  </td></tr>
            <tr><td width="900px"> <font  color="#66CC33"  face="楷体";  size="5px">${l.leirong}> </font></td><tr>
            </c:if>
            </c:forEach>
            <tr>
                <td><a href="go.do"> <font size="6px">← </font  size="10px">返回首页<font size="8px"></a></td>
            </tr>
        </table>
    </div>
</center>


</body>


</html>