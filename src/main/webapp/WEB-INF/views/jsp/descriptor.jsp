<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>

<div class="container">
<p>${descriptor.descriptorID}</p>
<p><a href="/thing/${descriptor.describedThing.thingID}">${descriptor.describedThing.title}</a></p>
<p><a href="/descriptortype/${descriptor.descriptorType.descriptorTypeID}">${descriptor.descriptorType.title}</a></p>
<p><a href="/user/${descriptor.createUserID}">${descriptor.createUserID}</a></p>
<p><a href="/user/${descriptor.changeUserID}">${descriptor.changeUserID}</a></p>
<p><a href="/thing/${descriptor.referenceThing.thingID}">${descriptor.referenceThing.title}</a></p>
<p>${descriptor.stringValue}</p>
<p>${descriptor.intValue}</p>
<p>${descriptor.doubleValue}</p>
<p>${descriptor.dateValue}</p>
<p>${descriptor.longitudeValue}</p>
<p>${descriptor.latitudeValue}</p>
<p>${descriptor.resourceValue}</p>
</div>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>