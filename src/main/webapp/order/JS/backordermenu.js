      // 開啟彈跳視窗
      $(".insertTag").on("click", function(){
        $(".overlayTag").fadeIn();
      });

      $(".insertProd").on("click", function(){
        $(".overlayProd").fadeIn();
      });
      

      // 關閉 標籤
      $(".btnClose, div.overlayTag, .tagSubmit").on("click", function(e){
        $("div.overlayTag").fadeOut();
      });

      $(".btnClose, div.overlayProd , .prodSubmit").on("click", function(e){
        $("div.overlayProd").fadeOut();
      });


      $("div.overlayTag > article").on("click", function(e){
        e.stopPropagation();
      });

      $("div.overlayProd > article").on("click", function(e){
        e.stopPropagation();
      });

      $(document).on("click", ".headerBtn", function (){
        let val  = $(this).val()
        // console.log($(this).val())
        $(".head").css("display","none")
        $(".head").eq(val).slideToggle("slow");
        $(".head").eq(val).find(".searchbar").val("")
        
      })
      

      //取得訂單資訊
      fetch('http://localhost:8080/TGA103eagleMuseum/OrderGetAll')
      .then(resp => resp.json())
      .then(Data => {
        console.log(Data);
        console.log(Data.result["length"]);

        for(let i = 0; i < Data.result["length"]; i++){
          console.log(i);
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
              <div class="col-2" style="text-align:center" class="statis" data-status= ${Data.result[i].orderStatus}>${status}</div>
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
                </span>     
              </div>
              <div class="col-1"></div>
            </div>
          </div>  `;

          $(".searchContent").append(text);
        }
      })

      //取得根據編號取得明細
        
                
