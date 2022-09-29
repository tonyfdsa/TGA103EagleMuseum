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
                  <div class="col-2 productID" style="text-align:center">${R.result[i].exhibitionId}</div>
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

                    <span style="margin-left: 10px; margin-right:10px" class="icon status"  data-value="1"  data-ID = ${R.result[i].exhibitionId}>
                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24" width="25" height="25" class="up" data-value = ${R.result[i].productID}>
                            <path d="M5 18h14v2H5v-2zm4.6-2.7L5 10.7l2-1.9l2.6 2.6L17 4l2 2l-9.4 9.3z" fill="currentColor">    
                            </path>
                        </svg>
                    </span>

                    <span class="icon status"  data-value="0" data-ID = ${R.result[i].exhibitionId}>
                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                            width="25" height="25" class="down" data-ID = ${R.result[i].exhibitionId}>
                            <path
                            d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm0 18c-4.42 0-8-3.58-8-8c0-1.85.63-3.55 1.69-4.9L16.9 18.31A7.902 7.902 0 0 1 12 20zm6.31-3.1L7.1 5.69A7.902 7.902 0 0 1 12 4c4.42 0 8 3.58 8 8c0 1.85-.63 3.55-1.69 4.9z"
                            fill="currentColor"></path>
                        </svg>
                    </span>     
                  </div>
                  <div class="col-1"></div>
                </div>
              </div>  `;


            $(".searchContent").append(text);
        }
    });


// 抓商品搜尋的資料回傳
$(".searchBtn").click(function () {
    let exhibitionName = document.querySelector("#exhibitionName").value
    let exhibitionSearchName = 'http://localhost:8080/TGA103_EagleMuseum/ExhibitionGetByName'
    fetch('http://localhost:8080/TGA103eagleMuseum/ExhibitionGetByName', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            exhibitionName
        })
    })
        .then(resp => resp.json())
        .then(R => {

            $(".searchContent").find(".add").remove();
            $(".searchContent").find("#searchNAN").remove();

            if (R.result["length"] === 0) {

                $(".searchContent").append("<div style='color: red;' id=searchNAN> 查無結果，請重新輸入 </div>");
            }

            //新增搜尋的結果
            for (i = 0; i < R.result["length"]; i++) {

                let text = `
                      <div class="container-fluid add" data-ID = ${R.result[i].exhibitionId}>
                      <div class="row d-flex  column">
                      <div class="col-1 " style="text-align:center">${i + 1}</div>
                      <div class="col-2 productID" style="text-align:center">${R.result[i].exhibitionId}</div>
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
    
                        <span style="margin-left: 10px; margin-right:10px" class="icon status"  data-value="1"  data-ID = ${R.result[i].exhibitionId}>
                            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24" width="25" height="25" class="up" data-value = ${R.result[i].productID}>
                                <path d="M5 18h14v2H5v-2zm4.6-2.7L5 10.7l2-1.9l2.6 2.6L17 4l2 2l-9.4 9.3z" fill="currentColor">    
                                </path>
                            </svg>
                        </span>
    
                        <span class="icon status"  data-value="0" data-ID = ${R.result[i].exhibitionId}>
                            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                                width="25" height="25" class="down" data-ID = ${R.result[i].exhibitionId}>
                                <path
                                d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm0 18c-4.42 0-8-3.58-8-8c0-1.85.63-3.55 1.69-4.9L16.9 18.31A7.902 7.902 0 0 1 12 20zm6.31-3.1L7.1 5.69A7.902 7.902 0 0 1 12 4c4.42 0 8 3.58 8 8c0 1.85-.63 3.55-1.69 4.9z"
                                fill="currentColor"></path>
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