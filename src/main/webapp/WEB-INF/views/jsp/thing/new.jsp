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
          action="/thing" modelAttribute="thing">
            <div class="form-group">
                    <form:label path="title">Title</form:label>
                    <form:input path="title" type="text" class="form-control" placeholder="Comparator title"/>
            </div>
            <div class="form-group">
                    <form:checkbox path="isAbstract" class="form-check-input" id="isAbstractCheckBox"/>
                    <form:label path="isAbstract" class="form-check-label" for="isAbstractCheckBox">isAbstract</form:label>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="parentDropdown">Parent Thing</label>
                <div class="col-md-4">
                  <form:select path = "parentThing" class="form-control" id="parentDropdown">
                     <form:options items = "${thingList}" itemValue="thingID" itemLabel="title"/>
                  </form:select>
                </div>
            </div>
                    <input type="submit" class="btn btn-primary" value="Submit"/>
        </form:form>
</div>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

</body>
</html>
