// 開啟彈跳視窗
$(document).on("click", "#seeAns", function () {
	$(".overlayTag").fadeIn();
	
	//綁定quesId
	$("#quesId").val($(this).val());

	let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/getAContentServlet'
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
			if (R != null) {
				let aContent = `${R}`;
				$("#ansContent").html(aContent);
			}else{
				$("#ansContent").html(" ");
			}
		})
});

// 關閉彈跳視窗
$(document).on("click", ".close, div.overlayTag, .tagSubmit", function (e) {
	$("div.overlayTag").fadeOut();
});

//取消冒泡事件
$(document).on("click", "div.overlayTag > article", function (e) {
	e.stopPropagation();
});


//請輸入問題內容
$(".btn").click(function () {
	let questionContent = ($(".formContent").val());

	if (questionContent == "") {
		$(".inputQues").slideToggle("slow");
	}
});

function clear(){
	$("#questionContent").val("");
}

//fetch部分
$("#submitBtn").click(function () {
	let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/inserQuesServlet'
	let memberId = document.querySelector(".memberId").value;
	let questionContent = document.querySelector(".formContent").value;
	let questionTypeID = document.querySelector(".questionTypeID").value;
	//    console.log(memberId);

	fetch(TagInsertURL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			memberId,
			questionContent,
			questionTypeID
		})
	})
		.then(resp => resp.json())//後端傳給前端的格式
		.then(R => {
			try {

			} finally {
				query();
				clear();
			}
		})
})



function query() {
	let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/questionContent'
	let memberId = document.querySelector(".memberId").value;
	//    console.log(memberId);
	fetch(TagInsertURL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			memberId
		})
	})
		.then(resp => resp.json())//後端傳給前端的格式
		.then(R => {
			let quesList = "";
			// console.log(R.result.length);
			for (let i = 0; i < R.result.length; i++) {

				if (R.result[i].answerContent == null) {
					R.result[i].answerContent = "";
				}
				if (R.result[i].answerTime == null) {
					R.result[i].answerTime = "";
				}

				quesList += `
                <tr>
                <td>${R.result[i].questionContentID}</td>
                <td>${R.result[i].memberId}</td>
                <td>${R.result[i].questionTypeID}</td>
                <td class="table_tit">${R.result[i].questionContent}</td>              
				<td class="table_tit"><button id="seeAns" value="${R.result[i].questionContentID}">查看</button>
				${R.result[i].answerContent}
				</td>
                <td>${R.result[i].quesTime}</td>
                <td>${R.result[i].answerTime}</td>
            </tr>
            `
			}
			$(".quesList").html(quesList);
		})
}