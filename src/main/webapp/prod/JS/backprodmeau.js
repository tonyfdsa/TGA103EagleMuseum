      var prodName= document.querySelector(".prodName").value;
      var prodTypeID= document.querySelector(".prodTypeID").value;
      var prodPrice= document.querySelector(".prodPrice").value;
      var prodDescription= document.querySelector(".prodDescription").value;
      var prodInStock = document.querySelector(".prodInStock").value;
      // 開啟彈跳視窗
      $(".insertTag").on("click", function(){
        $(".overlayTag").fadeIn();
      });

      $(".insertProd").on("click", function(){
        $(".overlayProd").fadeIn();
      });

      // 關閉 標籤
      $(".btnClose, div.overlayTag").on("click", function(e){
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

      //新增商品
      $(".prodSubmit").click(function(){
        let prodStatusURL = 'http://localhost:8080/TGA103eagleMuseum/ProdInsert'
        console.log(prodName);
        console.log( prodTypeID);
        console.log(prodPrice);
        console.log(prodDescription);
        console.log(prodInStock);

        fetch(prodStatusURL, {
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({
            prodName,
            prodTypeID,
            prodPrice,
            prodDescription,
            prodInStock
          })
        })
        .then(resp => resp.json())
        .then(R => {  
          console.log(R)
        })  
      })

      //新增標籤
      $(".tagSubmit").click(function(){
        let TagInsertURL = 'http://localhost:8080/TGA103eagleMuseum/ProdTagInsert'
        let prodType = document.querySelector(".ProdType").value;
        console.log(prodType);

        fetch(TagInsertURL, {
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({
            prodType
          })
        })
        .then(resp => resp.json())
        .then(R => {  
          console.log(R)
        })  
      })
   
      $(document).on("click", ".headerBtn", function (){
        let val  = $(this).val()
        // console.log($(this).val())
        console.log($(".searchbar").length)
        
       

        $(".head").css("display","none")
        $(".head").eq(val).slideToggle("slow");
        $(".head").eq(val).find(".searchbar").val("")
        
      })

      $(document).on("click", ".icon", function (){
        // console.log("as")
        // console.log($("div.status"))
        let val = $(this).attr("data-value")
        if(val == 0){
            $(this).closest(".col-2").prevAll('.col-2').eq(1).text("下架")
            $(this).closest(".col-2").prevAll('.col-2').eq(1).attr("data-status","0")
        }else if(val == 1){
            $(this).closest(".col-2").prevAll('.col-2').eq(1).text("上架")
            $(this).closest(".col-2").prevAll('.col-2').eq(1).attr("data-status","1")
        }
      })


      // 接受後端訊息

    

        fetch('http://localhost:8080/TGA103eagleMuseum/ProductGetAll')
          .then(resp => resp.json())
          .then(R => {
          
            for( i = 0 ; i < R.result["length"] ; i++){
              console.log(R.result[i])
            // console.log(statis);
            let re = (R.result[i].prodStatus === 1 ? "上架" : "下架");
            let text = `
                            <div class="container-fluid add" data-ID = ${R.result[i].productID}>
                            <div class="row d-flex  column">
                            <div class="col-1 " style="text-align:center">${i+1}</div>
                            <div class="col-2 productID" style="text-align:center">${R.result[i].productID}</div>
                            <div class="col-2" style="text-align:center">${R.result[i].prodName}</div>
                            <div class="col-2 status" style="text-align:center"  data-status= ${R.result[i].prodStatus}>${re}</div>
                            <div class="col-2"  style="text-align:center">${R.result[i].creatTime}</div>
                            <div class="col-2"  style="text-align:center"> 
                              <span class="icon" >
                                  <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                                      width="25" height="25" class="fixed" data-ID = ${R.result[i].productID}>
                                      <g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                      <path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
                                      <path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
                                      <path d="M16 5l3 3"></path>
                                      </g>
                                  </svg>
                              </span>    

                              <span style="margin-left: 10px; margin-right:10px" class="icon status"  data-value="1"  data-ID = ${R.result[i].productID}>
                                  <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24" width="25" height="25" class="up" data-value = ${R.result[i].productID}>
                                      <path d="M5 18h14v2H5v-2zm4.6-2.7L5 10.7l2-1.9l2.6 2.6L17 4l2 2l-9.4 9.3z" fill="currentColor">    
                                      </path>
                                  </svg>
                              </span>

                              <span class="icon status"  data-value="0" data-ID = ${R.result[i].productID}>
                                  <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                                      width="25" height="25" class="down" data-ID = ${R.result[i].productID}>
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
          
          $(".searchBtn").click(function(){
            let prodName = document.querySelector("#prodName").value
            let prodserchname = 'http://localhost:8081/TGA103_EagleMuseum/ProductGetName'
            fetch('http://localhost:8080/TGA103eagleMuseum/ProductGetName', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                  prodName
                })
              })
              .then(resp => resp.json())
              .then(R => {

                //清除所有的 $(".searchContent").append(text);
                $(".searchContent").find(".add").remove();
                $(".searchContent").find("#searchNAN").remove();
                console.log(R);
                console.log($(".searchContent").find("#searchNAN"));
                if(R.result["length"] === 0){
                  
                  $(".searchContent").append("<div style='color: red;' id=searchNAN> 查無結果，請重新輸入 </div>");
                }

                //新增搜尋的結果
                for( i = 0 ; i < R.result["length"] ; i++){
                  console.log(R.result[i])
                  // console.log(statis);
                  
                  let re = (R.result[i].prodStatus === 1 ? "上架" : "下架");
                  let text = `
                            <div class="container-fluid add">
                            <div class="row d-flex  column">
                            <div class="col-1 " style="text-align:center">${i+1}</div>
                            <div class="col-2" style="text-align:center">${R.result[i].productID}</div>
                            <div class="col-2" style="text-align:center">${R.result[i].prodName}</div>
                            <div class="col-2" style="text-align:center" class="statis" data-status= ${R.result[i].prodStatus}>${re}</div>
                            <div class="col-2"  style="text-align:center">${R.result[i].creatTime}</div>
                            <div class="col-2"  style="text-align:center"> 
                              <span class="icon" data-ID = ${R.result[i].productID}>
                                  <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                                      width="25" height="25" class="fixed" data-ID = ${R.result[i].productID}>
                                      <g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                      <path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
                                      <path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
                                      <path d="M16 5l3 3"></path>
                                      </g>
                                  </svg>
                              </span>    

                              <span style="margin-left: 10px; margin-right:10px" class="icon status"  data-value="1"  data-ID = ${R.result[i].productID}>
                                  <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24" width="25" height="25" class="up" data-value = ${R.result[i].productID}>
                                      <path d="M5 18h14v2H5v-2zm4.6-2.7L5 10.7l2-1.9l2.6 2.6L17 4l2 2l-9.4 9.3z" fill="currentColor">    
                                      </path>
                                  </svg>
                              </span>

                              <span class="icon status"  data-value="0" data-ID = ${R.result[i].productID}>
                                  <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                                      width="25" height="25" class="down" >
                                      <path
                                      d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm0 18c-4.42 0-8-3.58-8-8c0-1.85.63-3.55 1.69-4.9L16.9 18.31A7.902 7.902 0 0 1 12 20zm6.31-3.1L7.1 5.69A7.902 7.902 0 0 1 12 4c4.42 0 8 3.58 8 8c0 1.85-.63 3.55-1.69 4.9z"
                                      fill="currentColor"></path>
                                  </svg>
                              </span>     
                            </div>
                            <div class="col-1"></div>
                          </div>
                        </div>  `;

                  console.log(R.result[i])
                  $(".searchContent").append(text);
                }
            
            }); 
          })
          
          // update
          $(document).on("click", ".status", function (){
            let productID = $(this).attr("data-ID");
            let prodStatus = $(this).attr("data-value");
            let prodStatusURL = 'http://localhost:8080/TGA103eagleMuseum/ProdStatusUpdate'
            console.log(prodStatus);
              fetch(prodStatusURL, {
                  method: 'POST',
                  headers: {'Content-Type': 'application/json'},
                  body: JSON.stringify({
                    prodStatus,
                    productID
                  })
                })
                .then(resp => resp.json())
                .then(R => {  
                })
            
          })
                
