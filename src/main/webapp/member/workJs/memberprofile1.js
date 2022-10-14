(() => {
    
   let memberEmail = sessionStorage.getItem("Email")
  // 一進來get member資料
  fetch("http://localhost:8080/TGA103eagleMuseum/member/selectSelf",{
    method: 'POST',
	headers: { 'Content-Type': 'application/json' },
	body: JSON.stringify({
	memberEmail: memberEmail
			}),
		})

    .then(resp => resp.json())
    .then(member => {
        console.log(member)
      for (i = 0; i < member["length"]; i++) {
        let memberGender = "";
        if (member[i].memberGender == "0") {
            memberGender = "男";
        } else if (member[i].memberGender == "1") {
            memberGender = "女";
        }else if (member[i].memberGender == "2") {
            memberGender = "女";
        }else if (member[i].memberGender == "3") {
            memberGender = "女";
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
          <td>${member[i].memberAddress}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>手機 :</td>
        </tr>
        <tr>
          <td>o${member[i].memberPhone}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>性別 :</td>
        </tr>
        <tr>
          <td>${memberGender}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>生日 :</td>
        </tr>
        <tr>
          <td>${member[i].memberBirthday}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        `;

        $(".gino").append(text);
        console.log(11);
      }

    });
})();
