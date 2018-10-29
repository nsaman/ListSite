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
          action="/descriptorType" modelAttribute="descriptorType">
            <div class="form-group">
                    <form:label path="title">Title</form:label>
                    <form:input path="title" type="text" class="form-control" placeholder="Descriptor Type title"/>
            </div>
            <div class="form-group">
                    <form:checkbox path="isNullable" class="form-check-input" id="isNullableCheckBox"/>
                    <form:label path="isNullable" class="form-check-label" for="isNullableCheckBox">isNullable</form:label>
            </div>
            <div class="form-group">
                    <form:checkbox path="isUnique" class="form-check-input" id="isUniqueCheckBox"/>
                    <form:label path="isUnique" class="form-check-label" for="isUniqueCheckBox">isUnique</form:label>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="valueTypeDropdown">Value Type</label>
                <div class="col-md-4">
                  <form:select path="valueType" class="form-control" id="valueTypeDropdown">
                     <form:options items="${descriptorTypes}" itemValue="typeName" itemLabel="typeName"/>
                  </form:select>
                </div>
            </div>
            <input type="submit" class="btn btn-primary" value="Submit"/>
        </form:form>
</div>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

</body>
</html>
