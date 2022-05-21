<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Личный профель</title>
    <style>
        body {line-height: 1.5}

        .error{
            text-align: left;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp"%>
<form action="${pageContext.request.contextPath}/profile" method="post">
    <label for="firstname">Имя
        <input id="firstname" type="text" name="firstname" value="${sessionScope.user.firstname}">
    </label><br>
    <label for="lastname">Фамилия
        <input id="lastname" type="text" name="lastname" value="${sessionScope.user.lastname}">
    </label><br>
    <label for="email">Электронная почта
        <input id="email" type="email" name="email" value="${sessionScope.user.email}">
    </label><br>
    <label for="password">Пароль
        <input id="password" type="password" name="password" value="${sessionScope.user.password}">
    </label><br>
    <label for="tel">Телефон
        <input id="tel" type="tel" name="tel" value="${sessionScope.user.tel}">
    </label><br>
    <label for="address">Адрес
        <input id="address" type="text" name="address" value="${sessionScope.user.address}">
    </label><br>
    <button type="submit">Изменить</button><br>
    <c:if test="${not empty requestScope.errors}">
        <div class="error" style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}<span><br>
            </c:forEach>
        </div>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/delete?id=${sessionScope.user.id}" method="post">
    <button type="submit">Удалить профель</button>
</form>
</body>
</html>
