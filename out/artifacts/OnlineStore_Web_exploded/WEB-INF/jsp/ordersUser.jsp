<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Все пользователи</title>
    <style>
        .but {
            border: 0px;
        }

        .tab {
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp" %>
<%@include file="startline.jsp" %>
<h1>Все пользователи:</h1>
<table border="1px" class="tab">
    <tr>
        <th width="50">Номер</th>
        <th width="600">Товары и количество в заказе</th>
        <th width="80">Дата оформления заказа</th>
        <th width="80">Дата закрытия заказа</th>
        <th width="120">Статус</th>
        <th width="60">Цена</th>
    </tr>
    <c:forEach var="order" items="${requestScope.orders}">
        <tr>
            <td width="50">${order.id}</td>
            <td width="600">${order.product}</td>
            <td width="80">${order.dateRegistration}</td>
            <td width="80">
                <c:if test="${order.dateClose ne '1900-01-01'}">
                    ${order.dateClose}
                </c:if>
            </td>
            <td width="120">
                <c:if test="${order.status eq 'PROCESSING'}">На обработке</c:if>
                <c:if test="${order.status eq 'ACCEPTED'}">Принят</c:if>
                <c:if test="${order.status eq 'REJECTED'}">Отклонен</c:if>
                <c:if test="${order.status eq 'COMPLETED'}">Выполнен</c:if>
            </td>
            <td width="60">${order.total}$</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
