(() => {
    $(document).on("click", ".headerBtn", function (){
        let val  = $(this).val()
        // console.log($(this).val())
        $(".head").css("display","none")
        $(".head").eq(val).slideToggle("slow");
        $(".head").eq(val).find(".searchbar").val("")
        
      })

  // 接受後端訊息
  // 一進來get all
  fetch("/TGA103eagleMuseum/member/getAll")
    .then((resp) => resp.json())
    .then((member) => {
      for (i = 0; i < member["length"]; i++) {
        let status = "";
        if (member[i].memberPermission == "1") {
          status = "會員";
        } else if (member[i].memberPermission == "2") {
          status = "管理員";
        } else if (member[i].memberPermission == "3") {
          status = "黑名單";
        }

        let text = `
        <div class="container-fluid add" data-ID = ${member[i].memberID}>
          <div class="row d-flex  column">
          <div class="col-1 " style="text-align:center">${member[i].memberID}</div>
          <div class="col-2 " style="text-align:center">${member[i].memberName}</div>
          <div class="col-2" id="email" style="text-align:center">${member[i].memberEmail}</div>

          <div class="col-2"  style="text-align:center">${member[i].memberPhone}</div>
          <div class="col-1  status" style="text-align:center"  data-status= ${member[i].memberPermission}>${status}</div>
          <div class="col-3" id="modify" style="text-align:center">${member[i].modifyTime}</div>

          <div class="col-1"  style="text-align:center"> 
            <span class="icon" >
                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                    width="25" height="25" class="fixed" data-ID = ${member[i].memberEmail} class="fixed">
                    <g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
                    <path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
                    <path d="M16 5l3 3"></path>
                    </g>
                </svg>
            </span>    

            <span class="icon status"  data-value="0" data-ID = ${member[i].memberID}>
                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                    width="25" height="25" class="down" data-ID = ${member[i].memberID}>
                    <path
                    d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10s10-4.48 10-10S17.52 2 12 2zm0 18c-4.42 0-8-3.58-8-8c0-1.85.63-3.55 1.69-4.9L16.9 18.31A7.902 7.902 0 0 1 12 20zm6.31-3.1L7.1 5.69A7.902 7.902 0 0 1 12 4c4.42 0 8 3.58 8 8c0 1.85-.63 3.55-1.69 4.9z"
                    fill="currentColor"></path>
                </svg>
            </span>     
          </div>
          <div class="col-1"></div>
        </div>
      </div>
          `;
        $(".searchContent").append(text);
      }
    //   // 點擊進入
    //   for (i = 0; i < member["length"]; i++) {
    //     const button = document.querySelector(`#member${member[i].memId}`);
    //     button.addEventListener("click", () => {
    //       sessionStorage.setItem("member", button.value);
    //       location = "./emp_member_edit.html";
        // });
    //   }
    });



  // 抓商品搜尋的資料回傳
  $(".searchBtn").click(function () {
    let memberEmail = document.querySelector("#prodName").value;

    fetch("/TGA103eagleMuseum/member/selectSelf", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        memberEmail,
      }),
    })
      .then((resp) => resp.json())
      .then((member) => {
        //清除所有的 $(".searchContent").append(text);
        $(".searchContent").find(".add").remove();
        $(".searchContent").find("#searchNAN").remove();
        console.log(member, "0000");
   
        if (member == null) {
          console.log(member, "1111");

          $(".searchContent").append("<div style='color: red;' id=searchNAN> 查無結果，請重新輸入 </div>");
        }

        //新增搜尋的結果
            

            let status = "";
            if (member.memberPermission == "1") {
              status = "會員";
            } else if (member.memberPermission == "2") {
              status = "管理員";
            } else if (member.memberPermission == "3") {
              status = "黑名單";
            }
            console.log(status);

          let text = `
          
                        <div class="container-fluid add">
                        <div class="row d-flex  column">
                        <div class="col-1 " style="text-align:center">${member.memberID}</div>
                        <div class="col-2" style="text-align:center">${member.memberName}</div>
                        <div class="col-2" id="email" style="text-align:center">${member.memberEmail}</div>
                        <div class="col-2"  style="text-align:center">${member.memberPhone}</div>
                        <div class="col-1  status" style="text-align:center"  data-status= ${member.memberPermission}>${status}</div>
                        <div class="col-2" id="modify" style="text-align:center">${member.modifyTime}</div>

                        <div class="col-2"  style="text-align:center"> 
                          <span class="icon" data-ID = ${member.memberEmail}>
                              <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                                  width="25" height="25" class="fixed" data-ID = ${member.memberEmail}>
                                  <g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                  <path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
                                  <path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
                                  <path d="M16 5l3 3"></path>
                                  </g>
                              </svg>
                          </span>    

                          <span class="icon status"  data-value="0" data-ID = ${member.memberID}>
                              <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                                  width="25" height="25" class="down" data-ID = ${member.memberID}>
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
      );
  });



// 更新狀態
          $(document).on("click",".status" , function (){
            let memberID = $(this).attr("data-id");
  
            console.log(memberID);

              fetch("/TGA103eagleMuseum/member/remove", {
                  method: 'POST',
                  headers: {'Content-Type': 'application/json'},
                  body: JSON.stringify({
                    memberID,
                    
                  }),
                })
                .then(resp => resp.json())
                .then(member => {  
	              console.log(member.successful === true);
                console.log(typeof(member.successful));
                  if(member.successful === true){
                    alert("刪除成功")
                    location = "backHome.html"
                  }
                })
            
          })



  $(document).on("click", ".fixed", function () {

   var send_data = $(this).attr("data-id");
    console.log(send_data);

    sessionStorage.setItem("form_data", send_data);
    console.log(sessionStorage.getItem("form_data"));
    location.href = "backEdit.html";
  });
})();
