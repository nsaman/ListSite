<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>

<div class="container">
<p>${thing.thingID}</p>
<p>${thing.title}</p>
<p>${thing.isAbstract}</p>
<p><a href="/user/${thing.createUserID}">${thing.createUserID}</a></p>
<p><a href="/user/${thing.changeUserID}">${thing.changeUserID}</a></p>
<p><a href="/thing/${thing.parentThing.thingID}">${thing.parentThing.title}</a></p>
<c:forEach items="${thing.descriptors}" var="descriptor">
    <a href="/descriptorType/${descriptor.descriptorType.descriptorTypeID}">${descriptor.descriptorType.title}</a>: <a href="/descriptor/${descriptor.descriptorID}">${descriptor.stringValue}</a><br>
</c:forEach>
Children:
<c:forEach items="${children}" var="child">
    <a href="/thing/${child.thingID}">${child.title}</a>
</c:forEach>

</div>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>