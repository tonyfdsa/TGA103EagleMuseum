let exhibitionID = sessionStorage.getItem("exhibitionID")
let countAldult = sessionStorage.getItem("countAldult")
let countStu = sessionStorage.getItem("countStu")
let countOld = sessionStorage.getItem("countOld")
let countPhy = sessionStorage.getItem("countPhy")
let total = sessionStorage.getItem("total")
console.log(exhibitionID);
console.log(countAldult);
console.log(countStu);
console.log(countOld);
console.log(countPhy);
console.log(total);

fetch('http://localhost:8080/TGA103eagleMuseum/ExhibitionGetByID', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
        exhibitionID,
    })
})
    .then(resp => resp.json())
    .then(R => {
        console.log(R);
        console.log(R.result);
        $(".exhibitionName").html(R.result[0].exhibitionName)

    })
fetch('http://localhost:8080/TGA103eagleMuseum/TicketGetByID', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
        exhibitionID,
    })
})
    .then(resp => resp.json())
    .then(R => {
        console.log(R);
        console.log(R.result);
        $(".ticketDate").html(R.result[0].ticketDate)
        $(".aldultTicket").html(R.result[0].aldultTicket)
        $(".stuTicket").html(R.result[0].stuTicket)
        $(".oldTicket").html(R.result[0].oldTicket)
        $(".phyTicket").html(R.result[0].phyTicket)
        $(".ticketTotal").html(R.result[0].ticketTotal)


    })