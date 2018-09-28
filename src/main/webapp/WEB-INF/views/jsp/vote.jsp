<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>

<div class="container">
<p>${vote.voteID}</p>
<p><a href="/comparator/${vote.comparator.comparatorID}">${vote.comparator.title}</a></p>
<p><a href="/thing/${vote.winnerThing.thingID}">${vote.winnerThing.title}</a></p>
<p><a href="/thing/${vote.loserThing.thingID}">${vote.loserThing.title}</a></p>
<p><a href="/user/${vote.user.userID}">${vote.user.userID}</a></p>
</div>

<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>