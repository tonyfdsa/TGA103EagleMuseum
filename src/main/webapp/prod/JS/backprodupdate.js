
      //  上傳圖片
      
      var preview_img = function (file) {
        var reader = new FileReader(); // 用來讀取檔案
        reader.readAsDataURL(file); // 讀取檔案

        reader.addEventListener("load", function () {
              let img_src =
              '<img src="' + reader.result + '" class="preview_img">';
              document.querySelector(".preview").innerHTML = img_src
        });
      };


       //抓資料庫商品標籤類型
       fetch('http://localhost:8080/TGA103eagleMuseum/ProdTagGetAll')
        .then(resp => resp.json())
        .then(R => {
          for( i = 0 ; i < R.result["length"] ; i++){
            let text = `
            <option value="${R.result[i].prodTypeId}">${R.result[i].prodType}</option>
            `
            $(".prodTypeID").append(text);
          }
        });

        //用ID搜尋把資料填上要的地方

      var form_data = JSON.parse(sessionStorage.getItem("form_data"));
      var productID = form_data.productId
      fetch('http://localhost:8080/TGA103eagleMuseum/ProdgetById', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          productID
        })
      })
      .then(resp => resp.json())
      .then(R => {  
        console.log(R)
        let prodName = R.result[0].prodName;
        let prodStatus= R.result[0].prodStatus;
        let prodTypeID = R.result[0].prodTypeID;
        let prodPrice = R.result[0].prodPrice;
        let prodDescription = R.result[0].prodDescription;
        let prodInStock = R.result[0].prodInStock;
        let bestSeller = R.result[0].bestSeller;

        console.log(prodName);
        document.querySelector(".prodName").value = prodName
        document.querySelector(".prodPrice ").value = prodPrice 
        document.querySelector(".prodInStock").value = prodInStock
        document.querySelector(".prodDescription").value = prodDescription 
        document.querySelector(".prodTypeID").value = prodTypeID
        document.querySelector(".prodStatus").value = prodStatus

      })  

      
        let picture_list = document.getElementsByClassName("pic")[0];
        fetch('http://localhost:8080/TGA103eagleMuseum/ProdGetImg', {
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({
            productID,
          })
        })
        .then(resp => resp.json())
        .then(R => {  
          console.log(R.result)
          // console.log(R.result)
          // console.log(R.result[0].productimg)
          for (let i = 0; i < R.result.length; i++) {
              let div_html = `
                  <span class="smallpreview" >
                    <img src="${R.result[i].productimg}" class="small"/>
                  </span>
              `;
              picture_list.insertAdjacentHTML("beforeend", div_html); // 加進節點
          };
          let img_src =
          '<img src="' + R.result[0].productimg + '" class="preview_img">';
          document.querySelector(".preview").innerHTML = img_src
        })  
      

              //feach 上傳多張圖片(目前只做好單張)
      
      $(".submit").click(function(){
        let form_data = JSON.parse(sessionStorage.getItem("form_data"));
        let productID = form_data.productId
        let productimg = img.result;
        let prodName = document.querySelector(".prodName").value;
        let prodPrice = document.querySelector(".prodPrice ").value 
        let prodInStock = document.querySelector(".prodInStock").value
        let prodDescription = document.querySelector(".prodDescription").value 
        let prodTypeID = document.querySelector(".prodTypeID").value
        let prodStatus  = document.querySelector(".prodStatus").value    
        // console.log(productimg);
        //刪除圖片
        fetch('http://localhost:8080/TGA103eagleMuseum/ProdDeImg', {
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({
            productID,
          })
        })
        .then(resp => resp.json())
        .then(R => {  
          console.log(R)
        })


        //上傳圖片
        fetch('http://localhost:8080/TGA103eagleMuseum/InsertProdImg', {
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({
            productID,
            productimg
          })
        })
        .then(resp => resp.json())
        .then(R => {  
          // console.log(R)
        })
        
        // update資料
        fetch('http://localhost:8080/TGA103eagleMuseum/ProdUpdate', {
          
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({
            productID,
            prodName,
            prodPrice ,
            prodInStock,
            prodDescription ,
            prodTypeID,
            prodStatus
          })
        })
        .then(resp => resp.json())
        .then(R => {  
          // console.log(R)
        })

        })


              // 縮圖上傳
      var img = "";
      var the_file_element = document.querySelector("#upload")
      the_file_element.addEventListener("change", function (e) {
        var num=e.target.files.length
        // console.log($(".pic").find(".small").length)
        let picture_list = document.getElementsByClassName("pic")[0];
        // console.log(this.files.length);
        let that = this;
        if (num > 4 || $(".pic").find(".small").length >= 4){
          alert("最多上傳4張")
        }else{
          for (let i = 0; i < this.files.length; i++) {
            preview_img(this.files[0]);
            let reader = new FileReader(); // 用來讀取檔案
            reader.readAsDataURL(this.files[i]); // 讀取檔案
            // console.log(this.files.length);
            reader.addEventListener("load", function (e) {
              // console.log("load 事件");
              // console.log(e);
              let div_html = `
                  <span class="smallpreview" >
                    <img src="${reader.result}" class="small"/>
                  </span>
              `;
              picture_list.insertAdjacentHTML("beforeend", div_html); // 加進節點
              let form_data = new FormData();
              form_data.append("the_file", that.files[i]);
              img = reader;
              // console.log(reader.result)
              
              })
          };
        }
      })  

     
      
      // 縮圖變預覽圖
      $(document).on("click", ".small", function (){
       $(".preview_img").attr("src",$(this).attr("src"))
      })

      


    //click 清除所有縮圖和預覽圖
    $(document).on("click", ".clear", function (){
      $(".pic").find(".smallpreview").remove();
      $(".preview").find(".preview_img").remove();
    })


    