<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="add_product.title"/></title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        input[type="text"], input[type="number"], textarea {
            width: 300px;
            padding: 8px;
            margin: 5px 0 15px 0;
            display: block;
        }
        textarea {
            height: 100px;
        }
    </style>
</head>
<body>

<h1><fmt:message key="add_product.header"/></h1>

<form method="post" action="add-product">
    <label><fmt:message key="add_product.name"/></label>
    <input type="text" name="name" required>

    <label><fmt:message key="add_product.description"/></label>
    <textarea name="description"></textarea>

    <label><fmt:message key="add_product.price"/></label>
    <input type="number" name="price" min="0" step="0.01" required>

    <label><fmt:message key="add_product.stock"/></label>
    <input type="number" name="stock" min="0" required>

    <input type="submit" value="<fmt:message key='add_product.submit'/>">
</form>

<p><a href="${pageContext.request.contextPath}/admin">
    <fmt:message key="add_product.back"/>
</a></p>

</body>
</html>