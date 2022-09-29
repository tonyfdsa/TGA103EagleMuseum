//請輸入問題內容
$(".btn").click(function(){
    let questionContent = ($(".formContent").val());
	
    if(questionContent == ""){
        console.log("ok");
        $(".inputQues").slideToggle("slow"); 
    }
});

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
						
			// let hint = '提問已送出';
			// if (R.code == 200) {
			// 	// $(".hint").innerText.remove();
			// 	$(".hint").html(hint);
			// }
			// let hint2 = '提問失敗，請輸入內容。';
			// if (R.code == 400) {
			// 	// $(".hint").innerText.remove();
			// 	$(".hint").html(hint2);
			// }

			try {
				
			} finally  {
				query();
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
			//	if (R.code == 400) {
			// ${".hint"}.
			//			}
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
                <td>${R.result[i].questionContent}</td>
                <td>${R.result[i].answerContent}</td>
                <td>${R.result[i].quesTime}</td>
                <td>${R.result[i].answerTime}</td>
            </tr>
            `
			}
			$(".quesList").html(quesList);
		})
}