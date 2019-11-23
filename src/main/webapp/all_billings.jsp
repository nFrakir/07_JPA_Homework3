<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.gmail.silverleaf.annn.dbobjects.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bank -> user's billings</title>
    <meta charset="utf-8">
    <link rel='stylesheet' href='style.css' type='text/css' media='all' />
</head>
<body>
<div class="frm">
    <h2>All user's billings</h2>
    <form name="choose_user" action="/all_billings" method="post">
        <fieldset>
            <legend>Current user is <c:out value="${currentUser}"/></legend>
            <label  for="user_id">Choose another user: </label>
            <select class="elements" id="user_id" name="user_id">
                <c:forEach items="${users}" var="user">
                    <option value="<c:out value="${user.getId()}"/>"><c:out value="${user.getLogin()}"/></option>
                </c:forEach>
            </select>
            <input type="submit" value="Send">
        </fieldset>
    </form>
</div>
<div class="tbl">
    <table class="borders">
        <thead>
            <th>Currency</th>
            <th>Sum</th>
        </thead>
        <tbody>
            <c:forEach items="${billings}" var="billing">
                <tr>
                    <td><c:out value="${billing.getCurrency().getAbbreviation()}"/></td>
                    <td><c:out value="${billing.getTotal()}"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="2"><b>Total sum in UAH:</b></td>
            </tr>
            <tr>
                <td>---</td>
                <td><c:out value="${totalUAH}"/></td>
            </tr>
        </tbody>
    </table>
</div>
<div class="tbl">
    <a href="index.html">Return to main page</a>
    <br>
    <c:if test="${error eq true}">
        <c:out value="${error_msg}"/>
    </c:if>
</div>
</body>
</html>