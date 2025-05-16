<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang.language : 'ru'}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.lang != null ? sessionScope.lang.language : 'ru'}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="login.title"/></title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        input { display: block; margin: 10px 0; padding: 5px; width: 300px; }
    </style>
</head>
<body>

<h1><fmt:message key="login.header"/></h1>

<c:if test="${not empty error}">
    <p style="color:red;"><c:out value="${error}"/></p>
</c:if>

<form method="post" action="login">
    <label><fmt:message key="login.email"/></label>
    <input type="email" name="email" required>

    <label><fmt:message key="login.password"/></label>
    <input type="password" name="password" required>

    <input type="submit" value="<fmt:message key='login.submit'/>">
</form>

<p>
    <a href="register.jsp"><fmt:message key="login.registerLink"/></a>
</p>

<p>
    <fmt:message key="language.choose"/>:
    <a href="?lang=ru">🇷🇺 Русский</a> |
    <a href="?lang=en">🇬🇧 English</a>
</p>

</body>
</html>
