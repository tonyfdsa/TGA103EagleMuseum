fetch('http://localhost:8080/TGA103eagleMuseum/CartGet')
.then(resp => resp.json())
.then(Data => {
  console.log(Data)

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
         </div>
         <div class="col-2 d-flex" style="align-items: center;justify-content: center;">
         </div>

         <div class="col-1 d-flex" style="align-items: center;justify-content: center;">
           <div class = "d-flex" style="align-items: center;justify-content: center;">
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
        for(let i=0; i< $(".total").length;i++){
          totalprice = Number(totalprice) + Number($(".pr").find(".total")[i].value);
          }
          $(".totalPrice").attr("value",totalprice)
      }