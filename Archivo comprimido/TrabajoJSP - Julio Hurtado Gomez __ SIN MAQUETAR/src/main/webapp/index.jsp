<html>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Web01</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/generic.css" rel="stylesheet">
<link rel="shortcut icon" href="images/favicon.ico">
<body>
	<div>
	<div class="container">
				<div class="login-container">
			            <div id="output"></div>
			            <div class="avatar"></div>
			            <div class="form-box">
			                <form action="ValidarUsuario" method="post">
			                    <input name="user" type="text" placeholder="username">
			                    <input name="password" type="password" placeholder="password">
			                    <button class="btn btn-info btn-block login" type="submit">Login</button>
			                </form>
			            </div>
			        </div>
			        
				</div>
	
	
	
		<%
			String error = request.getParameter("error");
			if (error != null) {
		%>
		<div class="row">
			<div class="col-md-offset-5 col-md-3">
				<span style="color: Red; font-weight: bold;"><%=error %></span>
			</div>
		</div>
		<%
			}
		%>
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
