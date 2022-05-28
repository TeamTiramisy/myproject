<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 19.05.2022
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пользователь</title>
    <style>
        label {
            font-size: 25px;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp" %>
<%@include file="startline.jsp" %>
<h1>Пользователь:</h1>
<label><strong>Имя: </strong>
    ${requestScope.user.firstname}<br>
</label>
<label><strong>Фамилия: </strong>
    ${requestScope.user.lastname}<br>
</label>
<label><strong>Эл почта: </strong>
    ${requestScope.user.email}<br>
</label>
<label><strong>Телефон: </strong>
    ${requestScope.user.tel}<br>
</label>
<label><strong>Адрес: </strong>
    ${requestScope.user.address}<br>
</label>
<label><strong>Роль: </strong>
    ${requestScope.user.role}<br>
</label>
<label><strong>Пол: </strong>
    <c:if test="${requestScope.user.gender eq 'MALE'}">
        Муж<br>
    </c:if>
    <c:if test="${requestScope.user.gender eq 'FEMALE'}">
        Жен<br>
    </c:if>
</label>
<label><strong>Черный список: </strong>
    ${requestScope.user.blackList}<br>
</label>
<form action="${pageContext.request.contextPath}/user?id=${requestScope.user.id}" method="post">
    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select><br>
    <select name="blacklist" id="blacklist">
        <c:forEach var="blacklist" items="${requestScope.list}">
            <option value="${blacklist}">${blacklist}</option>
        </c:forEach>
    </select><br>
    <button type="submit">Изменить</button>
</form>
<a href="${pageContext.request.contextPath}/orders/user?id=${requestScope.user.id}">
    <button type="button">Заказы пользователя</button>
</a>
</body>
</html>
