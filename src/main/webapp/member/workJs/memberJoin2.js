(() => {
	const username = document.querySelector('#username');
	const password = document.querySelector('#password');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('next').addEventListener('click', () => {
		// console.log('next');
		fetch('http://localhost:8080/TGA103eagleMuseum/member/#', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memberEmail: username.value,
				memberPassword: password.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				console.log(body)
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					const { memberID, memberEmail } = body;
					sessionStorage.setItem('id', memberID);
					sessionStorage.setItem('Email', memberEmail);
					location = 'memberHome.html'
				} else {
					errMsg.textContent = message;
				}
			});
	});
})();


function setSelectUserNo(radioObj) {

	var radioCheck = $(radioObj).val();
	if ("1" == radioCheck) {
	  $(radioObj).attr("checked", false);
	  $(radioObj).val("0");

	} else {
	  $(radioObj).val("1");

	}
  }