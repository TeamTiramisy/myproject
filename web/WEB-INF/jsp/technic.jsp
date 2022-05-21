<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${param.category}</title>
    <style>
        body {
            font-size: 20px
        }

        h1 {
            font-size: 40px
        }
        .bt
        {
            margin-left: 20px;
        }
    </style>
</head>
<body>
<%@include file="logout.jsp" %>
<c:choose>
    <c:when test="${not empty requestScope.categories}">
        <c:if test="${param.category eq 'PHONE'}">
            <h1>Телефоны:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'REFRIGERATOR'}">
            <h1>Холодильники:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'TV'}">
            <h1>Телевизоры:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'COMPUTER'}">
            <h1>Компьюторы:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'LAPTOP'}">
            <h1>Ноутбуки:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'CONSOLE'}">
            <h1>Игровые приставки:<br></h1>
        </c:if>
        <div>
        <c:forEach var="category" items="${requestScope.categories}">
            <p>
            <img src="${pageContext.request.contextPath}/images/${category.image}" alt="Product image">
            <a href="${pageContext.request.contextPath}/product?id=${category.id}">${category.name}</a>
                <span class="bt">&emsp;${category.price}$</span>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <a style="text-decoration: none" href="${pageContext.request.contextPath}/update?id=${category.id}">
                        <button class="bt" type="button">Изменить</button>
                    </a>
                </c:if>
                <c:if test="${category.amount > 0}">
                    <a style="text-decoration: none" href="${pageContext.request.contextPath}/xxx">
                        <button class="bt" type="button">Оформить заказ</button>
                    </a>
                </c:if>
                <c:if test="${category.amount eq 0}">
                    <span class="bt">Нет в наличии</span>
                </c:if>
            </p>
        </c:forEach>
        </div>
    </c:when>
    <c:otherwise><span>Error</span></c:otherwise>
</c:choose>
</body>
</html>
