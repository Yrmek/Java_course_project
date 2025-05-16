<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="register.title"/></title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        input { display: block; margin: 10px 0; padding: 5px; width: 300px; }
    </style>
</head>
<body>

<h1><fmt:message key="register.header"/></h1>

<c:if test="${not empty error}">
    <p style="color:red;"><c:out value="${error}"/></p>
</c:if>

<form method="post" action="register">
    <label><fmt:message key="register.email"/></label>
    <input type="email" name="email" required>

    <label><fmt:message key="register.password"/></label>
    <input type="password" name="password" required>

    <input type="submit" value="<fmt:message key='register.submit'/>">
</form>

<p>
    <fmt:message key="register.already_registered"/>
    <a href="login.jsp"><fmt:message key="register.login_link"/></a>
</p>

</body>
</html>