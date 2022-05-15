<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавление товара</title>
    <style>
        body {line-height: 1.5}
    </style>
</head>
<body>
<%@include file="logout.jsp"%>
<form action="${pageContext.request.contextPath}/add" method="post" enctype="multipart/form-data">
    <label  for="name">Имя
        <input id="name" type="text" name="name">
    </label><br>
    <label for="category">Категория:
        <select name="category" id="category">
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category}">${category}</option>
            </c:forEach>
        </select>
    </label><br>
    <label for="description">Описание
        <input id="description" type="text" name="description">
    </label><br>
    <label for="price">Цена
        <input id="price" type="number" name="price">
    </label><br>
    <label for="amount">Количество
        <input id="amount" type="number" name="amount">
    </label><br>
    <label for="image">Фото
        <input id="image" type="file" name="image">
    </label><br>
    <button type="submit">Добавить</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
