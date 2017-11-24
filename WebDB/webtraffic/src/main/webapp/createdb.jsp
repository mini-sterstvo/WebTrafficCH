<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20.11.2017
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="*/../select">перейти к выбору без создания таблицы</a>
<br><br><br>

добавить новую базу данных и таблицу
или создать таблицу в существующей бд


<form method="POST" action="select">
    имя новой или существующей БД<br>
    <input type="text" name="namedb">
    <br>
    Имя таблицы
    <br>
    <input type="text" name="nametable">
    <br>
    будет создана таблица вида:<br>
    id|date|abonent|uplink|downlink
    <br> <br> <br>
    таблица будет пустая, заполнять данными нужно самостоятельно.
    <input type="submit" value="создать">

    <br><br><br><br>
    Техническая информация:<br>
    на предыдущей странице сгенерирована ссылка: ${url}
</form>
</body>
</html>
