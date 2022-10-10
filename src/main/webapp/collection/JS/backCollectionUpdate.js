(() => {
  //抓sessionStorage的資料
  const form_data = JSON.parse(sessionStorage.getItem("form_data"));
  const collectionID = form_data.collectionID;
  const collectionMaterial = form_data.collectionMaterial;
  
  //  上傳圖片
  var preview_img = function (file) {
    var reader = new FileReader(); // 用來讀取檔案
    reader.readAsDataURL(file); // 讀取檔案
    reader.addEventListener("load", function () {
      let img_src =
        '<img src="' + reader.result + '" class="preview_img">';
      document.querySelector(".preview").innerHTML = img_src
    });
  };

  let picture_list = document.getElementsByClassName("pic")[0];
  fetch("http://localhost:8080/TGA103eagleMuseum/ImgGetOneById", {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      collectionID,
    }),
  })
    .then(resp => resp.json())
    .then(collectionImgNameID => {
      console.log(collectionImgNameID,"2222");
      for (i = 0; i < collectionImgNameID.length; i++) {
        let div_html = `
                     <span class="smallpreview" >
                       <img src="${collectionImgNameID[i].collectionimgStr}" class="small"/>
                     </span>
                 `;
        picture_list.insertAdjacentHTML("beforeend", div_html); // 加進節點
      }
      let img_src =
        '<img src="' + collectionImgNameID[0].collectionimgStr + '" class="preview_img">';
      document.querySelector(".preview").innerHTML = img_src
    })




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
    let collectionimgStr = img.result;
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

    // 上傳圖片
    fetch('http://localhost:8080/TGA103eagleMuseum/imgAdd', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        collectionID,
        collectionimgStr
      })
    })
      .then(resp => resp.json())
      .then(Addimg => {
      })
  });

  // 縮圖上傳
  var img = "";
  var the_file_element = document.querySelector("#upload")
  the_file_element.addEventListener("change", function (e) {
    var num = e.target.files.length
    console.log($(".pic").find(".small").length)
    let picture_list = document.getElementsByClassName("pic")[0];
    // console.log(this.files.length);
    let that = this;
    if (num > 4 || $(".pic").find(".small").length >= 4) {
      alert("最多上傳4張")
    } else {
      for (let i = 0; i < this.files.length; i++) {
        preview_img(this.files[0]);
        let reader = new FileReader(); // 用來讀取檔案
        reader.readAsDataURL(this.files[i]); // 讀取檔案
        // console.log(this.files.length);
        reader.addEventListener("load", function (e) {
          // console.log("load 事件");
          // console.log(e);
          let div_html = `
                       <span class="smallpreview" >
                         <img src="${reader.result}" class="small"/>
                       </span>
                   `;
          picture_list.insertAdjacentHTML("beforeend", div_html); // 加進節點
          let form_data = new FormData();
          form_data.append("the_file", that.files[i]);
          img = reader;
          // console.log(reader.result)
        })
      };
    }
  })

  // 縮圖變預覽圖
  $(document).on("click", ".small", function () {
    $(".preview_img").attr("src", $(this).attr("src"))
  })

  //click 清除所有縮圖和預覽圖
  $(document).on("click", ".clear", function () {
    $(".pic").find(".smallpreview").remove();
    $(".preview").find(".preview_img").remove();
  })
})();