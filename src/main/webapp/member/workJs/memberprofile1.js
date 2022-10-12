(() => {
	// 一進來get all
	fetch("http://localhost:8080/simplefitness-servlet/member/getAll")
	.then(resp => resp.json())
	.then(member => {
		for (i = 0; i < member["length"]; i++){
			let status = "";
			if (member[i].memStatus == "0") {
				status = "無會籍"
			} else if (member[i].memStatus == "1") {
				status = "有會籍"
			}
		  let text = `

        <tr>
          <td>姓名 :</td>
        </tr>
        <tr>
          <td${member[i].memberName}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>帳號 :</td>
        </tr>
        <tr>
          <td>${member[i].memberEmail}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>密碼 :</td>
        </tr>
        <tr>
          <td>${member[i].memberPasssword}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>驗證問題 :</td>
        </tr>
        <tr>
          <td>${member[i].memberQA}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>驗證回答 :</td>
        </tr>
        <tr>
          <td>${member[i].memberAns}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>地址 :</td>
        </tr>
        <tr>
          <td>${member[i].member}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>手機 :</td>
        </tr>
        <tr>
          <td>${member[i].memId}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>性別 :</td>
        </tr>
        <tr>
          <td>${member[i].memId}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>生日 :</td>
        </tr>
        <tr>
          <td>${member[i].memId}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>




		  	<tr>
				<td>${member[i].memId}</td>
				<td>${member[i].memName}</td>
				<td>${member[i].memNickname}</td>
				<td>${member[i].memRegister}</td>
				<td>${status}</td>
			
	  		</tr>`;

			$(".gino").append(text);
		}
		// 點擊進入
		for (i = 0; i < member["length"]; i++){
		const button = document.querySelector(`#member${member[i].memId}`);
			button.addEventListener('click', () => {
				sessionStorage.setItem('member', button.value);
				location = './emp_member_edit.html';
			});
		}
	});

})();