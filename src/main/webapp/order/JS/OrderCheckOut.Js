fetch('/TGA103eagleMuseum/CartGet')
  .then(resp => resp.json())
  .then(Data => {

    for(let i = 0 ; i < Data.result.length; i++){

      text = `
      <div class="row" style="margin-top: 5px">
       <div class="col-1"></div>
       <div class="col-10 pr">
         <div style="height: 100px; background-color: rgba(215, 214, 214, 0.39);" class="row">
           <div class="col-1" class="img" style="height: 100%; padding: 0px;">
             <img src=${Data.result[i].prodImg} style="width: 100%;">
           </div>
           <div class="col-2 d-flex"  style="align-items: center; justify-content: center;">
             <div style="font-size: 20px;">${Data.result[i].prodName}</div>
           </div>
           <div class="col-2 d-flex" style="align-items: center;justify-content: center;">
             <input  class="price" readonly="readonly" value =${Data.result[i].prodPrice} style="font-size: 16px; height: 40px; border: 0px solid; width: 50px; background-color:transparent;">
           </div>
           <div class="col-2 d-flex" style="align-items: center;justify-content: center;">
             <div class="col-2 d-flex"  style="align-items: center; justify-content: center;>
               <div style="font-size: 20px;">${Data.result[i].prodCount} </div>
             </div>
             <div class="col-2 d-flex" style="align-items: center;justify-content: center;">
               <input class="total" readonly="readonly" value=${Data.result[i].prodCount * Data.result[i].prodPrice} style="font-size: 16px; height: 40px; border: 0px solid; width: 50px; background-color:transparent;">
             </div>
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

       //計算總金額用方法
       function  totalprice(){
          let totalprice = 0
          let mailPrice = 60;
          for(let i=0; i< $(".total").length;i++){
            totalprice = Number(totalprice) + Number($(".pr").find(".total")[i].value);
            }
            $(".totalPrice").attr("value",(totalprice +  mailPrice))
        }

        //點擊按鈕結帳
        $(".buy").click(function(){
          let orderAmount = document.querySelector(".totalPrice").value;
          let deliveryAddress = document.querySelector(".deliveryAddress").value;
          let URL = '/TGA103eagleMuseum/OrderInsert';
          $("#NAN").remove();
          if (deliveryAddress == ""){
            text="<div style='color: red;' id=NAN> 請輸入地址 </div>"
            $(".deliveryAddress").closest(".col-8").append( text);
          }else{
          //成立訂單
          fetch(URL, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
              orderAmount,
              deliveryAddress
              })
            })
            .then(resp => resp.json())
            .then(Data => {  
              console.log(Data);
              if(Data.code == 400){
                $(".mes").html(Data.message);
              }
             
              if(Data.code === 200){
                alert("訂單已成立")
                //清除購物車內容
                fetch("/CartDelAll")
                .then(resp => resp.json)
                .then(Data =>{
                  console.log(Data)
                  location.href = "../prod/productmenu.html"
                })
                
              }


            })
          }
        })
          //取得上架商品的資料
          fetch('/TGA103eagleMuseum/ProdGetListed')
          .then(resp => resp.json())
          .then(Data => {
            console.log(Data)
            for (let i = 0 ; i< Data.result.length; i++){
              let text=
              `
              <div class="card " id="${Data.result[i].productID}" style="padding: 0%;margin: 0.5% 0.5%; width: 16rem;" >

                  <div class="card-body" >
                    <h5 class="prodName" style="align-items:center">${Data.result[i].prodName}
                      
                    </h5>
                    <p class="prodtext">商品說明</p>
                    <span class="prodPrice"><span>NT$</span>${Data.result[i].prodPrice}</span>
    
                    <span  style="float: right;"  >
                      <svg  class="shoopingCart" data-id = ${Data.result[i].productID} xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24" width="30" height="30">
                        <path d="M11 9h2V6h3V4h-3V1h-2v3H8v2h3v3zm-4 9c-1.1 0-1.99.9-1.99 2S5.9 22 7 22s2-.9 2-2s-.9-2-2-2zm10 0c-1.1 0-1.99.9-1.99 2s.89 2 1.99 2s2-.9 2-2s-.9-2-2-2zm-8.9-5h7.45c.75 0 1.41-.41 1.75-1.03l3.86-7.01L19.41 4l-3.86 7H8.53L4.27 2H1v2h2l3.6 7.59l-1.35 2.44C4.52 15.37 5.48 17 7 17h12v-2H7l1.1-2z" fill="currentColor"></path>
                      </svg>
                    </span>

                  </div>
                </div>
              `
              $(".product").append(text)
            }
          });


        
          