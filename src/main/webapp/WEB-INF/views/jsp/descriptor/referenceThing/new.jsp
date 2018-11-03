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
          action="/descriptor/referenceThing" modelAttribute="descriptor">
            <div class="form-group">
                <label class="col-md-4 control-label" for="describedThingDropdown">Described Thing</label>
                <div class="col-md-4">
                  <form:select path="describedThing" class="form-control" id="describedThingDropdown">
                     <form:options items="${thingList}" itemValue="thingID" itemLabel="title"/>
                  </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="descriptorTypeDropdown">DescriptorType</label>
                <div class="col-md-4">
                  <form:select path = "descriptorType" class="form-control" id="descriptorTypeDropdown">
                     <form:options items = "${descriptorTypeList}" itemValue="descriptorTypeID" itemLabel="title"/>
                  </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="referenceThingDropdown">Reference Thing</label>
                <div class="col-md-4">
                  <form:select path="referenceThing" class="form-control" id="referenceThingDropdown">
                     <form:option value=""></form:option>
                     <form:options items="${thingList}" itemValue="thingID" itemLabel="title"/>
                  </form:select>
                </div>
            </div>
            <input type="submit" class="btn btn-primary" value="Submit"/>
        </form:form>
</div>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

</body>
</html>
