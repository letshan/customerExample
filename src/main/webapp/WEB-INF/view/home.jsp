<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Icearn Customer Web Service Demo</title>
    <script src="<spring:url value="/resources/script/jquery-1.10.2.js"/>"></script>
    <style>
    	body { background-color: #eee; font: helvetica; }
    	#container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
    	.green { font-weight: bold; color: green; }
    	.message { margin-bottom: 10px; }
    	label { width:70px; display:inline-block;}
    	.hide { display: none; }
    	.error { color: red; font-size: 0.8em; }
    </style> 
  </head>
  <body>
	
	<div id="container">
	
		<h1>Customer Page</h1>
		<p>This page demonstrates Spring MVC's Ajax functionality. Retrieve a
		random customer, retrieve a customer by ID, or save a new customer, all without page reload.
		</p>
		
		<h2>Random customer Generator</h2>
		<input type="submit" id="randomcustomer" value="Get Random customer" /><br/><br/>
		<div id="customerResponse"> </div>
		
		<hr/>
		
		<h2>Get By ID</h2>
		<form id="idForm">
			<div class="error hide" id="idError">Please enter a valid ID</div>
			<label for="customerId">ID : </label><input name="id" id="customerId" value="0" type="number" />
			<input type="submit" value="Get customer By ID" /> <br /><br/>
			<div id="customerIdResponse"> </div>
		</form>
		
		<hr/>
		
		<h2>Submit new customer</h2>
		<form id="newcustomerForm">
			<label for="nameInput">Name: </label>
			<input type="text" name="name" id="nameInput" />
			<br/>
			
			<label for="ageInput">Age: </label>
			<input type="text" name="age" id="ageInput" />
			<br/>
		
			<input type="submit" value="Save customer" /><br/><br/>
			<div id="customerFormResponse" clastUpdateInputlass="green"> </div>
		</form>
	</div>
	
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			// Random customer AJAX Request
			$('#randomcustomer').click(function() {
				$.getJSON('${pageContext.request.contextPath}/api/customer/random', function(customer) {
					if(customer){
					$('#customerResponse').text(customer.name + ', address ' + customer.address);
					}
				});
			});
			
			// Request customer by ID AJAX
			$('#idForm').submit(function(e) {
				var customerId = +$('#customerId').val();
				 
				$.get('${pageContext.request.contextPath}/api/customer/' + customerId, function(customer) {
					if(customer){
					$('#customerIdResponse').text(customer.name + ', address ' + customer.address);
					}
				});
				e.preventDefault(); // prevent actual form submit
			});
			
			// Save customer AJAX Form Submit 
			
			$('#newcustomerForm').submit(function(e) {
				// will pass the form date using the jQuery serialize function
// 				$.post('${pageContext.request.contextPath}/api/customer', $(this).serialize(), function(response) {
// 					$('#customerFormResponse').text(response);
// 				});
				var d = new Date();
				$.ajax({url:'${pageContext.request.contextPath}/api/customer',dataType:'json',
						type:'POST',
						contentType:'application/json',						
						data: JSON.stringify({name:$("#nameInput").val(),age:$("#ageInput").val(),lastUpdate:'2013-08-01T22:17:51', phone:null})						
						})
					.done(function ( customer ) { 
						if(customer){
							$('#customerIdResponse').text(customer.name + ', address ' + customer.address);
						}
					});
				e.preventDefault(); // prevent actual form submit and page reload
			});
			
		});
		
		function validatecustomerId(customerId) {
			console.log(customerId);
			if(customerId === undefined || customerId < 0 || customerId > 3) {
				$('#idError').show();
				return false;
			}
			else {
				$('#idError').hide();
				return true;
			}
		}
		
	
	</script>
	
  </body>
</html>