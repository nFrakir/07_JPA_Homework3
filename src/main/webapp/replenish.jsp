<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.gmail.silverleaf.annn.dbobjects.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bank -> replenishing money</title>
    <meta charset="utf-8">
    <link rel='stylesheet' href='style.css' type='text/css' media='all' />
</head>
<body>
<div class="frm">
    <h2>Replenish billing</h2>
    <form name="replenish" action="/replenish" method="post">
        <fieldset>
            <legend>Fill in all fields</legend>
            <table class="borders">
                <tbody>
                    <tr>
                        <td><label  for="user_id">Choose user: </label></td>
                        <td><select class="longelements" id="user_id" name="user_id">
                            <c:forEach items="${users}" var="user">
                                <option value="<c:out value="${user.getId()}"/>"><c:out value="${user.getLogin()}"/></option>
                            </c:forEach>
                        </select></td>
                    </tr>

                    <tr>
                        <td><label  for="currency_id">Choose currency: </label></td>
                        <td><select class="longelements" id="currency_id" name="currency_id">
                            <c:forEach items="${currencies}" var="currency">
                                <option value="<c:out value="${currency.getId()}"/>"><c:out value="${currency.getAbbreviation()}"/></option>
                            </c:forEach>
                        </select></td>
                    </tr>

                    <tr>
                        <td><label  for="sum">Enter sum: </label></td>
                        <td><input type="text" class="longelements" name="sum" id="sum" value="250.0"></td>
                    </tr>

                    <tr>
                        <td colspan="2"><input type="submit" class="btn" value="Send"></td>
                    </tr>
                </tbody>
            </table>
        </fieldset>
    </form>
</div>
<div class="tbl">
    <a href="index.html">Return to main page</a>
    <br>
    <c:if test="${error eq true}">
        <c:out value="${error_msg}"/>
    </c:if>
    <br>
    <c:if test="${error ne true}">
        Operation success!
    </c:if>
</div>
</body>
</html>