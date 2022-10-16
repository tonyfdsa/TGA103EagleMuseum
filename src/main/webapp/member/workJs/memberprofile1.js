(() => {
    
   let memberEmail = sessionStorage.getItem("Email")
   console.log(memberEmail);
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
        let memberGender = "";
        if (member.memberGender == "1") {
            memberGender = "男";
        } else if (member.memberGender == "2") {
            memberGender = "女";
        }else if (member.memberGender == "3") {
            memberGender = "其他";
        }else if (member.memberGender == "4") {
            memberGender = "不透露";
        }
        let text = `

        <tr>
          <td>姓名 :</td>
        </tr>
        <tr>
          <td>${member.memberName}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>帳號 :</td>
        </tr>
        <tr>
          <td>${member.memberEmail}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>驗證問題 :</td>
        </tr>
        <tr>
          <td>${member.memberQA}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>驗證回答 :</td>
        </tr>
        <tr>
          <td>${member.memberAns}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>地址 :</td>
        </tr>
        <tr>
          <td>${member.memberAddress}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td>手機 :</td>
        </tr>
        <tr>
          <td>0${member.memberPhone}</td>
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
          <td>${member.memberBirthday}</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        `;
        console.log(text);
        $(".gino").append(text);
        console.log(11);

    });
})();
