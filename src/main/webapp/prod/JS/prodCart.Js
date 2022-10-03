 

 //進入頁面立刻取得Session購物資訊
 fetch('http://localhost:8080/TGA103eagleMuseum/CartGet')
 .then(resp => resp.json())
 .then(Data => {
   console.log(Data)

   for(let i = 0 ; i < Data.result.length; i++){

     text = `
     <div class="row" style="margin-top: 5px">
     <div class="col-1"></div>
     <div class="col-10 ">
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
      $(".container-fluid").append(text);
   }
  })

   //單品總數改變時改變總價
   $(document).on("blur",".count",function(){
    let count = $(this).closest(".col-10").find(".count").val();
    let price = $(this).closest(".col-10").find(".price").val();
    $(this).closest(".col-10").find(".total").attr("value",price*count)
   })



   //點擊刪除按鈕刪除購物車欄位和session del
     $(document).on("click",".delBtn",function(){
      console.log("DEL");
      $(this).closest(".row").remove();

      let productID = $(this).attr("data-id");
      console.log(productID)
      //傳去session刪除
      fetch('http://localhost:8080/TGA103eagleMuseum/CartDel', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                  productID 
                })
              })
              .then(resp => resp.json())
              .then(R => {
              })

     })     
