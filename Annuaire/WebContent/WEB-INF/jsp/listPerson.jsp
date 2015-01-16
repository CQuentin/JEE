<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="detail" value="/person/detail.htm" />
<c:url var="add" value="/person/add.htm" />
<c:url var="logout" value="/auth/logout.htm" />

<html>
<body>
	<h1>Personnes</h1>
	<p>
		<a href="${logout}">Logout</a>
	</p>

	<p>
		<a href="${add}">Ajouter une nouvelle personne</a>
	</p>


	<ul>
		<c:forEach items="${persons}" var="p">
			<li><a href="${detail}?id=${p.login}">${p.login} :
					${p.firstName} ${p.lastName}</a></li>
		</c:forEach>
	</ul>
</body>
</html>