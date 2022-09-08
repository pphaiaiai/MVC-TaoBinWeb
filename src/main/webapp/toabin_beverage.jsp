<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sanit
  Date: 3/8/22
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%--63130500055 Tarathep Siripis--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToaBin Beverage</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>ToaBin Beverage</h1>
<c:choose>
    <c:when test="${account == null}">
        <a class="login button" href="login">Login</a>
    </c:when>
    <c:otherwise>
        <a class="login button" href="account">Account : ${account.mobileNo}</a>
    </c:otherwise>
</c:choose>
<div class="grid-container">
    <c:forEach items="${drinks}" var="drink">
        <div class="menu">
            <span><img src="images/${drink.drink}.png" width="132px" height="168px"></span>
            <span>${drink.drink} ( ${drink.price} ) <a class="button" href="basket?id=${drink.id}&action=add">Buy</a></span>

        </div>
    </c:forEach>
</div>
</body>
</html>
