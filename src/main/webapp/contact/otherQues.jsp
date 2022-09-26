<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>otherQues</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/contact/css/templete.css">

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
	href="<%=request.getContextPath()%>/contact/css/otherQuestions.css">

</head>


<body>
	<!-- head -->
	<div class="wrap">
		<div class="row d-flex">
			<div class="col-1" alt="大頭貼">
				<img src="./img/eagleIcon.jpg" class="min_picture"></IMG>
			</div>
			<div class="col-5">
				<h1 style="text-align: center;">Eagle Museum</h1>
			</div>
			<div class="col-6 row d-flex menu btn-group" id="meau">
				<ul class="drop-down-menu d-flex" style="justify-content: end;">
					<li><a href="#" class="topTag">首頁</a></li>
					<li><a href="exhibition.html" class="topTag">展覽</a></li>
					<li><a href="collectionSearch 3.0.html" class="topTag">館藏</a>
						<ul>
							<li class="dropMeau"><a href="collectionSearch 3.0.html">館藏資訊</a></li>
							<li class="dropMeau"><a href="#">館藏地圖</a></li>
						</ul></li>
					<li><a href="#" class="topTag">活動</a></li>
					<li><a href="#" class="topTag">商城</a>
						<ul>
							<li class="dropMeau"><a href="#">商品列表</a></li>
							<li class="dropMeau"><a href="#">我的購物車</a></li>
							<li class="dropMeau"><a href="./buyerCheck.html">訂單查詢</a></li>
							<li class="dropMeau"><a href="#">最愛清單</a></li>
						</ul></li>
					<li><a href="#" class="topTag">會員中心</a>
						<ul>
							<li class="dropMeau"><a href="./memberboot.html">會員資料</a></li>
							<li class="dropMeau"><a href="#">訂單資料</a></li>
							<li class="dropMeau"><a href="#">我的收藏</a></li>
						</ul></li>
					<li><a href="./contactUs(bootstrap).html" class="topTag">聯絡我們</a>
						<ul>
							<li class="dropMeau"><a href="./trafic.html">交通資訊</a></li>
							<li class="dropMeau"><a href="./memberJoin1.html">合作提案</a></li>
							<li class="dropMeau"><a href="#">捐款</a></li>
							<li class="dropMeau"><a href="#">問題回饋</a></li>
							<li class="dropMeau"><a href="#">租借場地</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>




	<div class="upperColumn" id="topcontent"></div>

	<!-- </div> -->

	<!-- main -->
	<div class="main">
		<div class="container">
			<div>
				<div class="page_sign">
					<a href="#">聯絡我們</a>→ <a href="#" style="margin-left: 0;">常見問題</a>
					<p>→其他問題</p>
				</div>
			</div>
			<form action="<%=request.getContextPath()%>/questionContent"
				method="POST">
				<div class="questionTypeSearch1">
					<span>問題類型</span> <select name="questionTypeID">
						<option value=1>交通問題</option>
						<option value=2>展覽問題</option>
						<option value=3>商品問題</option>
						<option value=4>失物問題</option>
						<option value=5>會員問題</option>
						<option value=6>其他</option>
					</select> <br>
				</div>
				<div class="statement formTextName">
					<textarea id="questionContent" name="questionContent"
						class="formContent" rows="10" placeholder="請輸入您的疑問......"></textarea>
				</div>
				<div class="confirm">
					<input class="btn" id="submitBtn" type="submit"> <span>
						<button class="btn" id="clearBtn">清除</button>
					</span>
					<div class="hint">${result}</div>
				</div>

			</form>
			<hr>
			<table class="table">
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
	<!-- footer -->
	<div class="footer"></div>

	<!-- jQuery 位置 -->
	<script
		src="<%=request.getContextPath()%>/contact/vendor/js/jquery3.6.0.js"></script>
	<!-- 模板js匯入 位置 -->
	<!-- <script src="./workJS/Template .js"></script> -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>


	<script>
		
	</script>
</body>

</html>