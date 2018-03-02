<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "com.texteditor.model.domain.*" %>
<%@ page import = "java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table >
		<caption>Result (Files):</caption>
		<thead>
			<tr class="tr">
				<th>File Name</th>
				<th>File Path</th>
				<th>File Size</th>
				<th>Date created</th>
				<th>Date last modified</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${files}" var="file">
				<tr>
					<td>${file.fileName}</td>
					<td>${file.filePath}</td>
					<td>${file.sizeInBytes}</td>
					<td>${file.dateAdded}</td>
					<td>${file.dateLastModified}</td>
					<td>
						<a href="/OnlineTextEditorWork/view-file?id=${file.id}">View</a>
						<a href="/OnlineTextEditorWork/edit-file?id=${file.id}">Edit</a>
						<a href="/OnlineTextEditorWork/delete-file?id=${file.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>