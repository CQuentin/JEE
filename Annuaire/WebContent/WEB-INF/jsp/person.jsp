<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/actions/person/edition.htm" />
<c:url var="delete" value="/actions/person/delete.htm" />

<html>
<body>
	<c:choose>
		<c:when test="${empty person}">
			<p>Personne inexistante</p>
		</c:when>
		<c:otherwise>
			<h1>${person.firstName} ${person.lastName}</h1>
			<p><a href="${edit}?id=${person.login}">Editer</a></p>
			<p><a href="${delete}?id=${person.login}">Supprimer</a></p>
		</c:otherwise>
	</c:choose>
</body>
</html>