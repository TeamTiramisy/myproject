<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>OnlineStore</title>
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
<h1><fmt:message key="page.processing.h"/>:</h1>
<table border="1px" class="tab">
    <tr>
        <th width="50"><fmt:message key="page.completed.number"/> </th>
        <th width="600"><fmt:message key="page.completed.product"/></th>
        <th width="80"><fmt:message key="page.completed.date"/></th>
        <th width="80"><fmt:message key="page.completed.data"/></th>
        <th width="120"><fmt:message key="page.completed.status"/></th>
        <th width="60"><fmt:message key="page.completed.price"/></th>
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
                <c:if test="${order.status eq 'PROCESSING'}"><fmt:message key="page.myOrders.processing"/></c:if>
            </td>
            <td width="60">${order.total}$</td>
                <td class="but">
                    <form action="${pageContext.request.contextPath}/orders/processing?id=${order.id}" method="post">
                        <button type="submit"><fmt:message key="page.processing.accept"/></button>
                    </form>
                </td>
                <td class="but">
                    <form action="${pageContext.request.contextPath}/ordersProduct?id=${order.id}" method="post">
                        <button type="submit"><fmt:message key="page.processing.reject"/></button>
                    </form>
                </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
