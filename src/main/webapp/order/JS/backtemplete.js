$(".sideBtn").click(function () {
    $(this).nextAll(".Btn").slideToggle("slow");
  });

  window.addEventListener("load", function() { 

    if(sessionStorage.getItem('memberName') != null){
      document.querySelector('#memberName').textContent = 
      sessionStorage.getItem('memberName') + "，您好！";
    }
  });
  window.addEventListener("load", function() { 

    if(sessionStorage.getItem('memberName') != null){
      document.querySelector('#memberName').textContent = 
      sessionStorage.getItem('memberName') + "，您好！";
    }
  });