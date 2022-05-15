<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Регистрация</title>
    <style>
        body {line-height: 1.5}
    </style>
</head>
<body>
<h3>Регистрация</h3>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <label  for="firstname">Имя
            <input id="firstname" type="text" name="firstname">
        </label><br>
        <label for="lastname">Фамилия
            <input id="lastname" type="text" name="lastname">
        </label><br>
        <label for="email">Электронная почта
            <input id="email" type="email" name="email">
        </label><br>
        <label for="password">Пароль
            <input id="password" type="password" name="password">
        </label><br>
        <label for="tel">Телефон
            <input id="tel" type="tel" name="tel">
        </label><br>
        <label for="address">Адрес
            <input id="address" type="text" name="address">
        </label><br>
<%--        <c:forEach var="gender" items="${requestScope.genders}">--%>
<%--            <input type="radio" name="gender" value="${gender}">${gender}--%>
<%--            <br>--%>
<%--        </c:forEach>--%>
        <input type="radio" name="gender" value="${requestScope.genders[0]}">Муж
        <br>
        <input type="radio" name="gender" value="${requestScope.genders[1]}">Жен
        <br>
        <button type="submit">Отправить</button>
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
