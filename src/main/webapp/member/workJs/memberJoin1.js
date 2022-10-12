(() => {
	const usname = document.querySelector('#usname');
	const username = document.querySelector('#username');
	const password = document.querySelector('#password');
	const confirm_pass = document.querySelector('#confirm_password');
	const captcha = document.querySelector('#captcha');
	const memQA = document.querySelector('#memQA');
	const memAns = document.querySelector('#memAns');
	const address = document.querySelector('#address');
	const phone = document.querySelector('#phone');
	const birth = document.querySelector('#birth');
	const errMsg = document.querySelector('#errMsg');

	const gender = document.querySelectorAll('[name=inlineRadioOptions]').forEach(input => input.addEventListener('change', onChange))
		var onChange = () => {
    	var checked = document.querySelector('[name=inlineRadioOptions]:checked')
    	console.log('选中的值:', checked.value)
		}
		
	// const gender = document.querySelector("input[name='inlineRadioOptions']:checked");
	// console.log(gender.value);

	document.getElementById('password').addEventListener('input', () => {
		const pass = password.value.length;
		// console.log(pass);
		if (pass < 8 || pass > 50) {
			errMsg.textContent = '密碼長度須介於8~50字元';
			return;
		} else {
			errMsg.textContent = '';
		}
	});
	document.getElementById('confirm_password').addEventListener('input', () => {
		if (confirm_pass.value !== password.value) {
			// console.log(confirm_pass);
			errMsg.textContent = '密碼與確認密碼不相符';
			return;
		} else {
			errMsg.textContent = '';
		}
	});
	document.getElementById('next').addEventListener('click', () => {
		// const accLength = username.value.length;
		// if (accLength < 1 || accLength > 30) {
		// 	errMsg.textContent = '帳號長度須介於8~50字元';
		// 	return;
		// }
		const pass = password.value.length;
		if (pass < 8 || pass > 50) {
			errMsg.textContent = '密碼長度須介於8~50字元';
			return;
		}
		if (confirm_pass.value !== password.value) {
			errMsg.textContent = '密碼與確認密碼不相符';
			return;
		}

		fetch('http://localhost:8080/TGA103eagleMuseum/member/register', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memberName: usname.value,
				memberEmail: username.value,
				memberPassword: password.value,
				memberPass: confirm_pass.value,
				memberCaptcha: captcha.value,
				memberQA: memQA.value,
				memberAns: memAns.value,
				memberAddress: address.value,
				memerPhone: phone.value,
				memberGender: gender.value,
				memberBirthday: birth.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				console.log(body);
					errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					window.alert("註冊成功！");
					location = 'memberboot.html';
				} else {
					errMsg.textContent = message;

				}
			});
	});


})();