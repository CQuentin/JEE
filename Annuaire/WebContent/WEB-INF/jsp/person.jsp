<jsp:root version="2.0" xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:t="urn:jsptagdir:/WEB-INF/tags/mestags/">

	<jsp:output omit-xml-declaration="false" doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" />

	<c:url var="edit" value="/person/edition.htm" />
	<c:url var="delete" value="/person/delete.htm" />

	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

	<t:genericpage>
		<jsp:attribute name="head">
			<title>Page de détail</title>
    	</jsp:attribute>
	
		<jsp:attribute name="nav">
			<c:if test="${user.login == person.login || user.login == 'login'}">
				<br />
				<a href="${edit}?id=${person.login}">Editer</a>
				<br />
			</c:if>
			<c:if test="${user.login == 'login'}">
				<a href="${delete}?id=${person.login}">Supprimer</a>
				<br />
			</c:if>
    	</jsp:attribute>

		<jsp:body>
			<c:choose>
				<c:when test="${empty person || person.login == ''}">
					<p>Personne inexistante</p>
				</c:when>
				<c:otherwise>
					<h1>${person.firstName} ${person.lastName}</h1>
					<p>Site web : ${person.website}</p>
					<c:if test="${user.isConnected()}">
						<p>Adresse électronique : ${person.mail}</p>
						<p>Date de naissance : ${person.dateOfBirth}</p>
					</c:if>
				</c:otherwise>
			</c:choose>
		</jsp:body>
	</t:genericpage>

</jsp:root>
