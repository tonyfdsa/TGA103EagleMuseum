
//搜尋彈出
$(document).on("click", ".headerBtn", function (){
    let val  = ($(this).val()-1)
    // console.log( $(".head").eq(val))
    $(".head").css("display","none")
    $(".head").eq(val).slideToggle("slow");
    $(".head").eq(val).find(".searchbar").val("")
    
  })
        
//getBY訂單狀態


function OrderGetByStat(){
  let orderStatus = document.querySelector(".select").value
  fetch("http://localhost:8080/TGA103eagleMuseum/OrderGetByStat",{
  method: 'POST',
  headers: {'Content-Type': 'application/json'},
  body: JSON.stringify({
    orderStatus 
    })
  })
  .then(resp => resp.json())
  .then(DATA => {
    console.log(DATA);
    // console.log(DATA.result.length)
    $(".searchContent").find(".add").remove();
    for(let i = 0 ; i < DATA.result.length; i++){
      if(DATA.result[i].orderStatus === 1){
        var Status = "未出貨"
      }else if(DATA.result[i].orderStatus === 2){
        var Status = "處理中"
      }else if(DATA.result[i].orderStatus === 3){
        var Status = "已出貨"
      }else if(DATA.result[i].orderStatus === 4){
        var Status = "已到貨"
      }else{
        var Status = "已收貨"
      }
      let text = `
              <div class="container-fluid add" >
              <div class="row d-flex  column">
              <div class="col-1 " style="text-align:center">${DATA.result[i].orderID}</div>
              <div class="col-2 orderID" style="text-align:center">${Status}</div>
              <div class="col-2" style="text-align:center">${DATA.result[i].createTime}</div>
              <div class="col-2 memberId" style="text-align:center" >${DATA.result[i].memberId}</div>
              <div class="col-2"  style="text-align:center">${DATA.result[i].orderAmount}</div>
              <div class="col-2"  style="text-align:center"> 
                <span class="icon" >
                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                        width="25" height="25" class="fixed" data-ID = ${DATA.result[i].orderID} >
                        <g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
                        <path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
                        <path d="M16 5l3 3"></path>
                        </g>
                    </svg>
                </span>    
  
                <span style="margin-left: 10px; margin-right:10px" class="icon status"  data-value="1"  data-ID = ${DATA.result[i].productID}>
                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24" width="25" height="25" class="up" data-value = ""}>
                        <path d="M5 18h14v2H5v-2zm4.6-2.7L5 10.7l2-1.9l2.6 2.6L17 4l2 2l-9.4 9.3z" fill="currentColor">    
                        </path>
                    </svg>
                </span>
  
                <span class="icon status"  data-value="0" data-ID = ${DATA.result[i].productID}>
                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                        width="25" height="25" class="down" data-ID = ${DATA.result[i].productID}>
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
    
  })
}

OrderGetByStat();
document.querySelector(".select").addEventListener("change",function(e){
  OrderGetByStat()
})


//點修改紐跳出狀態修改燈箱 
$(document).on("click", ".fixed",function(){
 // 開啟燈箱視窗
  $(".statWindow").fadeIn();
})


// 關閉燈箱

$(".statClose, div.statWindow ").on("click", function(e){
  $("div.statWindow").fadeOut();
  });


 $("div.statWindow > article").on("click", function(e){
  e.stopPropagation();
  });


//更新狀態  
  $(document).on("click",".fixed",function(){
    let orderID = $(this).attr("data-id")
    console.log(orderID)
    document.querySelector(".inorderID").innerHTML = orderID
    $(".statSubmit").click(function(){
      let orderStatus = document.querySelector(".statchange").value
      let URL = 'http://localhost:8080/TGA103eagleMuseum/OrderStatUpdate'
      
      fetch(URL, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
              orderStatus,
              orderID
            })
          })
          .then(resp => resp.json())
          .then(Data => {  
            console.log(Data);
          })
    })
  })
  

  