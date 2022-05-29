<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
</head>
<body>
<fmt:setLocale value="${sessionScope.lang != null
                            ? sessionScope.lang
                            : (param.lang != null ? param.lang : 'en_US')}"/>
<fmt:setBundle basename="translations"/>
</body>
</html>
