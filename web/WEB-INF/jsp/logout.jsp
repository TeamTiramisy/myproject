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
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/admin">
            <button class="btn" type="button">Кабинет админа</button>
        </a>
    </c:if>
    <a href="${pageContext.request.contextPath}/profile">
        <button class="btn" type="button">Мой профель</button>
    </a>
    <c:if test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/login">
            <button class="btn" type="button">Вход</button>
        </a>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/basket">
            <button class="btn" type="button">Корзина</button>
        </a>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <form action="/logout" method="post">
            <button class="btn" type="submit">Выйти</button>
        </form>
    </c:if>
</div>
