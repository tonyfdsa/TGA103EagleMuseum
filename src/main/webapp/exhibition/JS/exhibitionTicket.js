let exhibitionID = sessionStorage.getItem("exhibitionID")
let countAldult = sessionStorage.getItem("countAldult")
let countStu = sessionStorage.getItem("countStu")
let countOld = sessionStorage.getItem("countOld")
let countPhy = sessionStorage.getItem("countPhy")
let total = sessionStorage.getItem("total")
let memberId = sessionStorage.getItem("memberId")

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
		exhibitionID
	})
})
	.then(resp => resp.json())
	.then(R => {
		console.log(R);
		console.log(R.result);
		$(".exhibitionName").html(R.result[0].exhibitionName)

	})
//fetch('http://localhost:8080/TGA103eagleMuseum/TicketGetAll', {
//    method: '',
//    headers: { 'Content-Type': 'application/json' },
//    body: JSON.stringify({
//	
//        exhibitionID,
//        memberId
//    })
//})
//    .then(resp => resp.json())
//    .then(R => {
//        console.log(R);
//        console.log(R.result);
//        $(".ticketDate").html(R.result[0].ticketDate)
//        $(".aldultTicket").html(R.result[0].aldultTicket)
//        $(".stuTicket").html(R.result[0].stuTicket)
//        $(".oldTicket").html(R.result[0].oldTicket)
//        $(".phyTicket").html(R.result[0].phyTicket)
//        $(".ticketTotal").html(R.result[0].ticketTotal)
//
//
//    })

let ticketID = [];
fetch('http://localhost:8080/TGA103eagleMuseum/TicketGetAll')
	.then(resp => resp.json())
	.then(R => {
//		console.log(R);
//		console.log(R.result.pop().ticketID);
		ticketID = R.result.pop();
		
		$(".ticketID").html(ticketID.ticketID)
		$(".ticketDate").html(ticketID.ticketDate)
        $(".aldultTicket").html(ticketID.aldultTicket)
        $(".stuTicket").html(ticketID.stuTicket)
        $(".oldTicket").html(ticketID.oldTicket)
        $(".phyTicket").html(ticketID.phyTicket)
        $(".ticketTotal").html(ticketID.ticketTotal)
		
	});