<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajout personne</title>
<!-- 	<script src="../js/dist/jquery.validate.js"></script>
 -->
 
 <!--include jQuery -->
 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"
type="text/javascript"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"
type="text/javascript"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/additional-methods.min.js"
type="text/javascript"></script>

 <script>


	$().ready(function() {
		$("#addForm").validate({
			rules: {
				firstName: "required",
				lastName: "required",
				login: "required",
				password: {
					required: true,
					minlength: 8
				},
				mail: {
					required: true,
					email: true
				},
				dateOfBirth: {
					required: false,
					dateISO: true				}
			},
			messages: {
				firstName: "Prénom obligatoire.",
				lastName: "Nom obligatoire.",
				login: "Login obligatoire.",
				password: {
					required: "Mot de passe obligatoire.",
					minlength: "Le mot de passe doit contenir au moins 8 caractères"
				},
				mail: "Adresse mail non valide.",
				dateOfBirth: "La date doit être au format aaaa-mm-jj"
			}
		});
	});
	</script>
</head>
<body>
	<h1>Ajout personne</h1>
	<form:form id="addForm" method="POST" commandName="person">
		<table>
			<tr>
				<td>Login :</td>
				<td><form:input path="login" /></td>
				<td><form:errors path="login" /></td>
			</tr>
			<tr>
				<td>Nom :</td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" /></td>

			</tr>
			<tr>
				<td>Prénom :</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" /></td>

			</tr>
			<tr>
				<td>Email :</td>
				<td><form:input path="mail" /></td>
				<td><form:errors path="mail" /></td>

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
				<td><form:errors path="password" /></td>

			</tr>
			<tr>
				<td colspan="3"><input type="submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>