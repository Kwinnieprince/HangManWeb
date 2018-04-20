<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.db.*" %>
<%@ page import="domain.model.*" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Woordenlijst</title>
<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<% Woordenlijst db = (Woordenlijst)(request.getAttribute("lijst")); %>
<%int teller = 1; %>
<body>
<h1>WoordenLijst</h1>
<p>
<a href="Servlet" class="Knop">Home</a>
<a href="formulier.jsp" class="Knop" id="nieuwWoord">Nieuw woord</a>
<a href="Servlet?command=download" class="Knop">Download</a>
<div class="overzichtPagina">
<div class="Table">
<h2>Woorden in de woordenlijst</h2>
	<table>
			<tr>
				<th> </th>
				<th>Woord</th>
				<th>Niveau</th>
			</tr>
			<%for (Woord w : db.getAll()) {%>
			<tr>
				<td><%=teller%></td>
				<td><%=w.toString()%></td>
				<td><%=w.getNiveau() %></td>
			</tr>
			<%teller++; %>
			<%} %>
	</table>
	</div>
</p>
<div class="Filters">
<h2>Filters</h2>
			<p>
				<a href=Servlet?command=overview class="Knop">Geen Filter</a>
				<span><a href=Servlet?command=overview&filter=beginner id="beginnerFilter" class="Knop">Beginner</a></span>
				<span><a href=Servlet?command=overview&filter=expert id="expertFilter" class="Knop">Expert</a></span>
			</p>
</div>
</div>
</body>
</html>