<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap.css" />
</head>
<body >
	<table class="table table-bordered">
		<tr>
			<td>员工编号</td>
			<td>姓名</td>
			<td>工资</td>
		</tr>
		<c:forEach items="${list}" var="e">
			<tr>
				<td>${e.empno}</td>
				<td>${e.ename}</td>
				<td>${e.sal}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="PageServlet?page=1&pageSize=10">第一页</a>
		<c:if test="${npage>1}">
			<a href="PageServlet?page=${npage-1}&pageSize=10">上一页</a>
		</c:if>
		<c:forEach begin="${npage-3>1?(npage-3):1}"
			end="${(npage+5)<=total?(npage+5):total}" var="i">


			<c:if test="${npage!=i }">
				<a href="PageServlet?page=${i}&pageSize=10">${i }</a>
			</c:if>
			<c:if test="${npage==i }">
				<a style="color: red;" href="PageServlet?page=${i}&pageSize=10"></a>
			</c:if>

		</c:forEach>
		<a href="PageServlet?page=${total}&pageSize=10">最后一页</a>
		<c:if test="${npage<total}">
			<a href="PageServlet?page=${npage+1}&pageSize=10">上一页</a>
		</c:if>

	</div>
</body>
</html>