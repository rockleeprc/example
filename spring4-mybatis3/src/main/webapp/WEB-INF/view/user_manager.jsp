<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>userCRUD</h2>
	<a href="${pageContext.request.contextPath}/user/toInsertPage.action">添加</a>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>地址</th>
			<th>性别</th>
			<th>操作</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.id}" /></td>
				<td><c:out value="${user.userName}" /></td>
				<td><c:out value="${user.userAge}" /></td>
				<td><c:out value="${user.userAddress}" /></td>
				<td><c:out value="${user.sex}" /></td>
				<td>
					<a href="${pageContext.request.contextPath}/user/findById.action?id=${user.id}">修改</a>
					&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/user/deleteById.action?id=${user.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
