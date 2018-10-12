<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
  <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>

<body>
<div class="container">

        <form:form method="POST"
          action="/user" modelAttribute="userStub">
  <div class="form-group">
                    <form:label path="username">Username</form:label>
                    <form:input path="username" type="text" class="form-control" placeholder="Username title"/>
  </div>
                    <input type="submit" class="btn btn-primary" value="Submit"/>
            </table>
        </form:form>
</div>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

</body>
</html>
