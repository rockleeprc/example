<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<form action="${pageContext.request.contextPath}/user/update.action" method="post">
		<input type="hidden" name="id" value="${user.id} "><br />
		姓名：<input type="text" name="userName" value="${user.userName} "><br />
		年龄：<input type="text" name="userAge" value="${user.userAge} "><br />
		地址：<input type="text" name="userAddress" value="${user.userAddress} "><br />
		性别：<input type="text" name="sex" value="${user.sex} "><br />
		<input type="submit" value="提交">
		<input type="reset" value="重置">
	</form>

</body>
</html>
