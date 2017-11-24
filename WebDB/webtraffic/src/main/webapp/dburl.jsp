<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20.11.2017
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
Подключаемся к базе данных.

Введите
<form method="POST" action="createdb">
    url в фомате: 127.0.0.1:3306/<br>
    <input type="text" name="url">
    <br>
    log:<br>
    <input type="text" name="log">
    <br>
    pas:<br>
    <input type="text" name="pas">
    <br>
    <br><br>
    Введите имена существующей пары "база данных"."таблица"<br>Если ее нет, оставьте поле пустым. Создание будет предложено далее.
    <br>Имя БД:<br>
    <input type="text" name="namedb">
    <br> Имя таблицы:<br>
    <input type="text" name="nametb">
    <br>
    Важно: Данное приложение не проверяет корректность и существование введенных имен.<br>
    <input type="submit" value="Далее">
</form>
</body>
</html>
