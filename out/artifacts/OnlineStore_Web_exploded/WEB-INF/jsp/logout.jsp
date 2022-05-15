<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    button
    {
        background: dodgerblue;
        color: white;
        padding: 5px 16px;
        border: 0px transparent;
        text-align: right;
        margin-bottom: 1em;
    }
    div
    {
        text-align: right;
    }
</style>

<div>
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/admin">
            <button type="button">Кабинет админа</button>
        </a>
    </c:if>
    <c:if test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/login">
            <button type="button">Вход</button>
        </a>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
    <form action="/logout" method="post">
        <button type="submit">Выйти</button>
    </form>
    </c:if>
</div>
