<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC {$hello}</title>

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
			<c:out value="${structure.type}" />
			<br>
			<button type="button" onclick="saveConfig()">Submit</button>
		</div>
    </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
	/* $(document).ready(function(){
	alert();
	var obj = localStorage.getItem("object");
	alert(obj);
		$.ajax({            	  
			type : "GET",
			url : "/viewStructure",
			dataType : "json",  
			data : JSON.parse(JSON.stringify(obj)),      
			success : function(data) {
			  //alert(JSON.stringify(data));
			  //var config = data.
			  //location.reload();               	  
			}, error : function (data){
			 // alert(JSON.stringify(data));                	  
			}
		});
	}); */
</script>
</body>
</html>