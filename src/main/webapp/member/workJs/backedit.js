(() => {
  $("#myModal").on("shown.bs.modal", function () {
    $("#myInput").trigger("focus");
  });

  let memberEmail = sessionStorage.getItem("form_data");
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
      document.querySelector("#name").textContent = member.memberID;
      document.querySelector("#username").textContent = member.memberName;
      //   console.log(document.querySelector("#usname").textContent);
      document.querySelector("#email").textContent = member.memberEmail;
      document.querySelector("#phone").textContent = member.memberPhone;
      document.querySelector("#address").textContent = member.memberAddress;
      if (member.memberGender == "1") {
        document.querySelector("#gender").textContent = "男";
      } else if (member.memberGender == "2") {
        document.querySelector("#gender").textContent = "女";
      } else if (member.memberGender == "3") {
        document.querySelector("#gender").textContent = "其他";
      } else if (member.memberGender == "4") {
        document.querySelector("#gender").textContent = "不願透露";
      }
      console.log(member.memberGender);
      document.querySelector("#birth").textContent = member.memberBirthday;
      document.querySelector("#qa").textContent = member.memberQA;
      document.querySelector("#ans").textContent = member.memberAns;
      document.querySelector("#update").textContent = member.modifyTime;
      document.querySelector("#lastlogin").textContent = member.lastEnterTime;
      document.querySelector("#status").textContent = member.memberPermission;
      console.log(1233);

      document.querySelector("#username-input").value = member.memberName;
      document.querySelector("#email-input").textContent = member.memberEmail;
      document.getElementById("phone-input").value = member.memberPhone;
      document.getElementById("address-input").value = member.memberAddress;
      if (member.memberGender == "1") {
        document.getElementById("gender-input1").checked = true;
      } else if (member.memberGender == "2") {
        document.getElementById("gender-input2").checked = true;
      } else if (member.memberGender == "3") {
        document.getElementById("gender-input3").checked = true;
      } else if (member.memberGender == "4") {
        document.getElementById("gender-input4").checked = true;
      }

      document.getElementById("birth-input").value = member.memberBirthday;
      document.getElementById("qa-input").value = member.memberQA;
      document.getElementById("ans-input").value = member.memberAns;
      if (member.memberPermission == "1") {
        document.getElementById("status-input1").checked = true;
      } else if (member.memberPermission == "2") {
        document.getElementById("status-input2").checked = true;
      } else if (member.memberPermission == "3") {
        document.getElementById("status-input3").checked = true;
      }
    });

  // 編輯資料
  const name = document.querySelector("#username-input");
  const phone = document.querySelector("#phone-input");
  const address = document.querySelector("#address-input");
  const gender = document.getElementsByName("status1");
  const birth = document.querySelector("#birth-input");
  const memQA = document.querySelector("#qa-input");
  const memAns = document.querySelector("#ans-input");
  const status = document.getElementsByName("status2");
  const errMsg = document.querySelector("#errMsg");

  document.getElementById("edit").addEventListener("click", () => {
    let selected_status;
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
    if (status[0].checked == true) {
      selected_status = status[0];
    } else if (status[1].checked == true) {
      selected_status = status[1];
    } else if (status[2].checked == true) {
        selected_status = status[2];
    }
    console.log(name.value);
    console.log(selected_status.value);
    console.log(selected_gender.value);
    fetch("/TGA103eagleMuseum/member/manageredit", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        memberName: name.value,
        memberPhone: phone.value,
        memberAddress: address.value,
        memberGender: selected_gender.value,
        memberBirthday: birth.value,
        memberQA: memQA.value,
        memberAns: memAns.value,
        memberPermission: selected_status.value,
        memberEmail: memberEmail
    }),
})
      .then((resp) => resp.json())
      .then((body) => {
        errMsg.textContent = "";
        const { successful, message } = body;
        if (successful) {
          location = "backEdit.html";
        } else {
          errMsg.textContent = message;
        }
      });
  });



})();
