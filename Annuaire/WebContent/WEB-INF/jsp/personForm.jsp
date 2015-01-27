<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition personne</title>
</head>
<body>

	<h1>Edition personne</h1>
	<form:form method="POST" commandName="person">
		<table>
			<tr>
				<td>Nom :</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>Prénom :</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><form:input path="mail" /></td>
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
			</tr>
			<tr>
				<td colspan="3"><input type="submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>