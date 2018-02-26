<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="signup" method="post">
		<label for="firstName">First Name</label> <input type="text"
			name="firstName" required><br> <label for="lastName">Last
			Name</label> <input type="text" name="lastName" required><br> <label
			for="email">Email</label> <input type="email" name="email" required><br>

		<label for="username">Username</label> <input type="text"
			name="username" required><br> <label for="password">Password</label>
		<input type="password" name="password" required><br> <input
			type="submit" name="signup" value="Sign Up">
	</form>
</body>
</html>