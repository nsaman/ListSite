<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>

<div class="container">
<table id="things" class="table table-striped table-hover table-sm">
  <thead>
    <tr>
        <th>#</th>
        <c:forEach var="compareHeader" items="${thingsTableView.comparesHeaders}">
            <th><a href="/comparator/${compareHeader.comparatorID}">${compareHeader.title}</a></th>
        </c:forEach>
        <!--c:forEach var="thingHeader" items="${thingsTableView.thingHeaders}"-->
        <th>Title</th>
        <!--/c:forEach-->
        <c:forEach var="descriptorHeader" items="${thingsTableView.descriptorHeaders}">
            <th><a href="/descriptorType/${descriptorHeader.descriptorTypeID}">${descriptorHeader.title}</a></th>
        </c:forEach>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="thingsRow" items="${thingsTableView.thingsMap}" varStatus="rowCounter">
        <tr>
            <th scope="row">${rowCounter.index + 1}</th>
            <c:forEach var="compareHeader" items="${thingsTableView.comparesHeaders}">
                <td><a href="/compares/${thingsRow.value.comparesMap[compareHeader].comparesID}">${thingsRow.value.comparesMap[compareHeader].score}</a></td>
            </c:forEach>
            <!--c:forEach var="thingHeader" items="${thingsTableView.thingHeaders}"-->
            <td><a href="/thing/${thingsRow.key.thingID}">${thingsRow.key.title}</a></td>
            <!--/c:forEach-->
            <c:forEach var="descriptorHeader" items="${thingsTableView.descriptorHeaders}">
                <td>
                <c:forEach var="descriptorList" items="${thingsRow.value.descriptorMap[descriptorHeader]}">
                    <a href="/descriptor/${descriptorList.type.typeName}/${descriptorList.descriptorID}">${descriptorList.readableString}</a><br>
                </c:forEach>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>

  </tbody>
</table>
</div>

<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>