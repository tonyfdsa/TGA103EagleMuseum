(() => {
  //抓sessionStorage的資料

  var form_data = JSON.parse(sessionStorage.getItem("form_data"));
  var collectionID = form_data.collectionID
  var collectionMaterial = form_data.collectionMaterial
  // 把ID往下帶
  fetch("http://localhost:8080/TGA103eagleMuseum/collectionGetOne", {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      collectionID: collectionID,
      collectionMaterial: collectionMaterial,
    }),
  })
    .then(resp => resp.json())
    .then(collection => {
      document.querySelector('.colTitle').value = collection.collectionTitle;

      let text2 = `
      <option value="${collection.collectionMaterial}" selected disabled hidden>${collection.collectionMaterial}</option>
      `
      $(".selectMaterial").append(text2);
      
      document.querySelector('.colEar').value = collection.collectionEar;
      document.querySelector('.colText').value = collection.collectionText;
      document.querySelector('.collectStatus').value = collection.collectionStatus;
      if (document.querySelector('.collectStatus').value == "true") {
        document.getElementById("male-input").checked = true;
      } else if (document.querySelector('.collectStatus').value == "false") {
        document.getElementById("female-input").checked = true;
      }
    })

  //submit insert
  $(document).on("click", ".submit", function () {
    let collectionTitle = document.querySelector(".colTitle").value;
    let collectionMaterial = document.querySelector(".selectMaterial").value;
    let collectionEar = document.querySelector(".colEar").value;
    let collectionText = document.querySelector(".colText").value;
    let collectionStatus = document.getElementsByName("colStatus");

    let errMsg = document.querySelector('#errMsg');

    if (collectionTitle == "") {
      errMsg.textContent = "名稱不可為空"
      return;
    }
    if (collectionText == "") {
      errMsg.textContent = "說明不可為空"
      return;
    }
    if (collectionEar == "") {
      errMsg.textContent = "朝代不可為空"
      return;
    }

    let selected_colStatus;
    if (collectionStatus[0].checked == true) {
      selected_colStatus = false;
    } else if (collectionStatus[1].checked == true) {
      selected_colStatus = true;
    }

    fetch('http://localhost:8080/TGA103eagleMuseum/collectionUpdate', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        collectionTitle,
        collectionEar,
        collectionText,
        collectionStatus: selected_colStatus,
        collectionMaterial,
        collectionID
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
  });
})();