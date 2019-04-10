<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<head><%@taglib prefix="c"
                uri="http://java.sun.com/jsp/jstl/core"%>
    <meta charset="UTF-8">
    <title>Accordion Tools - jQuery EasyUI Demo</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <link href="css/sweet-alert.min.css" rel="stylesheet" />
    <script src="js/sweet-alert.min.js"></script>
</head>
<body style="background-color: #646464;">

<center><br><br>
    <table>
        <c:forEach items="${wengzhang }" var="l">
        <tr><td><br><br></td></tr>
        <tr><td> <img alt="" src="img/${l.pic }"  style="width: 700px;height: 300px;"/></td><tr>
        <tr><td><br>  </td></tr>
        <tr><td width="700px"> <font  color="#FFFFFF"  size="4px">${l.leirong}> </font></td><tr>
        </c:forEach>

    </table>
</center>


</body>


</html>