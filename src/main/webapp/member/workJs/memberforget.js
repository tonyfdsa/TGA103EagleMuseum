(() => {

    // 驗證碼寄送
	const username = document.querySelector('#username');
	const errMsg = document.querySelector('#errMsg');

	document.getElementById('button-addon2').addEventListener('click', () => {
		console.log(username.value);
		console.log(memAns.value);
		fetch('/TGA103eagleMuseum/member/forgetpass', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memberEmail: username.value,
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					window.alert("驗證碼已寄出，請至信箱查詢！");
				} else {
					errMsg.textContent = message;
				}
                console.log("寄了");
			});
	});

	


    // 驗證
    const password = document.querySelector('#password');
	const confirm_password = document.querySelector('#confirm_password');
    const memQA = document.querySelector('#memQA');
    const memAns = document.querySelector('#memAns');
	const captcha = document.querySelector('#captcha');
    console.log("準備了");
	document.getElementById('password').addEventListener('input', () => {
		const pass = password.value.length;
		// console.log(pass);
		if (pass < 8 || pass > 50) {
			errMsg.textContent = '密碼長度須介於8~50字元';
			return;
		} else {
			errMsg.textContent = '';
		}
        console.log("密碼");
	});
	document.getElementById('confirm_password').addEventListener('input', () => {
		if (confirm_password.value !== password.value) {
			// console.log(confirm_pass);
			errMsg.textContent = '密碼與確認密碼不相符';
			return;
		} else {
			errMsg.textContent = '';
		}
	});
	document.getElementById('into').addEventListener('click', () => {
		const pass = password.value.length;
		if (pass < 8 || pass > 50) {
			errMsg.textContent = '密碼長度須介於8~50字元';
			return;
		}
		if (confirm_password.value !== password.value) {
			errMsg.textContent = '密碼與確認密碼不相符';
			return;
		}
        console.log(captcha.value);
        console.log(password.value)
		fetch('/TGA103eagleMuseum/member/UpdatePass', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memberNewPass: password.value,
                memberQA: memQA.value,
                memberAns: memAns.value,
                captcha: captcha.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					window.alert("密碼修改成功！");
					location = 'memberboot.html';
				} else {
					errMsg.textContent = message;
				}
                console.log("改了");
			});
	});


})();

