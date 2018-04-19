<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="post" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <h2>Welcome ${pageContext.request.userPrincipal.name}  |  <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
        </c:if>
    </div>

    <c:if test="${!empty listBooks}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="120">Title</th>
                <th width="120">Author</th>
                <th width="120">Price</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listBooks}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td><a href="/bookdata/${book.id}" target="_blank">${book.bookTitle}</a></td>
                    <td>${book.bookAuthor}</td>
                    <td>${book.price/100}${book.price%100}</td>
                    <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>