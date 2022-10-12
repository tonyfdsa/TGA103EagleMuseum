// 接受後端資料庫訊息

fetch('http://localhost:8080/TGA103eagleMuseum/ExhibitionGetAll')
    .then(resp => resp.json())
    .then(R => {
        // console.log(R);
        for (i = 0; i < R.result["length"]; i++) {

            let text = `
                  <div class="container-fluid add" data-ID = ${R.result[i].exhibitionId}>
                  <div class="row d-flex  column">
                  <div class="col-1 " style="text-align:center">${i + 1}</div>
                  <div class="col-2 productID" style="text-align:center">${R.result[i].exhibitionID}</div>
                  <div class="col-2" style="text-align:center">${R.result[i].exhibitionName}</div>
                  <div class="col-1 status" style="text-align:center">${R.result[i].exhibitionStartDate}</div>
                  <div class="col-1 status" style="text-align:center">${R.result[i].exhibitionEndDate}</div>
                  <div class="col-1"  style="text-align:center">${R.result[i].exhibitionType}</div>
                  <div class="col-1"  style="text-align:center">${R.result[i].exhibitionStatus}</div>
                  <div class="col-2"  style="text-align:center"> 
                    <span class="icon" >
                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                            width="25" height="25" class="fixed" data-ID = ${R.result[i].exhibitionId} class="fixed">
                            <g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
                            <path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
                            <path d="M16 5l3 3"></path>
                            </g>
                        </svg>
                    </span>     
                  </div>
                  <div class="col-1"></div>
                </div>
              </div>  `;


            $(".searchContent").append(text);
        }
    });


// 抓搜尋的資料回傳
$(".searchBtn").click(function () {
    console.log("按");

    let exhibitionName = document.querySelector("#exhibitionName").value
    let exhibitionStartDate = document.querySelector("#start_date").value
    let exhibitionEndDate = document.querySelector("#end_date").value
    fetch('http://localhost:8080/TGA103eagleMuseum/ExhibitionGet', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            exhibitionName,
            exhibitionStartDate,
            exhibitionEndDate
        })
    })
        .then(resp => resp.json())
        .then(R => {

            //清除所有的 $(".searchContent").append(text);
            $(".searchContent").find(".add").remove();
            $(".searchContent").find("#searchNAN").remove();

            if (R.result["length"] === 0) {

                $(".searchContent").append("<div style='color: red;' id=searchNAN> 查無結果，請重新輸入 </div>");
            }
            console.log(R.result);
            //新增搜尋的結果
            for (i = 0; i < R.result["length"]; i++) {

                let text = `
                      <div class="container-fluid add" data-ID = ${R.result[i].exhibitionID}>
                      <div class="row d-flex  column">
                      <div class="col-1 " style="text-align:center">${i + 1}</div>
                      <div class="col-2 exhibitionID" style="text-align:center">${R.result[i].exhibitionID}</div>
                      <div class="col-2" style="text-align:center">${R.result[i].exhibitionName}</div>
                      <div class="col-1 status" style="text-align:center">${R.result[i].exhibitionStartDate}</div>
                      <div class="col-1 status" style="text-align:center">${R.result[i].exhibitionEndDate}</div>
                      <div class="col-1"  style="text-align:center">${R.result[i].exhibitionType}</div>
                      <div class="col-1"  style="text-align:center">${R.result[i].exhibitionStatus}</div>
                      <div class="col-2"  style="text-align:center"> 
                        <span class="icon" >
                            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                                width="25" height="25" class="fixed" data-ID = ${R.result[i].exhibitionId} class="fixed">
                                <g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                <path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
                                <path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
                                <path d="M16 5l3 3"></path>
                                </g>
                            </svg>
                        </span>    
                      </div>
                      <div class="col-1"></div>
                    </div>
                  </div>  `;


                $(".searchContent").append(text);
            }

        });
})