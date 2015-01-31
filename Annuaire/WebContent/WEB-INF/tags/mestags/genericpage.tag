<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>

<%@ attribute name="nav" fragment="true" %>
<%@ attribute name="head" fragment="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="css" value="/resources/css/style.css" />
<c:url var="marseille" value="/resources/img/logo_marseille.png" />
<c:url var="luminy" value="/resources/img/tn_logo_luminy.jpg" />
<c:url var="jsAddition" value="/resources/js/validationAddition.js"/>
<c:url var="jsEdition" value="/resources/js/validationEdition.js"/>
<c:url var="jsAddition" value="/resources/js/validationGroupAddition.js"/>
<c:url var="jsEdition" value="/resources/js/validationGroupEdition.js"/>

<html>
<head>
<jsp:invoke fragment="head" />

<link rel="stylesheet" href="${css}" type="text/css" />

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"
	type="text/javascript"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/additional-methods.min.js"
	type="text/javascript"></script>
<script src="${jsAddition}" type="text/javascript"></script>
<script src="${jsEdition}" type="text/javascript"></script>

</head>
<body>
	<div id="bloc_page">
		<div id="header">
			<div>
				<img src="${marseille}" alt="logo de l'université d'aix-marseille"
					id="logo_sciences" /> <img src="${luminy}" alt="logo de Luminy"
					id="logo_luminy" />
			</div>
		</div>
		
		<div id="nav">
			<h1>Menu</h1>
			<div>
				<a href="/Annuaire/person/annuaire.htm">Liste personnes</a><br />
				<a href="/Annuaire/group/groups.htm">Liste groupes</a>
			</div>
			<h1>Actions</h1>
			<div>
				<c:choose>
					<c:when test="${user.isConnected()}">
						<a href="/Annuaire/auth/logout.htm">Logout</a>
						<br />
					</c:when>
					<c:otherwise>
						<a href="/Annuaire/auth/login.htm">Login</a>
						<br />
					</c:otherwise>
				</c:choose>

				<c:if test="${user.isAdmin() == true}">
					<br />
					<a href="/Annuaire/person/add.htm">Créer personne</a>
					<br />
					<a href="/Annuaire/group/add.htm">Créer groupe</a>
					<br />
				</c:if>
				<jsp:invoke fragment="nav" />
			</div>
		</div>
		
		<div id="section">
			<jsp:doBody />
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
