
      //取得訂單資訊
      fetch('/TGA103eagleMuseum/OrderGetAll')
      .then(resp => resp.json())
      .then(Data => {
        console.log(Data);
        // console.log(Data.result["length"]);

        for(let i = 0; i < Data.result["length"]; i++){
          // console.log(i);
          let status = ""
          if(Data.result[i].orderStatus === 1){
            status = "未出貨";
          }else if(Data.result[i].orderStatus === 2){
            status = "處理中";
          }else if(Data.result[i].orderStatus === 3){
            status = "已出貨";
          }else {
            status = "已到貨";
          }


          let text = `
              <div class="container-fluid add">
                <div class="row d-flex  column">
                  <div class="col-1 " style="text-align:center">${Data.result[i].memberId}</div>
                  <div class="col-2" style="text-align:center">${Data.result[i].orderID}</div>
                  <div class="col-2" style="text-align:center">${Data.result[i].orderAmount}</div>
                  <div class="col-2 status" style="text-align:center"  data-status= ${Data.result[i].orderStatus}>${status}</div>
                  <div class="col-2"  style="text-align:center">${Data.result[i].createTime}</div>
                  <div class="col-2"  style="text-align:center"> 
                    <span class="icon" data-ID = ${Data.result[i].orderID}>
                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                        width="25" height="25" class="fixed" data-ID = ${Data.result[i].orderID}>
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
      })

      //取得出貨狀態
      fetch('/TGA103eagleMuseum/OrderTagGetAll')
      .then(resp => resp.json())
      .then(Data => {
        console.log(Data);


        for(let i = 0; i < Data.result["length"]; i++){
          let text = `
          <option value="${Data.result[i].orderStatusID}">${Data.result[i].orderStatus}</option>
          `
          $(".orderTag").append(text);
        }
      
      })

      //選擇出貨狀態搜尋
      $(document).on("change",".orderTag",function(){
        let orderStatus = document.querySelector(".orderTag").value;
        if( orderStatus != "請選擇"){
          fetch('/TGA103eagleMuseum/OrderGetByStat', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
              orderStatus 
            })
          })
          .then(resp => resp.json())
          .then(Data => {
            console.log(Data);
            $(".add").remove();
            for(let i = 0; i < Data.result["length"]; i++){
              // console.log(i);
              let status = ""
              if(Data.result[i].orderStatus === 1){
                status = "未出貨";
              }else if(Data.result[i].orderStatus === 2){
                status = "處理中";
              }else if(Data.result[i].orderStatus === 3){
                status = "已出貨";
              }else {
                status = "已到貨";
              }
    
    
              let text = `
                  <div class="container-fluid add">
                  <div class="row d-flex  column">
                    <div class="col-1 " style="text-align:center">${Data.result[i].memberId}</div>
                    <div class="col-2" style="text-align:center">${Data.result[i].orderID}</div>
                    <div class="col-2" style="text-align:center">${Data.result[i].orderAmount}</div>
                    <div class="col-2 status" style="text-align:center"  data-status= ${Data.result[i].orderStatus}>${status}</div>
                    <div class="col-2"  style="text-align:center">${Data.result[i].createTime}</div>
                    <div class="col-2"  style="text-align:center"> 
                      <span class="icon" data-ID = ${Data.result[i].orderID}>
                      <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                          width="25" height="25" class="fixed" data-ID = ${Data.result[i].orderID}>
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
        })
        }

    })

    //根據訂單編號取得明細
    $(document).on("click",".icon",function(){
      let orderID = $(this).attr("data-id")
      let orderStatus = $(this).closest(".add").find(".status").attr("data-status")
      sessionStorage.setItem("orderID", orderID)
      sessionStorage.setItem("orderStatus", orderStatus)
      location.href="backordercheck.html"
    })


    //搜尋會員編號的訂單
    $(document).on("click",".searchBtn",function(){
      let memberId = document.querySelector(".searchbar").value
      console.log(memberId)
      if(typeof(memberId) != "undefined"){
        fetch('/TGA103eagleMuseum/OrderGetByMem', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
              memberId 
            })
          })
          .then(resp => resp.json())
          .then(Data => {
            console.log(Data);
            $(".add").remove();
            for(let i = 0; i < Data.result["length"]; i++){
              // console.log(i);
              let status = ""
              if(Data.result[i].orderStatus === 1){
                status = "未出貨";
              }else if(Data.result[i].orderStatus === 2){
                status = "處理中";
              }else if(Data.result[i].orderStatus === 3){
                status = "已出貨";
              }else {
                status = "已到貨";
              }
    
    
              let text = `
                  <div class="container-fluid add">
                  <div class="row d-flex  column">
                    <div class="col-1 " style="text-align:center">${Data.result[i].memberId}</div>
                    <div class="col-2" style="text-align:center">${Data.result[i].orderID}</div>
                    <div class="col-2" style="text-align:center">${Data.result[i].orderAmount}</div>
                    <div class="col-2 status" style="text-align:center"  data-status= ${Data.result[i].orderStatus}>${status}</div>
                    <div class="col-2"  style="text-align:center">${Data.result[i].createTime}</div>
                    <div class="col-2"  style="text-align:center"> 
                      <span class="icon" data-ID = ${Data.result[i].orderID}>
                      <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                          width="25" height="25" class="fixed" data-ID = ${Data.result[i].orderID}>
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
          })
      }
      
    })
  
    window.addEventListener("load", function() { 

      if(sessionStorage.getItem('memberName') != null){
        document.querySelector('#memberName').textContent = 
        sessionStorage.getItem('memberName') + "，您好！";
      }
    });
    
    $(document).on("click", ".headerBtn", function (){
      let val  = $(this).val()
      $(".head").css("display","none")
      $(".head").eq(val).slideToggle("slow");
      $(".head").eq(val).find(".searchbar").val("")
      
    })
  