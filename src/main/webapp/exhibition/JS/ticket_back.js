// 接受後端資料庫訊息

fetch('http://localhost:8080/TGA103eagleMuseum/TicketGetAll')
    .then(resp => resp.json())
    .then(R => {
        // console.log(R);
        for (i = 0; i < R.result["length"]; i++) {

            let text = `
                  <div class="container-fluid add" data-ID = ${R.result[i].ticketID}>
                  <div class="row d-flex  column">
                  <div class="col-1 " style="text-align:center">${i + 1}</div>
                  <div class="col-1 ticketID" style="text-align:center">${R.result[i].ticketID}</div>
                  <div class="col-1 memberID" style="text-align:center">${R.result[i].memberID}</div>
                  <div class="col-1 exhibitionID" style="text-align:center">${R.result[i].exhibitionID}</div>
                  <div class="col-2 ticketDate"  style="text-align:center">${R.result[i].ticketDate}</div>
                  <div class="col-2 ticketTotal"  style="text-align:center">${R.result[i].ticketTotal}</div>
                  <div class="col-1 ticketStatus"  style="text-align:center">${R.result[i].ticketStatus}</div>
                  <div class="col-2"  style="text-align:center"> 
                    <span class="icon" >
                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 24 24"
                            width="25" height="25" class="fixed" data-ID = ${R.result[i].ticketID} class="fixed">
                            <g fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M9 7H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-3"></path>
                            <path d="M9 15h3l8.5-8.5a1.5 1.5 0 0 0-3-3L9 12v3"></path>
                            <path d="M16 5l3 3"></path>
                            </g>
                        </svg>
                    </span>     
                  </div>
                  <div class="col-1"></div>
                </div>
              </div>  `;


            $(".searchContent").append(text);
        }
    });