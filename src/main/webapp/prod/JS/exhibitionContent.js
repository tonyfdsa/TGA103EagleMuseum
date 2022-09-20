$(document).ready(function () {
    $('.cell').click(function () {
        $('.cell').removeClass('select');
        $(this).addClass('select');
    });
});

var addAldult = document.getElementById("addAldult");
var countAldult = document.getElementById("countAldult").value;
var minusAldult = document.getElementById("minusAldult");
var valueAldult = 150;
var addStu = document.getElementById("addStu");
var countStu = document.getElementById("countStu").value;
var minusStu = document.getElementById("minusStu");
var valueStu = 75;
var addOld = document.getElementById("addOld");
var countOld = document.getElementById("countOld").value;
var minusOld = document.getElementById("minusOld");
var valueOld = 75;
var addPhy = document.getElementById("addPhy");
var countPhy = document.getElementById("countPhy").value;
var minusPhy = document.getElementById("minusPhy");
var valuePhy = 0;


window.onload = function () {
    addAldult.onclick = function (e) {
        countAldult++;
        e.preventDefault();
        document.getElementById("countAldult").value = countAldult;
        document.getElementById("total").value = total();
    }
    minusAldult.onclick = function (e) {
        e.preventDefault(e);
        if (countAldult > 0) {
            countAldult--;
            document.getElementById("countAldult").value = countAldult;
            document.getElementById("total").value = total();
        } else {
            countAldult = 0;
            document.getElementById("countAldult").value = countAldult;
            document.getElementById("total").value = total();
        }
    }
    addStu.onclick = function (e) {
        e.preventDefault();
        countStu++;
        document.getElementById("countStu").value = countStu;
        document.getElementById("total").value = total();
    }
    minusStu.onclick = function (e) {
        if (countStu > 0) {
            e.preventDefault();
            countStu--;
            document.getElementById("countStu").value = countStu;
            document.getElementById("total").value = total();
        } else {
            countStu = 0;
            document.getElementById("countStu").value = countStu;
            document.getElementById("total").value = total();
        }
    }
    addOld.onclick = function (e) {
        e.preventDefault();
        countOld++;
        document.getElementById("countOld").value = countOld;
        document.getElementById("total").value = total();
    }
    minusOld.onclick = function (e) {
        e.preventDefault();
        if (countOld > 0) {
            countOld--;
            document.getElementById("countOld").value = countOld;
            document.getElementById("total").value = total();
        } else {
            countOld = 0;
            document.getElementById("countOld").value = countOld;
            document.getElementById("total").value = total();
        }
    }
    addPhy.onclick = function (e) {
        e.preventDefault();
        countPhy++;
        document.getElementById("countPhy").value = countPhy;
        document.getElementById("total").value = total();
    }
    minusPhy.onclick = function (e) {
        e.preventDefault();
        if (countPhy > 0) {
            countPhy--;
            document.getElementById("countPhy").value = countPhy;
            document.getElementById("total").value = total();
        } else {
            countPhy = 0;
            document.getElementById("countPhy").value = countPhy;
            document.getElementById("total").value = total();
        }
    }
    var total = function total(total) {
        total = (countAldult * valueAldult) + (countStu * valueStu) + (countOld * valueOld) + (countPhy * valuePhy)
        return total;
    }
}
