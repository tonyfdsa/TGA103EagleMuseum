(() => {
	//抓sessionStorage的資料

	var form_data = JSON.parse(sessionStorage.getItem("form_data"));
	var tagID = form_data.tagID
	// 把ID往下帶
	fetch("http://localhost:8080/TGA103eagleMuseum/tagGetOne", {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			tagID: tagID,

		}),
	})
		.then(resp => resp.json())
		.then(tag_ID => {
			document.querySelector('.tagName').value = tag_ID.tag;

		})
	//submit insert
	$(document).on("click", ".submit", function() {
		let tag = document.querySelector(".tagName").value;
		let errMsg = document.querySelector('#errMsg');

		if (tag == "") {
			errMsg.textContent = "名稱不可為空"
			return;
		}
	
		fetch('http://localhost:8080/TGA103eagleMuseum/tagUpdate', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				tag,
				tagID: tagID
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './backTag.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
})();