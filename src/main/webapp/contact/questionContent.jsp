<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>questionContent</title>
</head>
<body>

	<h1>會員提問頁面</h1>
	<form action="<%=request.getContextPath()%>/questionContent" method="POST">
		<!-- <input type="text" id="questionTypeID" name="questionTypeID"> -->
		<select name="questionTypeID">
			<option value = 1>交通問題</option>
			<option value = 2>展覽問題</option>
			<option value = 3>商品問題</option>
			<option value = 4>失物問題</option>
			<option value = 5>會員問題</option>
			<option value = 6>其他</option>
		</select>
		<br>
		<textarea id="questionContent" name="questionContent"></textarea>
		<input type="submit">
	</form>
	<div>${result}</div>
	<hr>
	<table border="1">
		<thead>
			<tr>
				<th>questionContentID</th>
				<th>memberId</th>
				<th>questionTypeID</th>
				<th>questionContent</th>
				<th>answerContent</th>
				<th>answered</th>
				<th>lastUpdateTime</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="questions" items="${questionList}">
				<tr>
					<td>${questions.questionContentID}</td>
					<td>${questions.memberId}</td>
					<td>${questions.questionTypeID}</td>
					<td>${questions.questionContent}</td>
					<td>${questions.answerContent}</td>
					<td>${questions.answered}</td>
					<td>${questions.lastUpdateTime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>