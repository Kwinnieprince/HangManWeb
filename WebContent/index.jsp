<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.db.*" %>
<%@ page import="domain.model.*" %>
<!DOCTYPE html">
<html>
<head>
<% Woordenlijst db = (Woordenlijst)(request.getAttribute("lijst")); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Beheerapplicatie Woordenlijst</title>
<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Beheerapplicatie Woordenlijst</h1>
<p>
Met deze applicatie kan je de woordenlijst beheren om in het spelletje 
Hangman te gebruiken.
<span>Deze woordenlijst bevat: <%=db.getAantalWoorden() %> woorden</span>
<span>Het Langste woord in de lijst is: <%=db.getLangsteWoord() %></span>
<span>Het kortste woord in de lijst is: <%=db.getKortsteWoord() %></span>
<span>Het gemiddelde aantal verschillende letters in een woord is: <%=db.getGemiddeldVerschillendeLetters() %></span>
</p>
<a href="Servlet?command=overview" class="Knop" id="naarWoordenLijst">Toon Woordenlijst</a>
<a href="Servlet?command=download" class="Knop">Download</a>
</body>
</html>