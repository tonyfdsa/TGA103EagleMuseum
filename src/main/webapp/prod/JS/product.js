
    //搜尋byID  
    let productID = sessionStorage.getItem("prodID")
    // console.log(productID)
    fetch('http://localhost:8080/TGA103eagleMuseum/ProdGetImg', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          productID
        })
      })
      .then(resp => resp.json())
      .then(Data => {
        // console.log(Data.result)
      })
      fetch('http://localhost:8080/TGA103eagleMuseum/ProdgetById', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          productID
        })
      })
      .then(resp => resp.json())
      .then(Data => {
        console.log(Data.result)
        $(".prodName").html(Data.result[0].prodName)
        $(".prodPrice").html(Data.result[0].prodPrice)
        $(".prodInStock").html(Data.result[0].prodInStock)
        $(".prodDescription").html(Data.result[0].prodDescription)
//        console.log(Data.result.prodName)
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






      // 縮圖變預覽圖
      $(document).on("click", ".small", function (){
       $(".preview_img").attr("src",$(this).attr("src"))
      })

      


    //click 清除所有縮圖和預覽圖
    $(document).on("click", ".clear", function (){
      $(".pic").find(".smallpreview").remove();
      $(".preview").find(".preview_img").remove();
    })

    //加入購物車
    $(".cart").click(function(){
      let URL = "http://localhost:8080/TGA103eagleMuseum/ProdShoppingCart"
      let prodImg = $(".preview_img").attr("src")
      // console.log(prodImg)
      // console.log(productID)
      fetch(URL, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        productID,
        prodImg
        })
      })
      .then(resp => resp.json())
      .then(Data => {  
        console.log(Data);
        alert("已加入購物車")
      })
    })
    

