<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>OnlineStore</title>
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
<c:choose>
    <c:when test="${not empty requestScope.categories}">
        <%@include file="logout.jsp" %>
        <%@include file="startline.jsp"%>
        <c:if test="${param.category eq 'PHONE'}">
            <h1><fmt:message key="page.categories.phone"/>:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'REFRIGERATOR'}">
            <h1><fmt:message key="page.categories.refrigerator"/>:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'TV'}">
            <h1><fmt:message key="page.categories.tv"/>:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'COMPUTER'}">
            <h1><fmt:message key="page.categories.computer"/>:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'LAPTOP'}">
            <h1><fmt:message key="page.categories.laptop"/>:<br></h1>
        </c:if>
        <c:if test="${param.category eq 'CONSOLE'}">
            <h1><fmt:message key="page.categories.console"/>:<br></h1>
        </c:if>
        <div>
            <c:forEach var="category" items="${requestScope.categories}">
                <p>
                <c:if test="${category.amount > 0 &&  sessionScope.user.blackList eq 'NO'}">
                    <form style="float: right" action="${pageContext.request.contextPath}/basket?id=${category.id}"
                          method="post">
                        <button class="bt" type="submit"><fmt:message key="page.product.button"/></button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <a class="f" href="${pageContext.request.contextPath}/update?id=${category.id}">
                        <button class="bt" type="button"><fmt:message key="page.search.update"/></button>
                    </a>
                </c:if>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <a class="f" href="${pageContext.request.contextPath}/ordersProduct?name=${category.name}">
                        <button class="bt" type="button"><fmt:message key="page.search.orders"/></button>
                    </a>
                </c:if>
                <img src="${pageContext.request.contextPath}/images/${category.image}" alt="Product image">
                <a href="${pageContext.request.contextPath}/product?id=${category.id}">${category.name}</a>
                <strong>&emsp;&emsp;${category.price}$</strong>
                <c:if test="${category.amount eq 0}">
                    <span style="float: right"><fmt:message key="page.product.text"/></span>
                </c:if>
                </p>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise><%@include file="500.jsp"%></c:otherwise>
</c:choose>
</body>
</html>
