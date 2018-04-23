<%--
  Created by IntelliJ IDEA.
  User: adil
  Date: 4/19/18
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">Name</th>
        <th width="120">Mail</th>
        <th width="120">Phone</th>
        <th width="120">City</th>
    </tr>
        <tr>
            <td>${contact.id}</td>
            <td>${contact.contactName}</td>
            <td>${contact.contactEmail}</td>
            <td>${contact.contactPhone}</td>
            <td>${contact.contactCity}</td>
            </td>
        </tr>
</table>
</body>
</html>
