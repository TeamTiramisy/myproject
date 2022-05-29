<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>OnlineStore</title>
    <style>
        body {line-height: 1.5}
    </style>
</head>
<body>
<%@include file="locale.jsp"%>
<h3><fmt:message key="page.refrigeration.h"/>:</h3>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <label  for="firstname"><fmt:message key="page.account.firstname"/>:
            <input id="firstname" type="text" name="firstname">
        </label><br>
        <label for="lastname"><fmt:message key="page.account.lastname"/>:
            <input id="lastname" type="text" name="lastname">
        </label><br>
        <label for="email"><fmt:message key="page.account.email"/>:
            <input id="email" type="email" name="email">
        </label><br>
        <label for="password"><fmt:message key="page.account.password"/>:
            <input id="password" type="password" name="password">
        </label><br>
        <label for="tel"><fmt:message key="page.account.tel"/>:
            <input id="tel" type="tel" name="tel">
        </label><br>
        <label for="address"><fmt:message key="page.account.address"/>:
            <input id="address" type="text" name="address">
        </label><br>
        <input type="radio" name="gender" value="${requestScope.genders[0]}"><fmt:message key="page.refrigeration.male"/>
        <br>
        <input type="radio" name="gender" value="${requestScope.genders[1]}"><fmt:message key="page.refrigeration.female"/>
        <br>
        <button type="submit"><fmt:message key="page.refrigeration.button"/></button>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <c:if test="${sessionScope.lang eq 'ru_RU'}">
                        <span>${error.messageRu}</span>
                        <br>
                    </c:if>
                    <c:if test="${sessionScope.lang ne 'ru_RU'}">
                        <span>${error.messageEn}</span>
                        <br>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
    </form>
</body>
</html>
