<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>

<%@ attribute name="nav" fragment="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="css" value="/resources/css/style.css" />
<c:url var="marseille" value="/resources/img/logo_marseille.png" />
<c:url var="luminy" value="/resources/img/tn_logo_luminy.jpg" />

<html>
<head>
<title>Page générique</title>
<link rel="stylesheet" href="${css}" type="text/css" />
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
				<a href="#">Liste personnes</a><br />
				<a href="#">Liste groupes</a>
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
				<!-- Si l'utilisateur est un admin ajouter "Créer personne" et "Créer groupe -->
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
