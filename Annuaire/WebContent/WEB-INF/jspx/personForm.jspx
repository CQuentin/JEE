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
			<title>Edition personne</title>
    	</jsp:attribute>
	
		<jsp:attribute name="nav">
			<!-- Ajouter ici les liens qui apparaiteront dans les actions -->
    	</jsp:attribute>
    	
		<jsp:body>
		<h1>Edition personne</h1>
		<form:form id="editForm" method="post" commandName="person">
		<table>
			<tr>
				<td>Nom :</td>
				<td><form:input path="lastName" /></td>
				<td><form:errors cssClass="error" path="lastName" /></td>
			</tr>
			<tr>
				<td>Prénom :</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors cssClass="error" path="firstName" /></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><form:input path="mail" /></td>
				<td><form:errors cssClass="error" path="mail" /></td>
			</tr>
			<tr>
				<td>Site web :</td>
				<td><form:input path="website" /></td>
			</tr>
			<tr>
				<td>Date de naissance :</td>
				<td><form:input path="dateOfBirth" /></td>
			</tr>
			<tr>
				<td>Mot de passe :</td>
				<td><form:input path="password" /></td>
				<td><form:errors cssClass="error" path="password" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" /></td>
			</tr>
		</table>
		</form:form>
		</jsp:body>
	</t:genericpage>

</jsp:root>
