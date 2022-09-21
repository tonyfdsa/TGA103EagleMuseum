
 // 更換圖片>
 var img = 1;
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

 // 更換圖片<
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