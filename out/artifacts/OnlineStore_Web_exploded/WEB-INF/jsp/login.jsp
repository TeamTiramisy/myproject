<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход</title>
    <style>
        body {line-height: 1.5}
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label  for="email">Эл почта
        <input id="email" type="email" name="email" value="${param.email}">
    </label><br>
    <label for="password">Пароль
        <input id="password" type="password" name="password">
    </label><br>
    <button type="submit">Вход</button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button">Регистрация</button>
    </a>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span>Почта или пароль неверны</span>
        </div>
    </c:if>
</form>
</body>
</html>
