<jsp:root version="2.0" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:t="urn:jsptagdir:/WEB-INF/tags/mestags/">

	<jsp:output omit-xml-declaration="false" doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" />

	<c:url var="retrievepw" value="/auth/retrievepw.htm" />

	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

	<t:genericpage>
		<jsp:attribute name="head">
			<title>Formulaire d'authentification</title>
    	</jsp:attribute>
	
		<jsp:attribute name="nav">
			<!-- Ajouter ici les liens qui apparaiteront dans les actions -->
    	</jsp:attribute>
    	
		<jsp:body>
		<div id="loginForm">
			<form:form method="post" commandName="user">
				<fieldset>
            	<legend class="gras"> Formulaire d'authentification :</legend>
				<table>
					<tr>
						<td>Login :</td>
						<td><form:input path="login" /></td>
					</tr>
					<tr>
						<td>Mot de passe :</td>
						<td><form:password path="password" /></td>
					</tr>
					<tr>
						<td class="right" colspan="3"><input type="submit" value="Envoyer" /></td>
					</tr>
				</table>
				</fieldset>
			</form:form>
			<div class="center">
					<p><c:out value="${error}"/></p>
				<a href="${retrievepw}">Mot de passe oublié ?</a>
			</div>
		</div>
		</jsp:body>
	</t:genericpage>

</jsp:root>