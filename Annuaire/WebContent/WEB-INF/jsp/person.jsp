<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<body>
	<c:choose>
		<c:when test="${empty person}">
			<p>Personne inexistante</p>
		</c:when>
		<c:otherwise>
			<h1>${person.firstName} ${person.lastName}</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>