(() => {
  let memberEmail = sessionStorage.getItem("Email");
  // console.log(memberEmail);
  // GET session 出現資料庫資料
  fetch("http://localhost:8080/TGA103eagleMuseum/member/selectSelf", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      memberEmail: memberEmail,
    }),
  })
    .then((resp) => resp.json())
    .then((member) => {
      document.querySelector("#usname").value = member.memberName;
    //   console.log(document.querySelector("#usname").textContent);
      document.querySelector("#username").textContent = member.memberEmail;
      document.querySelector("#pass").value = member.memberPassword;
	  document.querySelector("#password").value = member.memberPassword;
	  document.querySelector("#confirm_password").value = member.memberPassword;
	  document.querySelector("#memQA").value = member.memberQA;
	  document.querySelector("#memAns").value = member.memberAns;
	  document.querySelector("#address").value = member.memberAddress;
      document.querySelector('#phone').value = member.memberPhone;
      if (member.memberGender == "1") {
      	document.querySelector('#inlineRadio').checked = "男";
      } else if (member.memGender == "2") {
      	document.querySelector('#inlineRadio').checked = "女";
      } else if (member.memGender == "3") {
      	document.querySelector('#inlineRadio').checked = "其他";
      } else if (member.memGender == "4") {
      	document.querySelector('#inlineRadio').checked = "不透露";
      }
	  console.log(member.memberGender);
      document.querySelector("#birth").value = member.memberBirthday;
  
      console.log(1233);
    });

  // 編輯資料
  const username = document.querySelector("#username");
  const usname = document.querySelector("#usname");
  const password = document.querySelector("#password");
  const confirm_pass = document.querySelector("#confirm_password");
  const memQA = document.querySelector("#memQA");
  const memAns = document.querySelector("#memAns");
  const address = document.querySelector("#address");
  const phone = document.querySelector("#phone");
  const birth = document.querySelector("#birth");
  const errMsg = document.querySelector("#errMsg");
  const gender = document.getElementsByName("inlineRadioOptions");
  

  document.getElementById("password").addEventListener("input", () => {
    const pass = password.value.length;
    // console.log(pass);
    if (pass < 8 || pass > 50) {
      errMsg.textContent = "密碼長度須介於8~50字元";
      return;
    } else {
      errMsg.textContent = "";
    }
  });
  document.getElementById("confirm_password").addEventListener("input", () => {
    if (confirm_pass.value !== password.value) {
      // console.log(confirm_pass);
      errMsg.textContent = "密碼與確認密碼不相符";
      return;
    } else {
      errMsg.textContent = "";
    }
  });
  document.getElementById("revise").addEventListener("click", () => {
    let selected_gender;
    if (gender[0].checked == true) {
      selected_gender = gender[0];
    } else if (gender[1].checked == true) {
      selected_gender = gender[1];
    } else if (gender[2].checked == true) {
      selected_gender = gender[2];
    } else if (gender[3].checked == true) {
      selected_gender = gender[3];
    }

    const pass = password.value.length;
    if (pass < 8 || pass > 50) {
      errMsg.textContent = "密碼長度須介於8~50字元";
      return;
    }
    if (confirm_pass.value !== password.value) {
      errMsg.textContent = "密碼與確認密碼不相符";
      return;
    }
	console.log(58585);
    fetch("http://localhost:8080/TGA103eagleMuseum/member/edit", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        memberName: usname.value,
        memberPassword: password.value,
        memberPass: confirm_pass.value,
        memberQA: memQA.value,
        memberAns: memAns.value,
        memberAddress: address.value,
        memberPhone: phone.value,
        memberGender: selected_gender.value,
        memberBirthday: birth.value,
      }),
    })
      .then((resp) => resp.json())
      .then((body) => {
        errMsg.textContent = "";
        const { successful, message } = body;
        if (successful) {
          location = "memberprofile.html";
        } else {
          errMsg.textContent = message;
        }
        console.log("跑到更新了");
      });
  });
})();
