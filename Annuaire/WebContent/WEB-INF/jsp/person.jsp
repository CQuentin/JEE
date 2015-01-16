<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/person/edition.htm" />
<c:url var="delete" value="/person/delete.htm" />

<html>
<body>
	<c:choose>
		<c:when test="${empty person || person.login == ''}">
			<p>Personne inexistante</p>
		</c:when>
		<c:otherwise>
			<h1>${person.firstName} ${person.lastName}</h1>
			<c:if test="${user.login == person.login}">
				<p><a href="${edit}?id=${person.login}">Editer</a></p>
			</c:if>
			<p><a href="${delete}?id=${person.login}">Supprimer</a></p>
		</c:otherwise>
	</c:choose>
</body>
</html>