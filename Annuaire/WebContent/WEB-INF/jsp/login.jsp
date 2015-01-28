<jsp:root version="2.0" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core">

	<jsp:output omit-xml-declaration="false" doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" />

	<c:url var="retrievepw" value="/auth/retrievepw.htm" />
	<c:url var="css" value="/resources/css/style.css" />
	<c:url var="marseille" value="/resources/img/logo_marseille.png" />
	<c:url var="luminy" value="/resources/img/tn_logo_luminy.jpg" />

<html>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<head>
<title>Formulaire d'authentification</title>
<link rel="stylesheet" href="${css}" type="text/css" />
</head>
<body>
	<div id="bloc_page">
		<div id="header">
			<div>
				<img src="${marseille}"
					alt="logo de l'université d'aix-marseille" id="logo_sciences" /> <img
					src="${luminy}" alt="logo de Luminy"
					id="logo_luminy" />
			</div>
		</div>

		<div id="nav">
			<h1>Navigation</h1>
			<div id="navigation">
				<a href="#">Login</a><br /> <a href="#">Liste</a>
			</div>
		</div>

		<div id="section">
			<form:form method="post" commandName="user">
				<table>
					<tr>
						<td>Login :</td>
						<td><form:input path="login" /></td>
					</tr>
					<tr>
						<td>Mot de passe :</td>
						<td><form:input path="password" /></td>
					</tr>
					<tr>
						<td colspan="3"><input type="submit" value="Envoyer" /></td>
					</tr>
				</table>
			</form:form>
			<div>
				<a href="${retrievepw}">Mot de passe oublié ?</a>
			</div>
		</div>

		<div id="footer">
			<p>
				<a href="http://jigsaw.w3.org/css-validator/check/referer"> <img
					style="border: 0; width: 88px; height: 31px"
					src="http://jigsaw.w3.org/css-validator/images/vcss"
					alt="CSS Valide !" id="logo_W3C" />
				</a>
			</p>
		</div>
	</div>
</body>
</html>
</jsp:root>