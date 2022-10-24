(() => {
  const username = document.querySelector("#username");
  const password = document.querySelector("#password");
  const errMsg = document.querySelector("#errMsg");
  document.getElementById("next").addEventListener("click", () => {
    // console.log('next');
    fetch("/TGA103eagleMuseum/member/manlogin", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        memberEmail: username.value,
        memberPassword: password.value,
      }),
    })
      .then((resp) => {
        sessionStorage.setItem("url", resp.headers.get("url"));
        return resp.json();
      })
      .then((body) => {
        console.log(body.headers);
        errMsg.textContent = "";
        const { successful, message, memberName} = body;
        if (successful) {
          const { memberID, memberEmail } = body;
          sessionStorage.setItem("id", memberID);
          sessionStorage.setItem("Email", memberEmail);
          sessionStorage.setItem("memberName", memberName);
          console.log(memberEmail);
          if (sessionStorage.getItem("url") != "null") {
            location = sessionStorage.getItem("url");
          } else {
            console.log(body);
            location = "./toBeFilteredBack/backHome.html";
          }
        } else {
          errMsg.textContent = message;
        }
      });
  });
})();
