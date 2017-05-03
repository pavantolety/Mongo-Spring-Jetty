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
		<form class="navbar-brand" action="#" method="POST">
			<button type="submit">Letter Page</button>
		</form>
	</div>
  </div>
</nav>

<div class="jumbotron">
  <div class="container">
	<br>
   <div>
   		Mr./Ms./Miss. <h4 id="name"></h4>
		<div>
			<table>
				<tr>
				<td><b>Component</b></td>
				<td><b>Salary Structure ${structure.type}</b><td>
				</tr>
				<tr>
					<td>Basic</td>
					<td>${structure.basic} % of CTC</td>					
				</tr>
				<tr>
					<td>HRA</td>
					<td>${structure.hra} % of CTC</td>					
				</tr>
				<tr>
					<td>Conveyance Allowance</td>
					<td>${structure.conv_allowance}</td>					
				</tr>
				<tr>
					<td>Medical Allowance</td>
					<td>${structure.med_allowance}</td>					
				</tr>
				<tr>
					<td>Special Allowance</td>
					<td>${structure.specialAllowances}</td>					
				</tr>
			</table>			
			<br>
			<button type="button" onclick="generatePdf()">Generate Pdf</button>
		</div>
    </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
 $(document).ready(function(){
	alert();
	var obj = localStorage.getItem("object");
	alert(obj);
		/* $.ajax({            	  
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
		}); */
	});
	
	function generatePdf(){
		
	}
</script>
</body>
</html>
