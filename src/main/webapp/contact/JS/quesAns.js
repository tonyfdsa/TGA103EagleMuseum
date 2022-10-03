//清空答覆內容
function clear(){
	$("#ansContent").val("");
}

// 開啟彈跳視窗
$(document).on("click", ".replybtn", function () {
	$(".overlayTag").fadeIn();
	
	//綁定quesId
	$("#quesId").val($(this).val());
	

	let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/getQContentServlet'
	let questionContentID = document.querySelector("#quesId").value;

	fetch(TagInsertURL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			questionContentID,
		})
	})
		.then(resp => resp.json())//後端傳給前端的格式
		.then(R => {
			console.log(R);
			let qContent = `${R}`;
			$("#quesContent").html(qContent);
		})

	$(".reply").on("click", function () {
		let ansContent = ($("#ansContent").val());
		if (ansContent != "") {
			$("div.overlayTag").fadeOut();
		} else {
			$(".ansSubmit").slideToggle("slow");
		}
	})

});


// 關閉 標籤
$(document).on("click", ".close, div.overlayTag, .tagSubmit", function (e) {
	$("div.overlayTag").fadeOut();
	clear();
});

//取消冒泡事件
$(document).on("click", "div.overlayTag > article", function (e) {
	e.stopPropagation();
});

//日期不可為空
$(".searchByDate").click(function () {
	let start_date = ($("#start_date").val());
	let end_date = ($("#end_date").val());
	if (start_date == "") {
		// console.log(this);
		$(".needDates").find(".needSD").slideToggle("slow");
	}
	if (end_date == "") {
		// console.log(this);
		$(".needDates").find(".needED").slideToggle("slow");
	}
});

//會員Id不可為空
$(".searchById").click(function () {
	let memberId = ($("#memberId").val());

	if (memberId == "") {
		// console.log(this);
		$(".needId").find(".inputId").slideToggle("slow");
	}
});

//會員Id、日期皆不可為空
$(".searchByIAndD").click(function () {
	let memberId = ($("#memberId").val());
	let start_date = ($("#start_date").val());
	let end_date = ($("#end_date").val());

	if (memberId == "" || start_date == "" || end_date == "") {
		// console.log(this);
		$(".needIAndD").find(".inputIAndD").slideToggle("slow");
	}
});



//fetch部分
//列出全部問題
$(".searchAllQs").click(function () {
	let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/quesSearchAllServlet'

	fetch(TagInsertURL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({

		})
	})
		.then(resp => resp.json())//後端傳給前端的格式
		.then(R => {
			let quesList = "";
			for (let i = 0; i < R.result.length; i++) {

				if (R.result[i].answerTime == null) {
					R.result[i].answerTime = "";
				}
				quesList += `
                <tr>
                <td>${R.result[i].questionContentID}</td>
                <td>${R.result[i].memberId}</td>
                <td>${R.result[i].questionTypeID}</td>
				<td class="replytd"><button class="replybtn" value="${R.result[i].questionContentID}">回覆</button></td>
                <td class="table_tit">${R.result[i].questionContent}</td>
                <td>${R.result[i].answered}</td>
                <td>${R.result[i].quesTime}</td>
                <td>${R.result[i].answerTime}</td>
            </tr>
            `
			}
			$(".quesList").html(quesList);
		})
})

//館員SearchById
$(".searchById").click(function () {
	let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/quesSearchByIdServlet'
	let memberId = document.querySelector("#memberId").value;
	// let quesTime = document.querySelector("#start_date").value;
	// let answerTime = document.querySelector("#end_date").value;

	fetch(TagInsertURL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			memberId,
			// quesTime,
			// answerTime
		})
	})
		.then(resp => resp.json())//後端傳給前端的格式
		.then(R => {
			let quesList = "";
			for (let i = 0; i < R.result.length; i++) {

				if (R.result[i].answerTime == null) {
					R.result[i].answerTime = "";
				}

				quesList += `
                <tr>
                <td>${R.result[i].questionContentID}</td>
                <td>${R.result[i].memberId}</td>
                <td>${R.result[i].questionTypeID}</td>
				<td><button class="replybtn" value="${R.result[i].questionContentID}">回覆</button></td>
                <td class="table_tit">${R.result[i].questionContent}</td>
                <td>${R.result[i].answered}</td>
                <td>${R.result[i].quesTime}</td>
                <td>${R.result[i].answerTime}</td>
            </tr>
            `
			}
			$(".quesList").html(quesList);
		})
})

