<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="access_denied.title"/></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
            color: #333;
        }
        h1 {
            color: #d9534f;
            font-size: 2.5em;
        }
        a {
            color: #337ab7;
            text-decoration: none;
            font-size: 1.2em;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>🚫 <fmt:message key="access_denied.header"/></h1>
    <p><fmt:message key="access_denied.message"/></p>
    <a href="products">← <fmt:message key="access_denied.back_link"/></a>
</body>
</html>