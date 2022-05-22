<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .sr
        {
            font-size: 30px;
            border-radius: 20px;
        }

        .wh{
            background: linear-gradient(to right, white, grey);
            padding: 10px;
        }
    </style>
</head>
<body>
<form class="wh" action="${pageContext.request.contextPath}/search">
    <a href="/">
    <img src="https://freelance.ru/img/portfolio/pics/00/1C/17/1840940.jpg" alt="" height="100" width="200">
    </a>
    <input class="sr"  id="name" type="search" name="name" placeholder="&#128269; Поиск товаров">
</form>
</body>
</html>
