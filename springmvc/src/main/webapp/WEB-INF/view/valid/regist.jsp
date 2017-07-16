<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="user"
		action="${pageContext.request.contextPath}/valid/regist.do"
		method="post">
		<form:errors path="*"/><br/>
		<form:errors path="name"/>
	用户名:<form:input id="name" path="name" /><br/>
	<form:errors path="password"/>	
	密码:<form:input path="password" /><br/>
	年龄:<form:input path="age" />	<br/>
	日期<form:input path="date" /><br/>
		<input type="submit">
	</form:form>
</body>
</html>