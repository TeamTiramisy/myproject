<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Изменить товар</title>
    <style>
        body {line-height: 1.5}
    </style>
</head>
<body>
<%@include file="logout.jsp" %>
<%@include file="startline.jsp"%>
<h1>Изменить товар:</h1>
<form action="${pageContext.request.contextPath}/update?id=${requestScope.product.id}" method="post">
    <label  for="name">Имя
        <input id="name" type="text" name="name" value="${requestScope.product.name}">
    </label><br>
    <label for="description">Описание
        <input id="description" type="text" name="description" value="${requestScope.product.description}">
    </label><br>
    <label for="price">Цена
        <input id="price" type="number" name="price" value="${requestScope.product.price}">
    </label><br>
    <label for="amount">Количество
        <input id="amount" type="number" name="amount" value="${requestScope.product.amount}">
    </label><br>
    <button type="submit">Изменить</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/deletion?id=${requestScope.product.id}" method="post">
    <button type="submit">Удалить товар</button>
</form>
</body>
</html>
