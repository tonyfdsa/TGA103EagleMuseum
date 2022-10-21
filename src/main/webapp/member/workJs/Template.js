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

  // Banner彈出
   $(function(){
    $(".topBtn").mouseout(function(){
      $("#topcontent").slideToggle("fast");
    });
  });

  $(function(){
    $(".topBtn").mouseover(function(){
      $("#topcontent").slideToggle("fast");
    });
  });



  tab = document.querySelectorAll(".topBtn")
  active()
  click()

  window.addEventListener("load", function() { 

    if(sessionStorage.getItem('memberName') != null){
      document.querySelector('#memberName').textContent = 
      sessionStorage.getItem('memberName') + "，您好！";
    }
  });
