<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sanit
  Date: 3/8/22
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%--63130500055 Tarathep Siripis--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
          <h1>Transaction History</h1>
          <a class="button" href="beverage-list"> &lt; </a> &nbsp;&nbsp; <a class="button" href="account?action=logout">Logout</a>
          <table>
              <tr>
                  <th>Account No</th>
                  <td colspan="2">${account.mobileNo}</td>
              </tr>
              <tr>
                  <th>Last Buy</th>
                  <td colspan="2"><f:formatDate value="${account.lastBuy}" pattern="dd-MMM-yyyy hh:mm:ss"/></td>
              </tr>
              <tr>
                  <th>Total amount</th>
                  <td colspan="2">${account.totalAmount}</td>
              </tr>
              <tr>
                  <th>Tran Date</th>
                  <th>Drink (quantity)</th>
                  <th class="center">Amount</th>
              </tr>
              <c:forEach items="${account.transactions}" var="transaction">
                  <tr>
                      <td><f:formatDate value="${transaction.tranDate}" pattern="dd-MMM-yyyy"/></td>
                      <td>${transaction.drink.drink} (1 unit)</td>
                      <td class="center">${transaction.amount}</td>
                  </tr>
              </c:forEach>
          </table>
    </div>
</body>
</html>
