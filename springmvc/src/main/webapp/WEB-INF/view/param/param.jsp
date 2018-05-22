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
	<h1>RequestParam参数绑定</h1>
	<hr/>
	表单参数分别绑定
	<form
		action="<c:url value="/param/handle1.do"/>"
		method="post">
		用户名:<input type="text" name="name"><br /> 
		密码:<input type="text" name="password"><br /> 
		<input type="submit" name="提交">
	</form>
	<hr/>
	获取session,请求头参数
	<form
		action="<c:url value="/param/handle2.do"/>"
		method="post">
		用户名:<input type="text" name="name"><br /> 密码:<input
			type="text" name="password"><br /> <input type="submit"
			name="提交">
	</form>
		<hr/>
	使用原生HttpServletAPI获取参数
	<form
		action="<c:url value="/param/handle3.do"/>"
		method="post">
		用户名:<input type="text" name="name"><br /> 密码:<input
			type="text" name="password"><br /> <input type="submit"
			name="提交">
	</form>
			<hr/>
	使用代理HttpServlet获取参数
	<form
		action="<c:url value="/param/handle4.do"/>"
		method="post">
		用户名:<input type="text" name="name"><br /> 密码:<input
			type="text" name="password"><br /> <input type="submit"
			name="提交">
	</form>
	<hr/>
	数据格式化
	<form
		action='<c:url value="/param/handle5.do"/>' method="post">
		用户名:<input type="text" name="name"><br /> 
		密码:<input type="text" name="password"><br />
		生日:<input type="text" name="date"><br/>
		 <input type="submit" name="提交">
	</form>
</body>
</html>