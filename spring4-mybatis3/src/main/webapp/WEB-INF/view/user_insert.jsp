<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<form action="${pageContext.request.contextPath}/user/insert.action" method="post">
		姓名：<input type="text" name="userName" "><br />
		年龄：<input type="text" name="userAge" ><br />
		地址：<input type="text" name="userAddress" ><br />
		性别：<input type="text" name="sex" ><br />
		<input type="submit" value="提交">
		<input type="reset" value="重置">
	</form>

</body>
</html>
