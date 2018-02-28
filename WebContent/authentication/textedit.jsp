<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to text editor area ${currentLoggedUser.username}</h1>
	
	<form action="save" method="post">
		<label>Begin writing your document.</label><br>
		<textarea name="textarea" rows="75" cols="100"></textarea>
		
		<label for="filename">File Name &nbsp;</label>
		<input type="text" name="filename" required>
		<input type="submit" name="saveFile" value="Save Text">
	</form>
	
</body>
</html>