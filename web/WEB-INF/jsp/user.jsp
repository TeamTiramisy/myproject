<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OnlineStore</title>
    <style>
        label {
            font-size: 25px;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp" %>
<%@include file="startline.jsp" %>
<h1><fmt:message key="page.user.h"/>:</h1>
<label><strong><fmt:message key="page.account.firstname"/>: </strong>
    ${requestScope.user.firstname}<br>
</label>
<label><strong><fmt:message key="page.account.lastname"/>: </strong>
    ${requestScope.user.lastname}<br>
</label>
<label><strong><fmt:message key="page.account.email"/>: </strong>
    ${requestScope.user.email}<br>
</label>
<label><strong><fmt:message key="page.account.tel"/>: </strong>
    ${requestScope.user.tel}<br>
</label>
<label><strong><fmt:message key="page.account.address"/>: </strong>
    ${requestScope.user.address}<br>
</label>
<label><strong><fmt:message key="page.user.role"/> </strong>
    ${requestScope.user.role}<br>
</label>
<label><strong><fmt:message key="page.user.gender"/>: </strong>
    <c:if test="${requestScope.user.gender eq 'MALE'}">
        <fmt:message key="page.refrigeration.male"/><br>
    </c:if>
    <c:if test="${requestScope.user.gender eq 'FEMALE'}">
        <fmt:message key="page.refrigeration.female"/><br>
    </c:if>
</label>
<label><strong><fmt:message key="page.user.blacklist"/>: </strong>
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
    <button type="submit"><fmt:message key="page.search.update"/></button>
</form>
<a href="${pageContext.request.contextPath}/orders/user?id=${requestScope.user.id}">
    <button type="button"><fmt:message key="page.user.orders"/></button>
</a>
</body>
</html>
