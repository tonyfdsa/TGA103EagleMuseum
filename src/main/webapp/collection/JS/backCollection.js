(() => {
  $(document).on("click", ".headerBtn", function () {
    let val = $(this).val()
    $(".head").css("display", "none")
    $(".head").eq(val).slideToggle("slow");
  })



  // 接受後端訊息

  fetch('http://localhost:8080/TGA103eagleMuseum/collectionAll')
    .then(resp => resp.json())
    .then(findCollectuins => {
      console.log(findCollectuins)

      for (i = 0; i < findCollectuins.length; i++) {
        let re = (findCollectuins[i].collectionStatus === true ? "上架中" : "下架");
        let text = `
						<div class="container-fluid add" data-ID = ${findCollectuins[i].collectionID}>
							<div class="row d-flex  column">
								<div class="col-1" style="text-align:center">${i + 1}</div>
								<div class="col-1" style="text-align:center">${findCollectuins[i].collectionID}</div>
								<div class="col-2" style="">${findCollectuins[i].collectionTitle}</div>
								<div class="col-3" style="">${findCollectuins[i].collectionText}</div>
								<div class="col-1" style="text-align:center">${findCollectuins[i].collectionEar}</div>
								<div class="col-2" style="text-align:center">${findCollectuins[i].collectionMaterial}</div>
								<div class="col-1" style="text-align:center" data-stats= ${findCollectuins[i].collectionStatus}>${re}</div>
								<div class="col-1" style="text-align:center"> 
								<span class="icon">
									<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
										width="25" height="25" class="fixed" data-ID = ${findCollectuins[i].collectionID}  data-TAG = ${findCollectuins[i].collectionMaterial}>
										<g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
										<path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
										<path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
										<path d="M16 5l3 3"></path>
										</g>
									</svg>
									<span style="margin-left: 10px; margin-right:10px" class="icon" data-value="1">
										<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24" width="25" height="25">
											<path d="M5 18h14v2H5v-2zm4.6-2.7L5 10.7l2-1.9l2.6 2.6L17 4l2 2l-9.4 9.3z" fill="currentColor">    
											</path>
										</svg>
									</span>
								</span>
								</div><div class="col-1"></div>
							</div>
						</div>  `;
        $(".searchContent").append(text);
      }
    });

  // 抓商品標籤
  fetch('http://localhost:8080/TGA103eagleMuseum/tagAll')
    .then(resp => resp.json())
    .then(findAllTags => {
      for (i = 0; i < findAllTags.length; i++) {
        let text = `
              			<option value="${findAllTags[i].tag}">${findAllTags[i].tag}</option>
              			`
        $(".selectMaterial").append(text);
      }
    });


  $(document).on("click", ".fixed", function () {
    var send_data = {};
    send_data.collectionID = $(this).attr("data-id");
    send_data.collectionMaterial = $(this).attr("data-tag");
    sessionStorage.setItem("form_data", JSON.stringify(send_data));
    location.href = "./backCollectionUpdate.html";
  })





  // 抓商品搜尋的資料回傳  
  $(".searchBtn").click(function () {
    let collectionTitle = document.querySelector("#collectionTitle").value
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
          console.log(collectionSerchName[i])
          let re = (collectionSerchName[i].collectionStatus === true ? "上架中" : "下架");
          let text = `
                            <div class="container-fluid add" data-ID = ${collectionSerchName[i].collectionID}>
                            <div class="row d-flex  column">
                            <div class="col-1 "style="text-align:center">${i + 1}</div>
                            <div class="col-1" style="text-align:center">${collectionSerchName[i].collectionID}</div>
                            <div class="col-2" style="">${collectionSerchName[i].collectionTitle}</div>
							<div class="col-3" style="">${collectionSerchName[i].collectionText}</div>
							<div class="col-1" style="text-align:center">${collectionSerchName[i].collectionEar}</div>
          					<div class="col-2" style="text-align:center">${collectionSerchName[i].collectionMaterial}</div>
                            <div class="col-1" style="text-align:center" class="statis" data-status= ${collectionSerchName[i].prodStatus}>${re}</div>
                            <div class="col-1"  style="text-align:center"> 
								<span class="icon" data-ID = ${collectionSerchName[i].collectionID}>
							<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
								width="25" height="25" class="fixed" data-ID = ${collectionSerchName[i].collectionID}  data-TAG = ${collectionSerchName[i].collectionMaterial}>
								<g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
								<path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
								<path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
								<path d="M16 5l3 3"></path>
								</g>
							</svg>
							<span style="margin-left: 10px; margin-right:10px" class="icon" data-value="1">
								<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24" width="25" height="25">
									<path d="M5 18h14v2H5v-2zm4.6-2.7L5 10.7l2-1.9l2.6 2.6L17 4l2 2l-9.4 9.3z" fill="currentColor">    
									</path>
								</svg>
							</span>
           				</span>

                            </div>
                            <div class="col-1"></div>
                          </div>
                        </div>  `;
          $(".searchContent").append(text);
        }

      });
  })
})();