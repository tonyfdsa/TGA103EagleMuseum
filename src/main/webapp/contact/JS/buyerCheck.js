        // 點擊TAB更換頁面
        tabClick = document.querySelectorAll(".pageBtn")
        function TabClick(){
        for (let i = 0; i < tabClick.length; i++){
            tabClick[i].addEventListener("click",function(){
                //每當點擊清除id="show"
                var contentAll = document.querySelectorAll(".SR");
                for (let j = 0; j <  contentAll.length; j++){
                    $(".SR").removeAttr("id","show");
                    $(".pageBtn").removeClass("show");
                 }
            //用點擊設置內容，利用id

            contentAll[i].setAttribute("id","show")
            tabClick[i].setAttribute("class","col-auto pageBtn show")
            })
         }
        }
        TabClick()