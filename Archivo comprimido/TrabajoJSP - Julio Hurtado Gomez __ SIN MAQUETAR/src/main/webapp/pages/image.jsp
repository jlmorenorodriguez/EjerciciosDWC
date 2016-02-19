<%@ page language="java" contentType="image/jpeg"%><%@page import="es.altair.dao.AnuncioDAO"%><%@ page import="java.sql.*"%><%@ page import="java.io.*"%><%
try {
	String idLibro = request.getParameter("imag");
	byte[] imagen = AnuncioDAO.obtenerImagenPorId(Integer.parseInt(idLibro));
	try {
		if (imagen != null) {
			response.setContentType("image/jpeg");
			response.setHeader("Content-Disposition", "attachment");
			OutputStream o = response.getOutputStream();
			o.write(imagen); // even here we got the same as below.
			o.flush();
			o.close();
			return;
		}
	} catch (IllegalStateException e) {
	}
} catch (Exception e) {
}
%>