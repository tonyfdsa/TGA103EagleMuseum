//登入後顯示使用者名字
window.addEventListener("load", function() {
	if(sessionStorage.getItem('memberName') != null){
      document.querySelector('#memberName').textContent = 
      sessionStorage.getItem('memberName') + "，您好！";
    }
  });


// 開啟彈跳視窗
$(document).on("click", "#seeAns", function() {
	$(".overlayTag").fadeIn();

	//綁定quesId
	$("#quesId").val($(this).val());

	let TagInsertURL = '/TGA103eagleMuseum/getAContentServlet'
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
			} else {
				$("#ansContent").html(" ");
			}
		})
});

// 關閉彈跳視窗
$(document).on("click", ".close, div.overlayTag, .tagSubmit", function(e) {
	$("div.overlayTag").fadeOut();
});

//取消冒泡事件
$(document).on("click", "div.overlayTag > article", function(e) {
	e.stopPropagation();
});


//請輸入問題內容
$("#submitBtn").click(function() {
	let questionContent = ($(".formContent").val());

	if (questionContent == "") {
		$(".inputQues").slideToggle("slow");
	}
	//	else if(!questionContent == ""){
	//		$(".inputQues").
	//	}
});

function clear() {
	$("#questionContent").val("");
}

//清除按鈕
$("#clearBtn").click(clear());

//fetch部分
//提交問題
$("#submitBtn").click(function() {
	let TagInsertURL = '/TGA103eagleMuseum/inserQuesServlet'
	let memberId = sessionStorage.getItem('id');
	let questionContent = document.querySelector(".formContent").value;
	let questionTypeID = document.querySelector(".questionTypeID").value;
//	console.log(memberId);
//	console.log(questionContent);
//	console.log(questionTypeID);

	fetch(TagInsertURL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			memberId,
			questionContent,
			questionTypeID
		})
	})
		.then(resp => resp.json())
		.then(R => {
			console.log(R);
			if(R.code == 200){			
				query();				
				clear();
			}

		})
})

//提交後的Query功能
function query() {
	let TagInsertURL = '/TGA103eagleMuseum/questionContent'
	let memberId = sessionStorage.getItem('id');

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


//查看問題
$("#queryBtn").click(function query() {
	let TagInsertURL = '/TGA103eagleMuseum/questionContent'
	let memberId = sessionStorage.getItem('id');

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
})