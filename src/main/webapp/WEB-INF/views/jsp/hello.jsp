<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC</title>

</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<form class="navbar-brand" action="/saveConfig" method="POST">
			<button type="submit">Spring 3 MVC Project</button>
		</form>
	</div>
  </div>
</nav>

<div class="jumbotron">
  <div class="container">
	<br>
   <div>
		<div>
			<input type="text" name="firstName" id="firstName" placeholder="First Name">
			<input type="text" name="lastName" id="lastName" placeholder="Last Name">
			<br>
			<input type="text" name="designation" id="designation" placeholder="Designation"><br>
			<input type="text" name="department" id="department" placeholder="Department Name"><br>
			<select name="ctcType" id="ctcType">
				<c:forEach var="configuration" items="${configList}" >
					<option value="${configuration._id}">${configuration.type}</option>
				</c:forEach>
			</select> 
			<br>
			<button type="button" onclick="saveConfig()">Submit</button>
		</div>
    </div>
  </div>
</div>

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
	function saveConfig(){
		 var obj = {
			firstName: $("#firstName").val(),
			lastName: $("#lastName").val(),
			designation: $("#designation").val(),
			department: $("#department").val(),
			ctcType: $("#ctcType").val()
		};
		
		alert(JSON.stringify(obj));
		$.ajax({            	  
			type : "GET",
			url : "/saveStructure",
			dataType : "json", 
			data : JSON.parse(JSON.stringify(obj)),           
			success : function(data) {
			  //alert(JSON.stringify(data));
			  localStorage.setItem("object", obj);
			  //location.reload();
			  var redirect = '${redirectUrl}';
				if (redirect) {
				    window.location.replace(redirect);
				}               	  
			}, error : function (data){
			 // alert(JSON.stringify(data));                	  
			}
		});
	}
</script>
</body>
</html>
