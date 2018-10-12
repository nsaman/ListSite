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
    private Comparator comparator;
    private Thing winnerThing;
    private Thing loserThing;

        <form:form method="POST"
          action="/vote" modelAttribute="vote">
            <div class="form-group">
                <label class="col-md-4 control-label" for="comparatorDropdown">Comparator</label>
                <div class="col-md-4">
                  <form:select path = "comparator" class="form-control" id="comparatorDropdown">
                     <form:options items = "${comparatorList}" itemValue="comparatorID" itemLabel="title"/>
                  </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="winnerThingDropdown">Winner Thing</label>
                <div class="col-md-4">
                  <form:select path = "winnerThing" class="form-control" id="winnerThingDropdown">
                     <form:options items = "${thingList}" itemValue="thingID" itemLabel="title"/>
                  </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="loserThingDropdown">Loser Thing</label>
                <div class="col-md-4">
                  <form:select path = "loserThing" class="form-control" id="loserThingDropdown">
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
