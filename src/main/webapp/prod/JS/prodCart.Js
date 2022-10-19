 
 

 //進入頁面立刻取得Session購物資訊
 fetch('/TGA103eagleMuseum/CartGet')
 .then(resp => resp.json())
 .then(Data => {
   console.log(Data)

   for(let i = 0 ; i < Data.result.length; i++){

     text = `
     <div class="row" style="margin-top: 5px">
      <div class="col-1"></div>
      <div class="col-10 pr">
        <div style="height: 150px; background-color: rgba(215, 214, 214, 0.39);" class="row">
          <div class="col-2" class="img" style="height: 100%; padding: 0px;">
            <img src=${Data.result[i].prodImg} style="width: 100%;">
          </div>
          <div class="col-2 d-flex"  style="align-items: center; justify-content: center;">
            <div style="font-size: 20px;">${Data.result[i].prodName}</div>
          </div>
          <div class="col-2 d-flex" style="align-items: center;justify-content: center;">
            <input  class="price" readonly="readonly" value =${Data.result[i].prodPrice} style="font-size: 16px; height: 40px; border: 0px solid; width: 50px; background-color:transparent;">
          </div>
          <div class="col-2 d-flex" style="align-items: center;justify-content: center;">
            <div class="d-flex" align-items: center;>
              <input type="number" min="1" class="count" value=${Data.result[i].prodCount} style="font-size: 16px; height: 40px; border: 1px solid rgba(199, 196, 196, 0.982);; width: 50px; background-color: rgba(255, 251, 251, 0.33);">
            </div>
          </div>
          <div class="col-2 d-flex" style="align-items: center;justify-content: center;">
            <input class="total" readonly="readonly" value=${Data.result[i].prodCount * Data.result[i].prodPrice} style="font-size: 16px; height: 40px; border: 0px solid; width: 50px; background-color:transparent;">
          </div>

          <div class="col-1 d-flex" style="align-items: center;justify-content: center;">
            <div class = "d-flex" style="align-items: center;justify-content: center;">
              <button class="delBtn" type="button" style="background-color:rgba(236, 228, 228, 0.426) ;" data-id= ${Data.result[i].productID}> 刪除商品</button>
            </div>
          </div>

        </div>
      </div>
      <div class="col-1"></div>
    </div>
</div>
     `
      $(".prod").append(text);
   }
   totalprice()
  })

  


    // console.log(price)
   //單品總數改變時改變總價
   $(document).on("blur",".count",function(){
    let prodCount = $(this).closest(".col-10").find(".count").val();
    let price = $(this).closest(".col-10").find(".price").val();
    let productID = $(this).closest(".col-10").find(".delBtn").attr("data-id");
    $(this).closest(".col-10").find(".total").attr("value",price*prodCount)


    
    //更新session購物車數量
    fetch('/TGA103eagleMuseum/CartUpdate', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                  productID,
                  prodCount
                })
              })
              .then(resp => resp.json())
              .then(Data => {
                // console.log(Data)
                $(this).closest(".col-10").find(".total").attr("value",price*prodCount)
                totalprice()
              })
   })



   //點擊刪除按鈕刪除購物車欄位和session del
     $(document).on("click",".delBtn",function(){
      // $(this).closest(".row").remove();
      let productID = $(this).attr("data-id");

      //傳去session刪除
      fetch('/TGA103eagleMuseum/CartDel', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                  productID 
                })
              })
              .then(resp => resp.json())
              .then(Data => {
                if (Data.message === "Success"){
                  $(this).closest(".row").remove();
                  totalprice()
                }
              })

     }) 
     //點擊結帳轉跳
     document.querySelector(".buy").addEventListener("click",function(){
      // console.log($(".price").length)
      // console.log($(".totalPrice").attr("value"))

      
      if($(".price").length === 0  ){
        alert("請新增商品至購物車")
      }else{
        location.href = "../order/OrderCheckOut.html";
      }
      
     })

     //點擊圖片跳轉到商品頁面
     $(document).click(".card",function(){
      let prodID = $(this).attr("id")
      console.log(prodID)
      sessionStorage.setItem("prodID", prodID)
      
     })



    //計算總金額用方法
    function  totalprice(){
      let totalprice = 0
      for(let i=0; i< $(".total").length;i++){
        totalprice = Number(totalprice) + Number($(".pr").find(".total")[i].value);
        }
        $(".totalPrice").attr("value",totalprice)
    }

    
