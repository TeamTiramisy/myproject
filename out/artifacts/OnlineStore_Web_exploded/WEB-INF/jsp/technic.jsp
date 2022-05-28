<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<%@include file="logout.jsp" %>
<%@include file="startline.jsp"%>
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
                <c:if test="${category.amount > 0 &&  sessionScope.user.blackList eq 'NO'}">
                    <form style="float: right" action="${pageContext.request.contextPath}/basket?id=${category.id}"
                          method="post">
                        <button class="bt" type="submit">В корзину</button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <a class="f" href="${pageContext.request.contextPath}/update?id=${category.id}">
                        <button class="bt" type="button">Изменить</button>
                    </a>
                </c:if>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <a class="f" href="${pageContext.request.contextPath}/ordersProduct?name=${category.name}">
                        <button class="bt" type="button">Заказы с товаром</button>
                    </a>
                </c:if>
                <img src="${pageContext.request.contextPath}/images/${category.image}" alt="Product image">
                <a href="${pageContext.request.contextPath}/product?id=${category.id}">${category.name}</a>
                <strong>&emsp;&emsp;${category.price}$</strong>
                <c:if test="${category.amount eq 0}">
                    <span style="float: right">Нет в наличии</span>
                </c:if>
                </p>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise><span>Error</span></c:otherwise>
</c:choose>
</body>
</html>
