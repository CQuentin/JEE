<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajout personne</title>
</head>
<body>

	<h1>Ajout personne</h1>
	<form:form method="POST" commandName="person">
		<table>
			<tr>
				<td>Login :</td>
				<td><form:input path="login" /></td>
			</tr>
			<tr>
				<td>Nom :</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>Prénom :</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" /></td>

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