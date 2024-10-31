<%@page import="dominio.Seguro"%>
<%@page import="dominio.TipoSeguro"%>
<%@page import="java.util.List"%>
<%@page import="daoImpl.TipoSeguroDaoImpl"%>
<%@page import="dao.TipoSeguroDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="PlantillaMenu.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
/*table, td, th {
	border-color: black;
	border-width: 1px;
	border-style: solid;
}*/
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ListarStyle.css">
<title>Listar Seguros</title>
</head>
<body>


	<h1>"Tipo de seguros de la base de datos"</h1>

	<form action="ListarSeguroServlet" method="GET">
		<label>Búsqueda por tipo de Seguros: <select name="slcTipo">
				<%
					List<TipoSeguro> tiposSeguros = (List<TipoSeguro>) request.getAttribute("tiposSeguros");
					if (tiposSeguros != null) {
						for (TipoSeguro tipo : tiposSeguros) {
				%>
				<option value="<%=tipo.getId()%>"><%=tipo.getDescripcion()%></option>
				<%
					}
					}
				%>
		</select>
		</label>
		<button type="submit" name="btnFiltrar">Filtrar</button>
	</form>

	<table style="margin-top: 3em;">
		<tr>
			<th>ID Seguro</th>
			<th>Descripcion seguro</th>
			<th>Descripción tipo seguro</th>
			<th>Costo contratación</th>
			<th>Costo máximo asegurado</th>
		</tr>
		<%
			List<Seguro> seguros = (List<Seguro>) request.getAttribute("seguros");
			if (seguros != null) {
				for (Seguro s : seguros) {
		%>
		<tr>
			<td><%=s.getId()%></td>
			<td><%=s.getDescripcion()%></td>
			<td><%=s.getTipo().getDescripcion()%></td>
			<td><%=s.getCostoContratacion()%></td>
			<td><%=s.getCostoAsegurado()%></td>
		</tr>
		<%
			}
			}
		%>
	</table>


</body>
</html>