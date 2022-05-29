<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>OnlineStore</title>
    <style>
        body {line-height: 1.5}

        .error{
            text-align: left;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp"%>
<%@include file="startline.jsp"%>
<h1><fmt:message key="page.account.h"/>:</h1>
<form action="${pageContext.request.contextPath}/account" method="post">
    <label for="firstname"><fmt:message key="page.account.firstname"/>:
        <input id="firstname" type="text" name="firstname" value="${sessionScope.user.firstname}">
    </label><br>
    <label for="lastname"><fmt:message key="page.account.lastname"/>:
        <input id="lastname" type="text" name="lastname" value="${sessionScope.user.lastname}">
    </label><br>
    <label for="email"><fmt:message key="page.account.email"/>:
        <input id="email" type="email" name="email" value="${sessionScope.user.email}">
    </label><br>
    <label for="password"><fmt:message key="page.account.password"/>:
        <input id="password" type="password" name="password" value="${sessionScope.user.password}">
    </label><br>
    <label for="tel"><fmt:message key="page.account.tel"/>:
        <input id="tel" type="tel" name="tel" value="${sessionScope.user.tel}">
    </label><br>
    <label for="address"><fmt:message key="page.account.address"/>:
        <input id="address" type="text" name="address" value="${sessionScope.user.address}">
    </label><br>
    <button type="submit"><fmt:message key="page.account.send.button"/></button><br>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <c:if test="${sessionScope.lang eq 'ru_RU'}">
                    <span>${error.messageRu}</span>
                    <br>
                </c:if>
                <c:if test="${sessionScope.lang ne 'ru_RU'}">
                    <span>${error.messageEn}</span>
                    <br>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/delete?id=${sessionScope.user.id}" method="post">
    <button type="submit"><fmt:message key="page.account.delete.button"/></button>
</form>
</body>
</html>
