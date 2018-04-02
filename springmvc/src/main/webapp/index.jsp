<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
	<h2>Hello World!</h2>
	<a href="user/register.do">注册</a><br/> 
	<a href="${pageContext.request.contextPath}/user/register.do">注册</a><br/>
	<a href="${pageContext.request.contextPath}/param/param.do">参数绑定</a><br/> 
	<a href="${pageContext.request.contextPath}/valid/valid.do">数据校验</a><br/> 
	${pageContext.request.contextPath}<br/>
	<%=request.getContextPath()%><br/>
</body>
</html>
