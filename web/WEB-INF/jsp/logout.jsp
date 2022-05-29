<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .btn {
        background: dodgerblue;
        color: white;
        padding: 5px 16px;
        border: 0px transparent;
        text-align: right;
        margin-bottom: 1em;
    }

    a {
        text-decoration: none;
    }
</style>

<div style="text-align: right">
    <%@include file="locale.jsp"%>
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/admin">
            <button class="btn" type="button"><fmt:message key="page.logout.admin"/></button>
        </a>
    </c:if>
    <a href="${pageContext.request.contextPath}/account">
        <button class="btn" type="button"><fmt:message key="page.logout.account"/></button>
    </a>

    <a href="${pageContext.request.contextPath}/myOrders">
        <button class="btn" type="button"><fmt:message key="page.logout.orders"/></button>
    </a>
    <c:if test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/login">
            <button class="btn" type="button">Вход</button>
        </a>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/basket">
            <button class="btn" type="button"><fmt:message key="page.logout.basket"/></button>
        </a>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <form action="/logout" method="post">
            <button class="btn" type="submit"><fmt:message key="page.logout.exit"/></button>
        </form>
    </c:if>
</div>
