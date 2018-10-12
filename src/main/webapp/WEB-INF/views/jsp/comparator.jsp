<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>

<div class="container">
<p>${comparator.comparatorID}</p>
<p>${comparator.title}</p>
<p><a href="/user/${comparator.createUserID}">${comparator.createUserID}</a></p>
<p><a href="/user/${comparator.changeUserID}">${comparator.changeUserID}</a></p>
</div>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>