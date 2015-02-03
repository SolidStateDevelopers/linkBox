// JavaScript Document


var userInfo
var text

function myFunction() {
    var x = document.getElementById("frm1");
    var text = "";
    var i;
    for (i = 0; i < x.length ;i++) {
        text += x.elements[i].value + "<br>";
    }
	localStorage.setItem("userStored", text)
	window.open("main.html","_self")
}


function userInfo() {
	userInfo = document.getElementById("form1");
    text = "";
    var i;
    for (i = 0; i < x.length ;i++) {
        text += userInfo.elements[i].value + "<br>";
    }
	document.open()
	document.write("<p id='data'></p>")
	showInfo()
	document.close()
}

function showInfo() {
	text = localStorage.getItem("userStored")
	document.getElementById("demo").innerHTML = text
}