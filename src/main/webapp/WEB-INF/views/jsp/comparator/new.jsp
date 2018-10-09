<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<form id="createForm" action="javascript://">
  <div class="form-group">
    <label for="title">Title</label>
    <input type="text" class="form-control" id="title" name="title" placeholder="Comparator title">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</div>

<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script>
$( "#createForm" ).submit(function( event ) {
    event.preventDefault();
    var postObj = { };
    $.each($('#createForm').serializeArray(), function() {
        postObj[this.name] = this.value;
    });

    $.ajax({
      url:"/comparator",
      type:"POST",
      data:JSON.stringify(postObj),
      contentType:"application/json; charset=utf-8",
      success: function(response){
      console.log(response)
          window.location.href = response.comparatorID;
      },
      dataType: 'json'
    })
});

</script>

</body>
</html>
