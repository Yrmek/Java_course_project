<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="order_completed.title"/></title>
</head>
<body>

<h1><fmt:message key="order_completed.header"/></h1>
<p><fmt:message key="order_completed.message"/></p>
<p><a href="products"><fmt:message key="order_completed.back_to_catalog"/></a></p>

</body>
</html>