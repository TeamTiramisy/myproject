<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.product.name}</title>
    <style>
        body {font-size: 15px}
        h1 {font-size: 25px}
        .s {
            margin-left: 200px;
            font-size: 25px;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp"%>
<h1>${requestScope.product.name}</h1>
<img src="${pageContext.request.contextPath}/images/${requestScope.product.image}" alt="Product image">
<strong class="s">${requestScope.product.price}$</strong>
<c:if test="${requestScope.product.amount > 0}">
    <a style="text-decoration: none" href="${pageContext.request.contextPath}/xxx">
        <button style="margin-left: 200px" type="button">Оформить заказ</button>
    </a>
</c:if>
<c:if test="${requestScope.product.amount eq 0}">
    <span class="s">Нет в наличии</span>
</c:if>
<p>${requestScope.product.description}</p>
</body>
</html>
