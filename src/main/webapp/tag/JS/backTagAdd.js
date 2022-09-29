(() => {
    const tagName = document.querySelector("#tagName");
    const errMsg = document.querySelector('#errMsg');

    document.getElementById("addSave").addEventListener("click", () => {

      if (tagName.value == ""){
        errMsg.textContent = "名稱未填寫"
        return;
      }


      fetch('http://localhost:8080/TGA103eagleMuseum/tagAdd', {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          tag: tagName.value,
        }),
      })
        .then(resp => resp.json())
        .then(body => {
          errMsg.textContent = '';
          const { successful, message } = body;
          if (successful) {
            location = './backTag.html';
          } else {
            errMsg.textContent = message;
          }
        });
    })
})();