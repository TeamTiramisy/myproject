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
<%@include file="logout.jsp"%>
<%@include file="startline.jsp"%>
<h1><fmt:message key="page.add.h"/>: </h1>
<form action="${pageContext.request.contextPath}/add" method="post" enctype="multipart/form-data">
    <label  for="name"><fmt:message key="page.add.name"/>:
        <input id="name" type="text" name="name">
    </label><br>
    <label for="category"><fmt:message key="page.add.category"/>:
        <select name="category" id="category">
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category}">${category}</option>
            </c:forEach>
        </select>
    </label><br>
    <label for="description"><fmt:message key="page.add.description"/>:
        <input id="description" type="text" name="description">
    </label><br>
    <label for="price"><fmt:message key="page.add.price"/>:
        <input id="price" type="number" name="price">
    </label><br>
    <label for="amount"><fmt:message key="page.add.amount"/>:
        <input id="amount" type="number" name="amount">
    </label><br>
    <label for="image"><fmt:message key="page.add.image"/>:
        <input id="image" type="file" name="image">
    </label><br>
    <button type="submit"><fmt:message key="page.add.add.button"/></button>
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
</body>
</html>
