<%@page import="es.altair.dao.AnuncioDAO"%>
<%@page import="es.altair.bean.Anuncio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.altair.bean.Usuario"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de anuncios</title>
</head>
<body>
	<%
		if (session.getAttribute("usuLogeado") == null || session.isNew()) {
			response.sendRedirect("../index.jsp");
		} else {
			ArrayList<Anuncio> anuncios = AnuncioDAO.listarAnuncios();

			//Prueba anuncios de un solo usuario
			int usu = ((Usuario) session.getAttribute("usuLogeado"))
					.getIdUsuario();

			ArrayList<Anuncio> anuncios2 = AnuncioDAO
					.listarAnunciosPorUsuario(usu);

			//Fin prueba anuncios de un solo usuario
	%>
	<div class="container">
		<div class="row">
			<ul class="breadcrumb">
				<li class="completed"><a href="../index.jsp">Página
						Principal</a></li>
				<li class="active"><a href="#">Principal Administrador</a></li>
				<li class="completed"><a href="../CerrarSesion">Cerrar
						Sesion</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-lg-3">
				Bienvenido,
				<%=((Usuario) session.getAttribute("usuLogeado"))
						.getNombre()%>
			</div>
		</div>
		<div class="table-responsive">
			<div class="row col-md-10 col-md-offset-2 custyle">
				<table class="table table-striped custab">
					<thead>
						<div class="col-lg-9 pull-right">
							<a href="anadirAnuncio.jsp"
								class="btn btn-primary btn-xs pull-right"><b>+</b> Añadir
								Libro</a>
						</div>
						<tr>
							<th>ID</th>
							<th>Descripcion</th>
							<th>AUTOR</th>
							<th>ISBN</th>
							<th>PORTADA</th>
							<th>USUARIO</th>
							<th></th>
						</tr>
					</thead>
					<%
						for (Anuncio a : anuncios2) {
					%>
					<tr>
						<td><%=a.getIdAnuncio()%></td>
						<td><%=a.getDescripcion()%></td>

						<td><img alt="Portada" class="img-thumbnail" width="50"
							height="50" src="image.jsp?imag=<%=a.getIdAnuncio()%>" /></td>
						<td><%=a.getUsuario().getNombre()%></td>
						<td class="text-center">
							<%-- <a href="editarLibro.jsp?idLibro=<%=l.getIdLibro()%>"
							class='btn btn-info btn-xs'><span
								class="glyphicon glyphicon-edit"></span>Editar</a> --%> <a
							href="editarAnuncio.jsp?encrypt=<%=a.getUuid()%>"
							class='btn btn-info btn-xs'><span
								class="glyphicon glyphicon-edit"></span>Editar</a>

							<div id="myModal<%=a.getIdAnuncio()%>" class="modal fade in">
								<div class="modal-dialog">
									<div class="modal-content">

										<div class="modal-header">
											<a class="btn btn-default" data-dismiss="modal"><span
												class="glyphicon glyphicon-remove"></span></a>
											<h4 class="modal-title">Borrar</h4>
										</div>
										<div class="modal-body">
											<h4>Borrado de Libro</h4>
											<p>
												¿Desea borrar el libro
												<%=a.getIdAnuncio()%>?
											</p>
										</div>
										<div class="modal-footer">
											<div class="btn-group">
												<button class="btn btn-danger" data-dismiss="modal">
													<span class="glyphicon glyphicon-remove"></span> Cancelar
												</button>
												<%-- <a href="../BorrarLibro?idLibro=<%=l.getIdLibro()%>" class="btn btn-primary">
													<span class="glyphicon glyphicon-check"></span> Borrar
												</a> --%>
												<a href="../BorrarAnuncio?encrypt=<%=a.getUuid()%>"
													class="btn btn-primary"> <span
													class="glyphicon glyphicon-check"></span> Borrar
												</a>
											</div>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div> <!-- /.modal --> <a data-toggle="modal"
							href="#myModal<%=a.getIdAnuncio()%>"
							class="btn btn-danger btn-xs"><span
								class="glyphicon glyphicon-remove"></span> Borrar</a>
						</td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>

	<%
		}
	%>


</body>
</html>

