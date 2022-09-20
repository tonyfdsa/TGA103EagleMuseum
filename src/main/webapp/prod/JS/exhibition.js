function btn_click(e) {
    e.stopPropagation();
    console.log("button 被按");
  }
  function init() {
    document
      .querySelector("#ticketBtn1")
      .addEventListener("click", btn_click, false);
    document
      .querySelector("#ticketBtn2")
      .addEventListener("click", btn_click, false);
    document
      .querySelector("#ticketBtn3")
      .addEventListener("click", btn_click, false);
    document
      .querySelector("#ticketBtn4")
      .addEventListener("click", btn_click, false);
    document
      .querySelector("#ticketBtn5")
      .addEventListener("click", btn_click, false);
    document
      .querySelector("#ticketBtn6")
      .addEventListener("click", btn_click, false);
    document
      .querySelector(".search-btn")
      .addEventListener("click", btn_click, false);
  }
  window.addEventListener("load", init);