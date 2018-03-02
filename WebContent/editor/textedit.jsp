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
	<span>${status}</span><br>
	
	<form action="textedit" method="post">
		<input type="hidden" name="action" value="save">
		
		<c:if test="${isView == null || isView == false}">
		<label>Begin writing your document.</label><br>
			<textarea name="textarea" rows="75" cols="100"><c:out value="${content}"/></textarea>
		</c:if>
		
		<c:if test="${isView == true}">
			<span>"${content}"</span>
		</c:if>
		
		<c:if test="${content == null}">
			<label for="filename">File Name &nbsp;</label>
			<input type="text" name="filename" required>
			<input type="submit" name="saveFile" value="Save Text">
		</c:if>
	</form>
	
	<form action="manage" method="get">
		<input type="submit" name="manage" value="Manage Files">
	</form>
</body>
</html>