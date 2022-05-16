<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty param.name && not empty requestScope.technics}">
        <h1>Результаты поиска «${param.name}»</h1>
<ul>
    <c:forEach var="technic" items="${requestScope.technics}">
        <img src="${pageContext.request.contextPath}/images/${technic.image}" alt="Product image">
        <a href="${pageContext.request.contextPath}/product?id=${technic.id}">${technic.name}</a><br>
    </c:forEach>
</ul>
    </c:when>
    <c:otherwise>
        <h1>Результаты поиска «${param.name}»</h1>
        <img src="https://5element.by/public/desktop/images/multi-search-empty.png" alt="image">
    </c:otherwise>
</c:choose>
</body>
</html>
