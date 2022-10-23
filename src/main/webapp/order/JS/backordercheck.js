    //根據訂單編號取得明細

    let orderID = sessionStorage.getItem("orderID");
    let orderStatus = sessionStorage.getItem("orderStatus");
    document.querySelector(".orderTag").addEventListener("load",function(){
      $(this).val(orderStatus) 
    })
   
  //  console.log(orderStatus)
    console.log(document.querySelector(".orderTag").value)
    $(document).on("change",".orderTag",function(){
      let orderStatus = $(".orderTag").val();
      // console.log(orderStatus)
      //更新狀態
      fetch("/TGA103eagleMuseum/OrderStatUpdate",{
        
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          orderID,
          orderStatus
        })
      })
      .then(resp => resp.json())
      .then(Data => {
        console.log(Data);
        if(Data.code === 200){
          alert("狀態更新成功")
          let orderStatus = $(".orderTag").val();
          sessionStorage.setItem("orderStatus", orderStatus)
          // console.log(orderStatus)
        }
       

    })
  })





    // console.log(orderStatus)
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

        for(let i = 0 ; i < Data.result['length']; i++){
          
          text=`
          <div class="container-fluid add">
            <div class="row d-flex  column">
              <div class="col-1 " style="text-align:center">${i+1}</div>
              <div class="col-2" style="text-align:center">${orderID}</div>
              <div class="col-2" style="text-align:center">${Data.result[i].productID}</div>
              <div class="col-2" style="text-align:center" >${Data.result[i].prodQuantity}</div>
              <div class="col-2"  style="text-align:center"></div>
              <div class="col-2"  style="text-align:center"> </div>
              <div class="col-1"></div>
            </div>
          </div>  
      `   ;
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

