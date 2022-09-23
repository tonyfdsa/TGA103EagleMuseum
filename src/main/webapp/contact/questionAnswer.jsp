<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>questionAnswer</title>

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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/contact/css/quesAns.css">

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

			<h1>問題管理頁面</h1>
			<form action="<%=request.getContextPath()%>/questionAns"
				method="POST">
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
				<label for="lastUpdateDate1">從:</label> <input
					name="lastUpdateDate1" id="start_date" type="date"> <label
					for="lastUpdateDate2">到:</label> <input name="lastUpdateDate2"
					id="end_date" type="date"> <br> <label for="memberId">搜尋會員帳號：</label>
				<br> <input name="memberId"> <br> <input
					type="submit">
			</form>
			<hr>
			<form action="<%=request.getContextPath()%>/questionAns">
				<div>指定要答覆的問題：</div>
				<input name="quesId"> <br>
				<div>請輸入答覆內容：</div>
				<textarea id="ansContent" name="ansContent"></textarea>
				<br> <input type="submit">
			</form>
			<div>${result}</div>
			<hr>
			<table class="table">
				<thead>
					<tr class="thead">
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
							<td><button class="replybtn"
									onclick="openBox(${questions.questionContentID})">回覆</button>${questions.answerContent}</td>
							<td>${questions.answered}</td>
							<td>${questions.quesTime}</td>
							<td>${questions.answerTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>

<!-- 		<div class="overlayTag"> -->
<!-- 			<article> -->
<!-- 				<h4>商品標籤新增</h4> -->
<!-- 				<div style="height: 10px;"></div> -->
<!-- 				<span>商品標籤: <input type="text" name="" id="" class="ProdType"></span> -->
<!-- 				<span><input type="submit" class="tagSubmit" -->
<!-- 					style="margin-top: 10px;" value="送出"></span> <span><input -->
<!-- 					type="submit" class="btnClose" style="margin-top: 10px;" value="關閉"></span> -->
<!-- 			</article> -->
<!-- 		</div> -->

	</div>

</body>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script
	src="<%=request.getContextPath()%>/contact/vendor/js/jquery3.6.0.js"></script>
<script src="<%=request.getContextPath()%>/contact/JS/backtemplete.js"></script>
<%-- <script src="<%=request.getContextPath()%>/contact/JS/backprodmeau.js"></script> --%>
<script src="<%=request.getContextPath()%>/contact/JS/quesAns.js"></script>
<script>


</script>

</html>