<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>OnlineStore</title>
    <style>
        body {line-height: 1.5}
    </style>
</head>
<body>
<%@include file="locale.jsp"%>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label  for="email"><fmt:message key="page.login.email"/>:
        <input id="email" type="email" name="email" value="${param.email}">
    </label><br>
    <label for="password"><fmt:message key="page.login.password"/>:
        <input id="password" type="password" name="password">
    </label><br>
    <button type="submit"><fmt:message key="page.login.submit.button"/></button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button"><fmt:message key="page.login.register.button"/></button>
    </a>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span><fmt:message key="page.login.error"/></span>
        </div>
    </c:if>
</form>
<%@include file="lang.jsp"%>
</body>
</html>
