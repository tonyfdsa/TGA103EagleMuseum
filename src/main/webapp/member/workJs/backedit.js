(() => {
    $('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	});

  let memberEmail = sessionStorage.getItem("Email");
  console.log(memberEmail);
  // 一進來get member資料
  fetch("/TGA103eagleMuseum/member/selectSelf", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      memberEmail: memberEmail,
    }),
  })
    .then((resp) => resp.json())
    .then((member) => {
      console.log(member);
      let memberGender = "";
      if (member.memberGender == "1") {
        memberGender = "男";
      } else if (member.memberGender == "2") {
        memberGender = "女";
      } else if (member.memberGender == "3") {
        memberGender = "其他";
      } else if (member.memberGender == "4") {
        memberGender = "不透露";
      }
    });

  //submit insert
  $(document).on("click", ".submit", function () {
    let prodStatusURL = "http://localhost:8081/TGA103_EagleMuseum/ProdInsert";
    let prodName = document.querySelector(".prodName").value;
    let prodTypeID = document.querySelector(".prodTypeID").value;
    let prodPrice = document.querySelector(".prodPrice").value;
    let prodDescription = document.querySelector(".prodDescription").value;
    let prodInStock = document.querySelector(".prodInStock").value;
    console.log(prodName);
    console.log(prodTypeID);
    // console.log(prodPrice);
    // console.log(prodDescription);
    // console.log(prodInStock);

    fetch(prodStatusURL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        prodName,
        prodTypeID,
        prodPrice,
        prodDescription,
        prodInStock,
      }),
    })
      .then((resp) => resp.json())
      .then((R) => {
        console.log(R);
      });
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
    fetch("/TGA103eagleMuseum/member/edit", {
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
