(() => {
  $(document).on("click", ".headerBtn", function () {
    let val = $(this).val()
    $(".head").css("display", "none")
    $(".head").eq(val).slideToggle("slow");
  })

  // // 抓商品標籤
  // fetch('http://localhost:8080/TGA103eagleMuseum/tagAll')
  //   .then(resp => resp.json())
  //   .then(findAllTags => {
  //     for (i = 0; i < findAllTags.length; i++) {
  //       let text = `
  //             			<option value="${findAllTags[i].tag}">${findAllTags[i].tag}</option>
  //             			`
  //       $(".selectMaterial").append(text);
  //     }
  //   });

  // searchTitle 商品搜尋的資料回傳  
  $("#searchBtnTitle").click(function () {
    let collectionTitle = document.querySelector("#collectionTitle").value
    if (collectionTitle != "") {
      fetch('http://localhost:8080/TGA103eagleMuseum/collectionGetOneName', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          collectionTitle
        })
      })
        .then(resp => resp.json())
        .then(collectionSerchName => {
          //清除所有的 $(".searchContent").append(text);
          $(".searchContent").find(".add").remove();
          $(".searchContent").find("#searchNAN").remove();
          if (collectionSerchName.length === 0) {
            $(".searchContent").append("<div style='color: red;' id=searchNAN> 查無結果，請重新輸入 </div>");
          }
          //新增搜尋的結果
          for (i = 0; i < collectionSerchName.length; i++) {
            let text = `
          <div class="container-fluid add" data-ID = ${collectionSerchName[i].collectionID} data-TAG = ${collectionSerchName[i].collectionMaterial}>
            <div class="row d-flex  column">
              <div class="col-1 "style="text-align:center">${i + 1}</div>
              <div class="col-1" style="text-align:center">${collectionSerchName[i].collectionID}</div>
              <div class="col-2" style="">${collectionSerchName[i].collectionTitle}</div>
              <div class="col-3" style="">${collectionSerchName[i].collectionText}</div>
              <div class="col-1" style="text-align:center">${collectionSerchName[i].collectionEar}</div>
              <div class="col-2" style="text-align:center">${collectionSerchName[i].collectionMaterial}</div>
              <div class="col-2"  style="text-align:center"></div>
              <div class="col-1"></div>
            </div>
          </div>
        `;
            $(".searchContent").append(text);
          }
        });
    } else {
      $(".searchContent").find(".add").remove();
      $(".searchContent").find("#searchNAN").remove();
      $(".searchContent").append("<div style='color: red;' id=searchNAN> 請輸入查詢文字 </div>");
    }
  })

  // searchEar 商品搜尋的資料回傳  
  $("#searchBtnEar").click(function () {
    let collectionEar = document.querySelector("#collectionEar").value
    if (collectionEar != "") {
      fetch('http://localhost:8080/TGA103eagleMuseum/collectionGetOneEar', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        collectionEar
      })
    })
      .then(resp => resp.json())
      .then(collectionSerchEar => {
        //清除所有的 $(".searchContent").append(text);
        $(".searchContent").find(".add").remove();
        $(".searchContent").find("#searchNAN").remove();
        if (collectionSerchEar.length === 0) {
          $(".searchContent").append("<div style='color: red;' id=searchNAN> 查無結果，請重新輸入 </div>");
        }
        //新增搜尋的結果
        for (i = 0; i < collectionSerchEar.length; i++) {
          let re = (collectionSerchEar[i].collectionStatus === true ? "上架中" : "下架");
          let text = `
          <div class="container-fluid add" data-ID = ${collectionSerchEar[i].collectionID} >
            <div class="row d-flex  column">
              <div class="col-1 "style="text-align:center">${i + 1}</div>
              <div class="col-1" style="text-align:center">${collectionSerchEar[i].collectionID}</div>
              <div class="col-2" style="">${collectionSerchEar[i].collectionTitle}</div>
              <div class="col-3" style="">${collectionSerchEar[i].collectionText}</div>
              <div class="col-1" style="text-align:center">${collectionSerchEar[i].collectionEar}</div>
              <div class="col-2" style="text-align:center">${collectionSerchEar[i].collectionMaterial}</div>
              <div class="col-1" style="text-align:center" class="statis" data-status= ${collectionSerchEar[i].prodStatus}>${re}</div>
              <div class="col-1"  style="text-align:center"></div>
              <div class="col-1"></div>
            </div>
          </div>
        `;
          $(".searchContent").append(text);
        }
      });
    } else {
      $(".searchContent").find(".add").remove();
      $(".searchContent").find("#searchNAN").remove();
      $(".searchContent").append("<div style='color: red;' id=searchNAN> 請輸入查詢文字 </div>");
    }
  })
  

  // sessionStorage 
  $(document).on("click", ".add", function () {
    var send_data = {};
    send_data.collectionID = $(this).attr("data-id");
    send_data.collectionMaterial = $(this).attr("data-tag");
    // send_data.collectionImg = $(this).attr("data-tag");

    sessionStorage.setItem("form_data", JSON.stringify(send_data));
    location.href = "./collectionDetail.html";
  })
})();