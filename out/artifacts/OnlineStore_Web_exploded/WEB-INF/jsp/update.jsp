<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Изменить товар</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/update?id=${requestScope.product.id}" method="post">
    <label  for="name">Имя
        <input id="name" type="text" name="name" value="${requestScope.product.name}">
    </label><br>
    <label for="description">Описание
        <input id="description" type="text" name="description" value="${requestScope.product.description}">
    </label><br>
    <label for="price">Цена
        <input id="price" type="number" name="price" value="${requestScope.product.price}">
    </label><br>
    <label for="amount">Количество
        <input id="amount" type="number" name="amount" value="${requestScope.product.amount}">
    </label><br>
    <button type="submit">Изменить</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>