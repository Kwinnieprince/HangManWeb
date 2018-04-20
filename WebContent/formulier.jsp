<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Woord Toevoegen</title>
<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Woord Toevoegen</h1>
<form method="post" action="Servlet?command=add">
			<fieldset>
			<div class="toevoegen-velden">
				<p class="row">
                   <label for="woord">Woord:</label>
                   <input type="woord" id="woord" name="woord" required aria-required="true"/>
               </p>
				<p class="row">
						<label for="niveau">Niveau:</label>
						
	                    <select id="niveau" name="niveau" required>
	                    	<option value=null>Geen niveau</option>
	                        <option value="beginner">Beginner</option>
	                        <option value="expert">Expert</option>
	                    </select>
	
					</p>
				 <p>
                    <input type="submit" value="Woord toevoegen" class="Knop" id="voegToe">
               </p>
               </div>
				</fieldset>
				</form>
<a href="Servlet?command=overview" class="Knop">Terug naar woordenlijst</a>
</body>
</html>