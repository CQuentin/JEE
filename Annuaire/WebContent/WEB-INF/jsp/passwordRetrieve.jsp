<jsp:root version="2.0" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:t="urn:jsptagdir:/WEB-INF/tags/mestags/">

	<jsp:output omit-xml-declaration="false" doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" />

	<!-- Définir ici les variables -->

	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

	<t:genericpage>
		<jsp:attribute name="head">
			<title>Formulaire de récupération de mot de passe</title>
    	</jsp:attribute>
	
		<jsp:attribute name="nav">
			<!-- Ajouter ici les liens qui apparaiteront dans les actions -->
    	</jsp:attribute>
    	
		<jsp:body>
			<p>
			Entrez votre login. Un mail avec votre nouveau mot de passe vous sera envoyé.<br/>
			Vous pourrez changer votre mot de passe par la suite (via l'édition)
			</p>
			
			<form:form method="post" commandName="user">
				<div>Login :	<form:input path="login" /></div>
			</form:form>
		</jsp:body>
	</t:genericpage>

</jsp:root>
