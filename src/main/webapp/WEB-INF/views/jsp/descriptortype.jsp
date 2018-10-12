<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>

<div class="container">
<p>${descriptorType.descriptorTypeID}</p>
<p>${descriptorType.title}</p>
<p>${descriptorType.valueType}</p>
<p>${descriptorType.isNullable}</p>
<p><a href="/user/${descriptorType.createUserID}">${descriptorType.createUserID}</a></p>
<p><a href="/user/${descriptorType.changeUserID}">${descriptorType.changeUserID}</a></p>
</div>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>