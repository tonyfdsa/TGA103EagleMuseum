(() => {
    const colTitle = document.querySelector("#colTitle");
    const colText = document.querySelector("#colText");
    const colEar = document.querySelector("#colEar");
    const colMaterial = document.querySelector("#colMaterial");
    const colStatus = document.getElementsByName("colStatus");
    const errMsg = document.querySelector('#errMsg');

    document.getElementById("addSave").addEventListener("click", () => {

      if (colTitle.value == ""){
        errMsg.textContent = "名稱未填寫"
        return;
      }

      if (colText.value == ""){
        errMsg.textContent = "説明未填寫"
        return;
      }

      if (colEar.value == ""){
        errMsg.textContent = "朝代未填寫"
        return;
      }

      if (colMaterial.value == ""){
        errMsg.textContent = "類別未填寫"
        return;
      }

      let selected_colStatus;
      if (colStatus[0].checked == true) {
        selected_colStatus = colStatus[0];
      } else if (colStatus[1].checked == true) {
        selected_colStatus = colStatus[1];
      }

      if (selected_colStatus == null){
        errMsg.textContent = "狀態未選"
        return;
      }



      fetch('http://localhost:8080/TGA103eagleMuseum/collectionAdd', {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          collectionTitle: colTitle.value,
          collectionEar: colEar.value,
          collectionText: colText.value,
          collectionMaterial: colMaterial.value,
          collectionStatus: selected_colStatus.value
        }),
      })
        .then(resp => resp.json())
        .then(body => {
          errMsg.textContent = '';
          const { successful, message } = body;
          if (successful) {
            location = './backCollection.html';
          } else {
            errMsg.textContent = message;
          }
        });
    })
})();