//館員SearchByDate
$(".searchByDate").click(function () {
	let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/quesSearchByDateServlet'
	// let memberId = document.querySelector(".memberId").value;
	let quesTime = document.querySelector("#start_date").value;
	let answerTime = document.querySelector("#end_date").value;

	fetch(TagInsertURL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			// memberId,
			quesTime,
			answerTime
		})
	})
		.then(resp => resp.json())//後端傳給前端的格式
		.then(R => {
			let quesList = "";
			for (let i = 0; i < R.result.length; i++) {

				if (R.result[i].answerTime == null) {
					R.result[i].answerTime = "";
				}

				quesList += `
                <tr>
                <td>${R.result[i].questionContentID}</td>
                <td>${R.result[i].memberId}</td>
                <td>${R.result[i].questionTypeID}</td>
				<td><button class="replybtn" value="${R.result[i].questionContentID}">回覆</button></td>
                <td class="table_tit">${R.result[i].questionContent}</td>
                <td>${R.result[i].answered}</td>
                <td>${R.result[i].quesTime}</td>
                <td>${R.result[i].answerTime}</td>
            </tr>
            `
			}
			$(".quesList").html(quesList);
		})
})

//館員SearchByIAndD
$(".searchByIAndD").click(function () {
	let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/quesSearchByIAndDServlet'
	let memberId = document.querySelector("#memberId").value;
	let quesTime = document.querySelector("#start_date").value;
	let answerTime = document.querySelector("#end_date").value;

	fetch(TagInsertURL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			memberId,
			quesTime,
			answerTime
		})
	})
		.then(resp => resp.json())//後端傳給前端的格式
		.then(R => {
			let quesList = "";
			for (let i = 0; i < R.result.length; i++) {
				if (R.result[i].answerTime == null) {
					R.result[i].answerTime = "";
				}
				quesList += `
                <tr>
                <td>${R.result[i].questionContentID}</td>
                <td>${R.result[i].memberId}</td>
                <td>${R.result[i].questionTypeID}</td>
				<td><button class="replybtn" value="${R.result[i].questionContentID}">回覆</button></td>
                <td class="table_tit">${R.result[i].questionContent}</td>
                <td>${R.result[i].answered}</td>
                <td>${R.result[i].quesTime}</td>
                <td>${R.result[i].answerTime}</td>
            </tr>
            `
			}
			$(".quesList").html(quesList);
		})
})



//館員回覆問題
$(document).on("click", ".reply", function () {
	
	// console.log("ok");
	let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/questionAns'
	let questionContentID = document.querySelector("#quesId").value;
	let answerContent = document.querySelector("#ansContent").value;

	fetch(TagInsertURL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			questionContentID,
			answerContent
		})
	})
		.then(resp => resp.json())//後端傳給前端的格式
		.then(R => {
			clear();
			let quesList = "";
			for (let i = 0; i < R.result.length; i++) {

				if (R.result[i].answerTime == null) {
					R.result[i].answerTime = "";
				}

				quesList += `
                <tr>
                <td>${R.result[i].questionContentID}</td>
                <td>${R.result[i].memberId}</td>
                <td>${R.result[i].questionTypeID}</td>
				<td><button class="replybtn" value="${R.result[i].questionContentID}">回覆</button></td>
                <td class="table_tit">${R.result[i].questionContent}</td>
                <td>${R.result[i].answered}</td>
                <td>${R.result[i].quesTime}</td>
                <td>${R.result[i].answerTime}</td>
            </tr>
            `
			}
			$(".quesList").html(quesList);
			
		})

});
