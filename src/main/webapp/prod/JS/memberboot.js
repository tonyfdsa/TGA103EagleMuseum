function click(){
  for (let i = 0; i < tab.length; i++){
    tab[i].addEventListener("click",function(){
        console.log("被按")
    })
    }
}

function active(){
  for (let i = 0; i < tab.length; i++){
    tab[i].addEventListener("mousemove",function(){
      //每當點擊清除class = upcontent 的id="show"
      contentAll = document.querySelectorAll(".upcontent");
   
      for (let j = 0; j <  contentAll.length; j++){
         contentAll[j].removeAttribute("id")
      }
      //用點擊設置內容，利用id
      contentAll[i].setAttribute("id","show")
    })
  }
}
tab = document.querySelectorAll(".topBtn")
active()
click()


//----- 產生min到max之間的亂數
function getRandom(min,max){
  return Math.floor(Math.random()*(max-min+1))+min;
};     

//--隨機生成RGB顏色
function randomRgbColor(xV) {
  var r = Math.floor(Math.random() * xV); //隨機生成256以內r值
  var g = Math.floor(Math.random() * xV); //隨機生成256以內g值
  var b = Math.floor(Math.random() * xV); //隨機生成256以內b值
  return "rgb(" + r + "," + g + "," + b + ")"; //返回rgb(r,g,b)格式顏色
}

var gRandom='';
function draw_Captcha(){
  gRandom=getRandom(10000, 99999);
  var canvas = document.getElementById("canvas");
  var context = canvas.getContext("2d");
  canvas.width = 110;
  canvas.height = 38;
  context.strokeRect(0, 0, canvas.width, canvas.height);
  //console.log('gRandom = ',gRandom);

  //---驗証數字
  for(var i=0;i<gRandom.toString().length;i++){
      var x = 5 + i * 20;
      var y = 26;
      //var deg = Math.random() * 70 * Math.PI / 180;//隨機弧度
      var deg = Math.random() * 38 * Math.PI / 160;//隨機弧度
      var txt = gRandom.toString()[i];
      //console.log('txt = ',txt);
      context.fillStyle = randomRgbColor(100);
      context.font = "bold 25px Arial";
      //修改座標原點和旋轉角度
      context.translate(x, y);
      context.rotate(deg);
      context.fillText(txt, 0, 0);
      //恢復座標原點和旋轉角度
      context.rotate(-deg);
      context.translate(-x, -y);
  }
  
  //---干擾線
  for (var i = 0; i < 6; i++) {
      context.strokeStyle = randomRgbColor(256);
      context.beginPath();
      context.moveTo(Math.random() * 120, Math.random() * 40);
      context.lineTo(Math.random() * 120, Math.random() * 40);
      context.stroke();
  }
  //---干擾點
  for (var i = 0; i < 50; i++) {
      context.fillStyle = randomRgbColor(256);
      context.beginPath();
      context.arc(Math.random() * 120, Math.random() * 40, 1, 0, 2 * Math.PI);
      context.fill();
  }
}
draw_Captcha();  
      
      





     