<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>OnlineStore</title>
    <style>
        body {font-size: 25px}
        h1 {
            font-size: 40px;
            text-align: center;
        }
        ul
        {
            text-align: center;
            letter-spacing: 50px;
        }
        a {
            letter-spacing: 0;
            color: white;
            text-decoration: none;
        }
        .grad{
            background: linear-gradient(to right, darkorchid, crimson);
            padding: 10px;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp"%>
<h1><fmt:message key="page.categories.h"/>:</h1>
<%@include file="startline.jsp"%>
<ul class="grad">
    <c:forEach  var="technic" items="${requestScope.technics}">
        <c:if test="${technic eq 'PHONE'}">
            <a  href="${pageContext.request.contextPath}/technic?category=${technic}"><fmt:message key="page.categories.phone"/></a>
        </c:if>
        <c:if test="${technic eq 'REFRIGERATOR'}">
            <a href="${pageContext.request.contextPath}/technic?category=${technic}"><fmt:message key="page.categories.refrigerator"/></a>
        </c:if>
        <c:if test="${technic eq 'TV'}">
            <a href="${pageContext.request.contextPath}/technic?category=${technic}"><fmt:message key="page.categories.tv"/></a>
        </c:if>
        <c:if test="${technic eq 'COMPUTER'}">
            <a href="${pageContext.request.contextPath}/technic?category=${technic}"><fmt:message key="page.categories.computer"/></a>
        </c:if>
        <c:if test="${technic eq 'LAPTOP'}">
            <a href="${pageContext.request.contextPath}/technic?category=${technic}"><fmt:message key="page.categories.laptop"/></a>
        </c:if>
        <c:if test="${technic eq 'CONSOLE'}">
            <a href="${pageContext.request.contextPath}/technic?category=${technic}"><fmt:message key="page.categories.console"/></a>
        </c:if>

    </c:forEach>
</ul>
<img src="https://telsgroup.ru/upload/medialibrary/4c2/8odb4m6x7hl0663l8oip50sdl1xgm3qf.png" alt="">
<div style="text-align: center"><%@include file="lang.jsp"%></div>
</body>
</html>
