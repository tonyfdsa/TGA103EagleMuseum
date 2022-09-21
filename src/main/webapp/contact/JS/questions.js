$(".questionContent").click(function () {
    $(this).find(".innerBlock").slideToggle("slow");
});


$(".search-btn").click(function(){
    let input_val = ($(".queKeyWord").val());
    if(input_val == ""){
        console.log(this);
        $(".questionTypeSearch2").find(".needWords").slideToggle("slow"); 
    }
});