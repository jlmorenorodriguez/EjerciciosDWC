<%@page import="es.altair.bean.Usuario"%>
<html>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Añadir Anuncio</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/estilos.css" rel="stylesheet">
<link rel="shortcut icon" href="../images/favicon.ico">
<body>
	<div class="container">
		<%
			if (session.getAttribute("usuLogeado") == null || session.isNew()) {
				response.sendRedirect("../index.jsp");
			} else {
		%>
		<div class="row">
			<ul class="breadcrumb">
				<li class="completed"><a href="../index.jsp">Página
						Principal</a></li>
				<li class="completed"><a href="principalUsu.jsp">Principal
						Usuario</a></li>
				<li class="active"><a href="#">Añadir anuncio</a></li>
				<li class="completed"><a href="../CerrarSesion">Cerrar
						Sesion</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-md-5">
				<div class="form-area">
					<form role="form" action="../AnadirAnuncio" method="post" enctype="multipart/form-data">
						<br style="clear: both">
						<h3 style="margin-bottom: 25px; text-align: center;">Añadir anuncio</h3>
						<div class="form-group">
							<input type="text" class="form-control" id="descripcion" name="descripcion"
								placeholder="Descripcion" required>
						</div>

						<div class="form-group">
							<input type="file" class="form-control" id="imagen" name="imagen"
								placeholder="Imagen" required>
						</div>

						<input type="submit" id="submit" name="submit"
							class="btn btn-primary pull-right"/>
					</form>
				</div>
			</div>
		</div>
		<%
			}
		%>
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>