<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 15.05.2022
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .btn{
            background: dodgerblue;
            color: white;
            padding: 5px 16px;
            border: 0px transparent;
            margin-bottom: 1em;
        }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/add">
    <button class="btn" type="button">Добавить товар</button>
</a><br>
<a href="${pageContext.request.contextPath}/users">
    <button class="btn" type="button">Все пользователи</button>
</a>

</body>
</html>
