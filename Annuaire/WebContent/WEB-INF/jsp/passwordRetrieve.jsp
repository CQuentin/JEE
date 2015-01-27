<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>
Entrez votre login. Un mail avec votre nouveau mot de passe vous sera envoyé.<br/>
Vous pourrez changer votre mot de passe par la suite (via l'édition)
</p>
	<form:form method="POST" commandName="user">
		Login :	<form:input path="login" />
	</form:form>
</body>
</html>