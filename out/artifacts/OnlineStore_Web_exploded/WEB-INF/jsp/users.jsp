<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<h1><fmt:message key="page.users.h"/>:</h1>
<ul>
    <c:forEach var="user" items="${requestScope.users}">
        <p>
            <a href="${pageContext.request.contextPath}/user?id=${user.id}">${user.firstname} ${user.lastname} ${user.email}</a>
        </p>
    </c:forEach>
</ul>
</body>
</html>
