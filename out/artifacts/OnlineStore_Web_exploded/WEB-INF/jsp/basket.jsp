<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Корзина</title>
</head>
<body>
<%@include file="logout.jsp"%>
<%@include file="startline.jsp"%>
<h1>Корзина:</h1>
<c:forEach var="basket" items="${requestScope.baskets}">
    <p>
    <form style="float: right" action="${pageContext.request.contextPath}/clear?userId=${sessionScope.user.id}&technicId=${basket.id}" method="post">
        <button class="bt" type="submit">Удалить</button>
    </form>
    <img src="${pageContext.request.contextPath}/images/${basket.image}" alt="Product image">
    ${basket.name}
    <strong  style="font-size: 25px">&emsp;&emsp;${basket.price}$</strong>
    </p>
</c:forEach>
<c:if test="${not empty requestScope.baskets}">
<a href="${pageContext.request.contextPath}/ordering">
    <button class="btn" type="button">Оформить заказ</button>
</a>
</c:if>
</body>
</html>
