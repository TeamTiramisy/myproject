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
</head>
<body>
<%@include file="logout.jsp"%>
${requestScope.user.firstname}<br>
${requestScope.user.lastname}<br>
${requestScope.user.email}<br>
${requestScope.user.tel}<br>
${requestScope.user.address}<br>
${requestScope.user.role}<br>
<c:if test="${requestScope.user.gender eq 'MALE'}">
    Муж<br>
</c:if>
<c:if test="${requestScope.user.gender eq 'FEMALE'}">
    Жен<br>
</c:if>
${requestScope.user.blackList}<br>
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
</body>
</html>
