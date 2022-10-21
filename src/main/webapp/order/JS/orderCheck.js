        //取得訂單資料
        let orderStatus = 1;
        fetch('/TGA103eagleMuseum/OrderGetByStat',{
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
             orderStatus 
            })
          })
          .then(resp => resp.json())
          .then(Data => {
            console.log(Data)
            for(let i = 0 ; i < Data.result["length"]; i++){
                console.log("123")
                text=`
            <div class="column d-flex row" style="margin-top:10px;margin-bottom: 20px; align-items: center ; text-align: center;">
                <div class="col-2 createTime">${Data.result[i].createTime}</div>
                <div class="col-3 orderID" >訂單編號: &nbsp ${Data.result[i].orderID}</div>
                <div class="col-3 deliveryAddress">運送地址: &nbsp ${Data.result[i].deliveryAddress}</div>
                <div class="col-2 orderAmount"> Price: &nbsp ${Data.result[i].orderAmount}NTD</div>
                <div class="col-2" style="text-align: end;">
                    <button style="background-color: transparent;" class="more" order-ID=${Data.result[i].orderID}>more</button>
                </div>
            </div>
                `

                $(".SR").append(text);

            }

        })

        $(".pageBtn").click(function(){
           let orderStatus = $(this).attr("data-vlaue")
            console.log(orderStatus)
            //每當點擊清除id="show"
            var contentAll = document.querySelectorAll(".SR");
            let tabClick = document.querySelectorAll(".pageBtn")
            for (let i = 0; i < tabClick.length; i++){
                tabClick[i].addEventListener("click",function(){
                    var contentAll = document.querySelectorAll(".SR");
                    for (let j = 0; j <  contentAll.length; j++){
                        $(".pageBtn").removeClass("show");
                     }
                //用點擊設置內容，利用id
                tabClick[i].setAttribute("class","col-auto pageBtn show")
                })
             }

             fetch('/TGA103eagleMuseum/OrderGetByStat',{
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                 orderStatus 
                })
              })
              .then(resp => resp.json())
              .then(Data => {
                console.log(Data)
                $(".column").remove();
                if(Data.code === 200){
                    if(Data.result["length"] === 0){
                        text=`
                        <div class="column d-flex row" style="margin-top:10px;margin-bottom: 20px; align-items: center ; text-align: center;">
                           <div style="color:red">此項狀態無訂單</div>
                        </div>
                            `
            
                            $(".SR").append(text);

                    }else{
                        for(let i = 0 ; i < Data.result["length"]; i++){
                            console.log("123")
                            text=`
                        <div class="column d-flex row" style="margin-top:10px;margin-bottom: 20px; align-items: center ; text-align: center;">
                            <div class="col-2 createTime">${Data.result[i].createTime}</div>
                            <div class="col-3 orderID" >訂單編號: &nbsp ${Data.result[i].orderID}</div>
                            <div class="col-3 deliveryAddress">運送地址: &nbsp ${Data.result[i].deliveryAddress}</div>
                            <div class="col-2 orderAmount"> Price: &nbsp ${Data.result[i].orderAmount}NTD</div>
                            <div class="col-2" style="text-align: end;">
                                <button style="background-color: transparent;" class="more" order-ID=${Data.result[i].orderID}>more</button>
                            </div>
                        </div>
                            `
            
                            $(".SR").append(text);
                    }

                }
    
                }
            })
        })

        //點擊more查看明細
        $(document).on("click",".more",function(){
           let orderID = $(this).attr('order-id')
           fetch("/TGA103eagleMuseum/OrderDetailGetByID",{
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          orderID
        })
      })
      .then(resp => resp.json())
      .then(Data => {
        console.log(Data)

        $(".column").remove();
        if(Data.code === 200){
            if(Data.result["length"] === 0){
                text=`
                <div class="column d-flex row" style="margin-top:10px;margin-bottom: 20px; align-items: center ; text-align: center;">
                   <div style="color:red">此項狀態無訂單</div>
                </div>
                    `
    
                    $(".SR").append(text);

            }else{
                for(let i = 0 ; i < Data.result["length"]; i++){

                    text=`
                <div class="column d-flex row" style="margin-top:10px;margin-bottom: 20px; align-items: center ; text-align: center;">
                    <div class="col-2 ">商品編號${Data.result[i].productID}</div>
                    <div class="col-3 ">商品名稱: &nbsp ${Data.result[i].prodName}</div>
                    <div class="col-3 " >商品數量: &nbsp ${Data.result[i].prodQuantity}</div>
                    <div class="col-2"> 商品單價: &nbsp ${Data.result[i].prodPrice}NTD</div>
                    <div class="col-2 "> 明細總金額: &nbsp ${Data.result[i].prodPrice * Data.result[i].prodQuantity}NTD</div>
                </div>
                    `
    
                    $(".SR").append(text);
            }

        }

        }
        })
     })