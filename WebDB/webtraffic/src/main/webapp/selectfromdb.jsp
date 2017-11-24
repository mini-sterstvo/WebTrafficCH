<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.11.2017
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
выбор из БД

<form method="POST" action="answer">

    <br>
    <br>дата от<br>
    <input type="datetime-local" name="day1">
    секунды
    <input type="number" min="0" max="59" name="second1">

    <br><br>
    <br>дата до<br>
    <input type="datetime-local" name="day2">
    секунды
    <input type="number" min="0" max="59" name="second2">
    <br>
    Важно: Приложение не провеhяет соответствие min и max. Даты должны отличаться хотябы на 1 секунду

    <br><br>
    направление
    <select name="link">   <option value="uplink">UpLink</option>
        <option value="downlink">DownLink</option></select>
    <br>
    Абонент
    <select name="abonent">${abonent}
    </select>
    <input type="submit" value="считать">
</form>
</body>
</html>
