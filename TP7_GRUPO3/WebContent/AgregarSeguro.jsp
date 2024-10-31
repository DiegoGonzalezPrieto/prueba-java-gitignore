<%@page import="daoImpl.SeguroDaoImpl"%>
<%@page import="dominio.Seguro"%>
<%@page import="java.util.List"%>
<%@page import="daoImpl.TipoSeguroDaoImpl"%>
<%@page import="dao.TipoSeguroDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="PlantillaMenu.jsp" %>
<%@page import=" dominio.TipoSeguro" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Seguro</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/AgregarSeguroStyle.css">
</head>
<body>

<h1>Agregar Seguros</h1>

<form action="AgregarSeguroServlet" method="post">
	<fieldset>
		<legend>Seguros</legend>
		<p>
		<%
		int proximoId = 0;
		SeguroDaoImpl segDaoImpl = new SeguroDaoImpl();
		proximoId = segDaoImpl.ultimoSeguro();
		System.out.println(proximoId);
		%>
			<label for="ID">ID Seguro</label>
			<input id="ID" type="text" required name="txtID"  value=<%= proximoId + 1 %> readonly>
		</p>
			<label for="Descripcion">Descripcion</label>
			<input id="Descripcion" type="text" placeholder="Ingrese la Descripcion" required name="txtDescripcion">
		<p>
		</p>
			<label for="Tipo de Seguro">Tipo de seguro</label>
			<select name="Tipos" required>
				<option value="" disabled selected>Seleccione una opción</option>
				<% 
					//ArrayList<TipoSeguro> listaTipoSeguros = (ArrayList<TipoSeguro>) request.getAttribute("listaTipoSeguros");
					TipoSeguroDao tipoSeguro = new TipoSeguroDaoImpl();
					List<TipoSeguro> listaTipoSeguros = tipoSeguro.readAll();
					
					if (listaTipoSeguros != null) {
						for (TipoSeguro tipo : listaTipoSeguros) {
				%>
							<option value="<%= tipo.getId() %>"><%= tipo.getDescripcion() %></option>
				<%
						}
					}
				%>
			</select>
		<p>
		</p>
			<label for="Costo Contratacion">Costo de contratación</label>
			<input id="Costo Contratacion" type="number" placeholder="Ingrese el Costo Contratacion" min="0" required name="txtCostoContratacion">
		<p>
			<label for="Costo Maximo">Costo máximo</label>
			<input id="Costo Maximo" type="number" placeholder="Ingrese el Costo maximo" required name="txtCostoMaximo" min="0">
		</p>
		<p>
			<input id="btnAceptar" type="submit" value="Aceptar" required name="btnEnviar">
		</p>		
	</fieldset>

</form>
</body>
</html>