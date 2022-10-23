(() => {
  //抓sessionStorage的資料
  const form_data = JSON.parse(sessionStorage.getItem("form_data"));
  const collectionID = form_data.collectionID;
  const collectionMaterial = form_data.collectionMaterial;

  // $(".prdName").text(cartproduct.name);
  // 把ID往下帶
  fetch("http://localhost:8080/TGA103eagleMuseum/collectionGetOne", {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      collectionID: collectionID,
      collectionMaterial: collectionMaterial,
    }),
  })
    .then(resp => resp.json())
    .then(collection => {
      document.querySelector('.colTitleFront').value = $(".colTitleFront").text("館藏名稱: " + collection.collectionTitle);
      document.querySelector('.collectionTextFront').value =$(".collectionTextFront").text(collection.collectionText);
      document.querySelector('.collectionEar').value = $(".collectionEar").text("朝代: " + collection.collectionEar);
      document.querySelector('.collectionMaterial').value = $(".collectionMaterial").text("材質: " + collection.collectionMaterial);
    })

 // 更換圖片
 let img = 1;
 $("#imgBtnR").click(function(){
     imgAll = $(".img");
     $(".show").attr("src", $(imgAll[img]).attr("src"))
     if(img < (imgAll.length-1)){
         img++;   
     }
     else{
         img = 0;
     }
 })

 // 更換圖片
     $("#imgBtnL").click(function(){
     imgAll = $(".img");
     $(".show").attr("src", $(imgAll[img]).attr("src"))
     if(img == 0){
         img = (imgAll.length-1);
     }
     else{
         img--
     }
 })


 //我的最愛
 $(".like").click(function(){
     if($(".like div iconify-icon").attr("icon") == "ant-design:heart-outlined"){
         $(".like div iconify-icon").attr("icon","ant-design:heart-twotone")
     }
     else{
         $(".like div iconify-icon").attr("icon","ant-design:heart-outlined")
     }
 })
})();