<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>

<div class="container">
<p>${compares.comparesID}</p>
<p><a href="/thing/${compares.thing.thingID}">${compares.thing.title}</a></p>
<p><a href="/comparator/${compares.comparator.comparatorID}">${compares.comparator.title}</a></p>
<p>${compares.score}</p>
<p>${compares.createUserID}</p>
<p>${compares.changeUserID}</p>
</div>

<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>