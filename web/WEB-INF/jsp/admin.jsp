<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 15.05.2022
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OnlineStore</title>
    <style>
        p {
            font-size: 25px;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp" %>
<%@include file="startline.jsp" %>
<h1><fmt:message key="page.admin.h"/>:</h1>
<a href="${pageContext.request.contextPath}/add">
    <p><fmt:message key="page.add.h"/> </p>
</a>
<a href="${pageContext.request.contextPath}/users">
    <p><fmt:message key="page.admin.users"/></p>
</a>
<a href="${pageContext.request.contextPath}/orders">
    <p><fmt:message key="page.admin.orders"/></p>
</a>
<a href="${pageContext.request.contextPath}/orders/processing">
    <p><fmt:message key="page.admin.orders.processing"/></p>
</a>
<a href="${pageContext.request.contextPath}/orders/completed">
    <p><fmt:message key="page.admin.orders.completed"/></p>
</a>

</body>
</html>
