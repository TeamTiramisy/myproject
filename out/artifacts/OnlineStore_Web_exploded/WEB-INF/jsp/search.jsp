<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-size: 20px
        }
        .bt {
            margin-left: 20px;
        }

        .f {
            float: right;
            text-decoration: none;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp"%>
<%@include file="startline.jsp"%>
<c:choose>
    <c:when test="${not empty param.name && not empty requestScope.technics}">
        <h1>Результаты поиска «${param.name}»</h1>
        <ul>
            <c:forEach var="technic" items="${requestScope.technics}">
                <p>
                <c:if test="${technic.amount > 0}">
                    <form style="float: right" action="${pageContext.request.contextPath}/basket?id=${technic.id}"
                          method="post">
                        <button class="bt" type="submit">В корзину</button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <a class="f" href="${pageContext.request.contextPath}/update?id=${technic.id}">
                        <button class="bt" type="button">Изменить</button>
                    </a>
                </c:if>
                <img src="${pageContext.request.contextPath}/images/${technic.image}" alt="Product image">
                <a href="${pageContext.request.contextPath}/product?id=${technic.id}">${technic.name}</a>
                <strong>&emsp;&emsp;${technic.price}$</strong>
                <c:if test="${technic.amount eq 0}">
                    <span style="float: right">Нет в наличии</span>
                </c:if>
                </p>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <h1>Результаты поиска «${param.name}»</h1>
        <img src="https://5element.by/desktop/images/multi-search-empty.png" alt="image">
    </c:otherwise>
</c:choose>
</body>
</html>
