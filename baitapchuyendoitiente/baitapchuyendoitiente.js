function chuyendoi(){
let giaTriTien = parseFloat(document.getElementById("soTien").value);
let soTienFrom=document.getElementById("from").value;
let soTienTo=document.getElementById("to").value;
let kq1;
let kq2;
let kq3;
if (soTienFrom === "usd" && soTienTo === "vietnam"){
    kq1 = giaTriTien*24000;
    document.getElementById("kq").innerHTML = kq1;
}
else if (soTienFrom === "vietnam" && soTienTo === "usd"){
    kq2 = giaTriTien/24000;
    document.getElementById("kq").innerHTML = kq2;
}
else{
    kq3 = giaTriTien;
    document.getElementById("kq").innerHTML = kq3;
}
}
