<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="${pageContext.request.contextPath}/user/add.do" /><br/>
	<c:url value="/user/add.do"/>
	<form
		action="<c:url value="/user/add.do"/>"
		method="post">
		用户名:<input type="text" name="name"><br /> 密码:<input
			type="text" name="password"><br /> <input type="submit"
			name="提交">
	</form>
</body>
</html>