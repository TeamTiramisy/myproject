<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 15.05.2022
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админ</title>
    <style>
        p {
            font-size: 25px;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp" %>
<%@include file="startline.jsp" %>
<h1>Страничка админа:</h1>
<a href="${pageContext.request.contextPath}/add">
    <p>Добавить товар</p>
</a>
<a href="${pageContext.request.contextPath}/users">
    <p>Все пользователи</p>
</a>
<a href="${pageContext.request.contextPath}/orders">
    <p>Все заказы</p>
</a>
<a href="${pageContext.request.contextPath}/orders/processing">
    <p>Заказы на обработку</p>
</a>
<a href="${pageContext.request.contextPath}/orders/completed">
    <p>Заказы на закрытие</p>
</a>

</body>
</html>
