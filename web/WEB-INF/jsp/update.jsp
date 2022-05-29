<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>OnlineStore</title>
    <style>
        body {line-height: 1.5}
    </style>
</head>
<body>
<%@include file="logout.jsp" %>
<%@include file="startline.jsp"%>
<h1><fmt:message key="page.update.h"/>:</h1>
<form action="${pageContext.request.contextPath}/update?id=${requestScope.product.id}" method="post">
    <label  for="name"><fmt:message key="page.add.name"/>:
        <input id="name" type="text" name="name" value="${requestScope.product.name}">
    </label><br>
    <label for="description"><fmt:message key="page.add.description"/>:
        <input id="description" type="text" name="description" value="${requestScope.product.description}">
    </label><br>
    <label for="price"><fmt:message key="page.add.price"/>:
        <input id="price" type="number" name="price" value="${requestScope.product.price}">
    </label><br>
    <label for="amount"><fmt:message key="page.add.amount"/>:
        <input id="amount" type="number" name="amount" value="${requestScope.product.amount}">
    </label><br>
    <button type="submit"><fmt:message key="page.search.update"/></button>
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
<form action="${pageContext.request.contextPath}/deletion?id=${requestScope.product.id}" method="post">
    <button type="submit"><fmt:message key="page.update.button"/></button>
</form>
</body>
</html>
