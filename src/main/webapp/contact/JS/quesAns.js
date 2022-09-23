

function openBox(questionContentID) {
	alert(questionContentID);
}

//用回覆按鈕開啟彈跳視窗
      $(".replybtn").on("click", function(){
		console.log("ok");
        $(".overlayTag").fadeIn();
      });
      
