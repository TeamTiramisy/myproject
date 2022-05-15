<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.product.name}</title>
    <style>
        body {font-size: 15px}
        h1 {font-size: 25px}
    </style>
</head>
<body>
<%@include file="logout.jsp"%>
<h1>${requestScope.product.name}</h1>
<img src="${pageContext.request.contextPath}/images/${requestScope.product.image}" alt="Product image">
<p>${requestScope.product.description}</p>
</body>
</html>
