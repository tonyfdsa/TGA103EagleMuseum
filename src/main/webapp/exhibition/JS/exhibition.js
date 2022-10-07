// 接受後端資料庫訊息
fetch('http://localhost:8080/TGA103eagleMuseum/ExhibitionGetAll')
  .then(resp => resp.json())
  .then(R => {
    console.log(R);
    let exhibitionContent = "";
    for (i = 0; i < R.result.length; i++) {

      exhibitionContent += `
      <div class="card id="${R.result[i].exhibitionID}" style="padding: 0%;margin: 0.5% 0.5%; width: 19rem;" v-on:click="exhContent">
      <img src="${R.result[i].exhibitionImg}" class="card-img-top"/>
      <div class="card-body">
      <h5 class="card-title">${R.result[i].exhibitionName}</h5>
      <p class="card-text">${R.result[i].exhibitionStartDate} ~ ${R.result[i].exhibitionEndDate}</p>
      <a href="#" class="btn btn-outline-secondary" id="ticketBtn1">購票</a>
      </div>
      </div>
      </div>
      `;
    }
    $(".card-img-top").attr('src', base64string)
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
/**
       * API
       */
// const baseUrl = 'http://localhost:8080/TGA103eagleMuseum/';
// const api = {
//   exhibition: '/ExhibitionGetAll',
// };

// /**
//  * 進入時執行
//  */
// async function init() {
//   let res = await get(api.exhibition);
//   document.querySelector('#exhibitionTable').innerHTML = res.result
//     .map((e) =>
//       Template(
//         e.exhibitionID,
//         e.exhibitionType,
//         e.exhibitionName,
//         e.exhibitionStartDate,
//         e.exhibitionEndDate,
//         e.exhibitionArticle,
//         e.exhibitionStatus,
//         e.lastUpdateTime,
//         e.exhibitionImg
//       )
//     )
//     .join('');
// }

/*
 * click
 */

// insert
//  document.querySelector('#onInsert').onclick = async () => {
//    let [
//      exhibitionType,
//      exhibitionName,
//      exhibitionStartDate,
//      exhibitionEndDate,
//      exhibitionArticle,
//      exhibitionStatus,
//      lastUpdateTime,
//      locationId,
//      exhibitionImg,
//    ] = [
//      document.querySelector('#exhibitionType').value,
//      document.querySelector('#exhibitionName').value,
//      document.querySelector('#exhibitionStartDate').value,
//      document.querySelector('#exhibitionEndDate').value,
//      document.querySelector('#exhibitionArticle').value,
//      document.querySelector('#exhibitionStatus').value,
//      document.querySelector('#lastUpdateTime').value,
//      document.querySelector('#locationId').value,
//      document.querySelector('#exhibitionImg'),
//    ];

//    if (!exhibitionImg.files[0]) return alert('請上傳圖片');
//    let reader = new FileReader();
//    reader.readAsDataURL(exhibitionImg.files[0]);
//    reader.onload = async (data) => {
//      let exhibitionImgBase64 = data.target.result;

//      const res = await post(api.exhibition, {
//        exhibitionType,
//        exhibitionName,
//        exhibitionStartDate,
//        exhibitionEndDate,
//        exhibitionArticle,
//        exhibitionStatus,
//        lastUpdateTime,
//        locationId,
//        exhibitionImgBase64,
//      });

//      if (res.code === 200) {
//        alert('新增成功^_^!');
//        history.go();
//        return;
//      }
//      alert('新增失敗U_U!');
//    };
//  };

//  // update
//  document.querySelector('#onUpdate').onclick = async () => {
//    let [exhibitionId, img] = [
//      document.querySelector('#exhibitionType').value,
//      document.querySelector('#putImg'),
//    ];

//    if (!img.files[0]) return alert('請上傳圖片');
//    let reader = new FileReader();
//    reader.readAsDataURL(img.files[0]);
//    reader.onload = async (data) => {
//      let exhibitionImg = data.target.result;
//      const res = await put(api.exhibition, {
//        exhibitionId,
//        exhibitionImg,
//      });
//      if (res.code === 200) {
//        alert('更新成功^_^!');
//        history.go();
//        return;
//      }
//      alert('更新失敗U_U!');
//    };
//  };

// delete
//  document.querySelector('#onDelete').onclick = async () => {
//    let [exhibitionId] = [document.querySelector('#deleteId').value];

//    let res = await Delete(api.exhibition + '/' + exhibitionId);

//    if (res.code === 200) {
//      alert('刪除成功^_^!');
//      history.go();
//      return;
//    }
//    alert('刪除失敗U_U!');
//  };

/*
 * fetch
 */

// GET
// async function get(api) {
//   let resp;
//   try {
//     resp = await fetch(baseUrl + api).then((res) => res.json());
//   } catch (error) {
//     console.log(error);
//     alert(error);
//   }
//   return resp;
// }

// // POST
// async function post(api, data) {
//   let resp;
//   try {
//     resp = await fetch(baseUrl + api, {
//       method: 'POST',
//       body: JSON.stringify(data),
//       headers: {
//         'Content-type': 'application/json',
//       },
//     }).then((res) => res.json());
//   } catch (error) {
//     console.log(error);
//     alert(error);
//   }
//   return resp;
// }

// // PUT
// async function put(api, data) {
//   let resp;
//   try {
//     resp = await fetch(baseUrl + api, {
//       method: 'PUT',
//       body: JSON.stringify(data),
//       headers: {
//         'Content-type': 'application/json',
//       },
//     }).then((res) => res.json());
//   } catch (error) {
//     console.log(error);
//     alert(error);
//   }
//   return resp;
// }

// // DELETE
// async function Delete(api, data) {
//   let resp;
//   try {
//     resp = await fetch(baseUrl + api, {
//       method: 'DELETE',
//       headers: {
//         'Content-type': 'application/json',
//       },
//     }).then((res) => res.json());
//   } catch (error) {
//     console.log(error);
//     alert(error);
//   }
//   return resp;
// }

// function Template(
//   id,
//   type,
//   name,
//   start,
//   end,
//   article,
//   status,
//   last,
//   location,
//   img
// ) {
//   return `<tr>
//      <td>${id}</td>
//      <td>${type}</td>
//      <td>${name}</td>
//      <td>${start}</td>
//      <td>${end}</td>
//      <td>${article}</td>
//      <td>${status}</td>
//      <td>${last}</td>
//      <td>${location}</td>
//      <td >
//        <img style="width: 100px; height: 100px" src="${img}" alt="NASA">
//      </td>
//    </tr>`;
// }

// window.addEventListener('load', init);