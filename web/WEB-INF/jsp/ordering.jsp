<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Оформить заказ</title>
</head>
<body>
<%@include file="logout.jsp"%>
<%@include file="startline.jsp"%>
<h1>Оформить заказ:</h1>
<c:if test="${not empty requestScope.baskets}">
<form action="${pageContext.request.contextPath}/ordering?id=${sessionScope.user.id}" method="post">
    <c:forEach var="basket" items="${requestScope.baskets}">
<p>
<img src="${pageContext.request.contextPath}/images/${basket.image}" alt="Product image">
${basket.name}
<strong  style="font-size: 25px">&emsp;&emsp;${basket.price}$</strong>
<label style="float: right"  for="amount">Коллчество:
    <input id="amount" name="amount" type="number" min="1" max="${basket.amount}" value="1">&emsp;&emsp;&emsp;&emsp;
</label>
</p>
    </c:forEach>
    <button type="submit">Подтвердить</button>
</form>
</c:if>
</body>
</html>
