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
    <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="post" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <h6>Welcome ${pageContext.request.userPrincipal.name}  |  <a onclick="document.forms['logoutForm'].submit()">Logout</a></h6>
        </c:if>

        <c:if test="${!empty listContacts}">
            <table class="tg">
                <tr>
                    <th width="80">ID</th>
                    <th width="120">Name</th>
                    <th width="120">City</th>
                    <th width="120">Tel.NO</th>
                    <th width="120">e-mail</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>
                <c:forEach items="${listContacts}" var="contact">
                    <tr>
                        <td>${contact.id}</td>
                        <td><a href="/contactData/${contact.id}" target="_blank">${contact.contactName}</a></td>
                        <td>${contact.contactCity}</td>
                        <td>${contact.contactPhone}</td>
                        <td>${contact.contactEmail}</td>
                        <td><a href="<c:url value='/edit/${contact.id}'/>">Edit</a></td>
                        <td><a href="<c:url value='/remove/${contact.id}'/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <h5>Add contact</h5>

        <c:url var="addAction" value="/welcome/add"/>

        <form:form action="${addAction}" commandName="contact">
            <table>
                <c:if test="${!empty contact.contactName}">
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message text="ID"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        <form:label path="contactName">
                            <spring:message text="Name"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="contactName"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <form:label path="contactCity">
                            <spring:message text="City"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="contactCity"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="contactPhone">
                            <spring:message text="NO"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="contactPhone"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="contactEmail">
                            <spring:message text="Mail"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="contactEmail"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty contact.contactName}">
                            <input type="submit"
                                   value="<spring:message text="Edit Contact"/>"/>
                        </c:if>
                        <c:if test="${empty contact.contactName}">
                            <input type="submit"
                                   value="<spring:message text="Add Contact"/>"/>
                        </c:if>
                    </td>
                </tr>
            </table>

        </form:form>

        <br>
        <div class="align-content-center">
            <a href="/downloadExcel">Download Data in excel.</a>
            <a href="/downloadDoc">Download Data in doc.</a>
        </div>

        <br>
        <br>
        <div>
            <center>City Search</center>
            <%--<form:input path="contactCityForSearch"/>--%>
        </div>


    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>