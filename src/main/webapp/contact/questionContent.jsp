<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>questionContent</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/contact/css/backtemplete.css">

<!-- bootstrapr -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous" />
<!-- 正黑體字型包 -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/prod/workCss/backprodmeau.css">

</head>

<body>
	<!-- header -->
	<header>
		<div class="header">
			<div>
				<img src="<%=request.getContextPath()%>/contact/img/eagle2.jpg"
					alt="" class="headImg" /> <span class="user">使用者</span>
			</div>
		</div>
	</header>
	<!-- aside -->

	<div class="aside">
		<!-- 頂部留白 -->
		<div style="height: 20px"></div>
		<!-- 大標籤 -->
		<ul>
			<li>
				<div class="sideBtn">首頁管理</div>
				<div class="Btn">
					<a href="#">展覽資訊</a>
				</div>
				<div class="Btn">
					<a href="#">商品資訊</a>
				</div>
				<div class="Btn">
					<a href="#">館方資訊</a>
				</div>
			</li>
			<li>
				<div class="sideBtn">展覽管理</div>
				<div class="Btn">
					<a href="#">票卷管理</a>
				</div>
			</li>
			</li>
			<li>
				<div class="sideBtn">館藏管理</div>
				<div class="Btn">
					<a href="#">館藏列表</a>
				</div>
			</li>
			<li>
				<div class="sideBtn">商城管理</div>
				<div class="Btn">
					<a href="#">訂單搜尋</a>
				</div>
				<div class="Btn">
					<a href="#">商品搜尋</a>
				</div>
			</li>
			<li>
				<div class="sideBtn">會員管理</div>
				<div class="Btn">
					<a href="#">會員搜尋</a>
				</div>
			</li>
			<li>
				<div class="sideBtn">聯絡管理</div>
				<div class="Btn">
					<a href="#">常見問題</a>
				</div>
				<div class="Btn">
					<a href="#">其他問題</a>
				</div>
				<div class="Btn">
					<a href="#">交通資訊</a>
				</div>
				<div class="Btn">
					<a href="#">租借場地</a>
				</div>
			</li>
			</li>
		</ul>
	</div>

	<div class="main">

		<div>
			<h1>會員提問頁面</h1>
			<form action="<%=request.getContextPath()%>/questionContent"
				method="POST">
				<!-- <input type="text" id="questionTypeID" name="questionTypeID"> -->
				<select name="questionTypeID">
					<option value=1>交通問題</option>
					<option value=2>展覽問題</option>
					<option value=3>商品問題</option>
					<option value=4>失物問題</option>
					<option value=5>會員問題</option>
					<option value=6>其他</option>
				</select> <br>
				<textarea id="questionContent" name="questionContent"></textarea>
				<input type="submit">
			</form>
			<div>${result}</div>
			<hr>
			<table border="1">
				<thead>
					<tr>
						<th>問題編號</th>
						<th>會員ID</th>
						<th>問題類型</th>
						<th>問題內容</th>
						<th>答覆內容</th>
						<th>答覆狀態</th>
						<th>提問時間</th>
						<th>答覆時間</th>
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
							<td>${questions.quesTime}</td>
							<td>${questions.answerTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/contact/vendor/js/jquery3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/contact/JS/backtemplete.js"></script>
<script src="<%=request.getContextPath()%>/contact/JS/quesAns.js"></script>
<%-- <script src="<%=request.getContextPath()%>/contact/JS/backprodmeau.js"></script> --%>

</html>