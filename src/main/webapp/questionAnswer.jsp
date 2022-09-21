<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>questionAnswer</title>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

</head>
<body>

	<h1>問題管理頁面</h1>
	<form action="questionAns" method="POST">
		<!-- <input type="text" id="questionTypeID" name="questionTypeID"> -->
		<!--<select name="questionTypeID">
			<option value = 1>交通問題</option>
			<option value = 2>展覽問題</option>
			<option value = 3>商品問題</option>
			<option value = 4>失物問題</option>
			<option value = 5>會員問題</option>
			<option value = 6>其他</option>
		</select>-->

		<div>搜尋發文日期：</div>
		<label for="lastUpdateDate1">從:</label> <input name="lastUpdateDate1"
			id="f_date1" type="text"> <label for="lastUpdateDate2">到:</label>
		<input name="lastUpdateDate2" id="f_date2" type="text"> <br>
		<label for="memberId">搜尋會員帳號：</label>  <br> <input name="memberId">
		<br>
		<!-- <textarea id="questionContent" name="questionContent"></textarea> -->
		<input type="submit">
		
<!-- 		<textarea id="ansContent" name="ansContent"></textarea> -->
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

<link rel="stylesheet" type="text/css"
	href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
	//日期選擇器
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function() {
		$('#f_date1').datetimepicker({
			format : 'Y-m-d',
			onShow : function() {
				this.setOptions({
					maxDate : $('#f_date2').val() ? $('#f_date2').val() : false
				})
			},
			timepicker : false
		});

		$('#f_date2').datetimepicker({
			format : 'Y-m-d',
			onShow : function() {
				this.setOptions({
					minDate : $('#f_date1').val() ? $('#f_date1').val() : false
				})
			},
			timepicker : false
		});
	});
</script>



</html>