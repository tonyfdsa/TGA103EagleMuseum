// 接受後端資料庫訊息
fetch('http://localhost:8080/TGA103eagleMuseum/ExhibitionGetAll')
  .then(resp => resp.json())
  .then(R => {
    console.log(R);
    let exhibitionContent = "";
    for (i = 0; i < R.result.length; i++) {

      exhibitionContent += `
      <div class="card id="${R.result[i].exhibitionID}" style="padding: 0%;margin: 0.5% 0.5%; width: 19rem;" v-on:click="exhContent">
      <img src="${R.result[i].exhibitionImgBase64}" class="card-img-top"/>
      <div class="card-body">
      <h5 class="card-title">${R.result[i].exhibitionName}</h5>
      <p class="card-text">${R.result[i].exhibitionStartDate} ~ ${R.result[i].exhibitionEndDate}</p>
      <a href="#" class="btn btn-outline-secondary" id="ticketBtn1">購票</a>
      </div>
      </div>
      </div>
      `;
    }
    $(".exhibitionContent").html(exhibitionContent);
  });


// 抓搜尋的資料回傳
$(".search-btn").click(function () {

})
const { createApp } = Vue;

createApp({
  data() {
    return {
      message: "Hello Vue.js",
      preview: null,
      image: null,
      preview_list: [],
      image_list: []
    };
  },
  methods: {

    search() {
      console.log("按");

      let exhibitionName = document.querySelector("#exhibitionName").value
      fetch('http://localhost:8080/TGA103eagleMuseum/ExhibitionGet', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          exhibitionName,
        })
      })
        .then(resp => resp.json())
        .then(R => {

          //清除所有的 $(".searchContent").append(text);
          $(".exhibitionContent").find(".card").remove();
          console.log(R.result);
          //新增搜尋的結果
          let exhibitionContent = "";
          for (i = 0; i < R.result.length; i++) {

            exhibitionContent += `
                    <div class="card id="${R.result[i].exhibitionID}" style="padding: 0%;margin: 0.5% 0.5%; width: 19rem;"">
                    <div class="card-img-top">
                      <div class="card-body">
                       <h5 class="card-title">${R.result[i].exhibitionName}</h5>
                       <p class="card-text">${R.result[i].exhibitionStartDate} ~ ${R.result[i].exhibitionEndDate}</p>
                       <a href="#" class="btn btn-outline-secondary" id="ticketBtn1">購票</a>
                       </div>
                     </div>
                   </div>
                    `;
          }
          $(".exhibitionContent").html(exhibitionContent);

        });
    },

    exhContent() {
      let exhibitionID = $(this).attr("data-id")
      // console.log(exhibitionID)
      sessionStorage.setItem("exhibitionID", exhibitionID)
      // console.log(sessionStorage.getItem("exhibitionID"))
      location.href = "./exhibitionContent.html"
    }

  },
}).mount("#app");