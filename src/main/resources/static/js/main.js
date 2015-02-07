// JavaScript Document


var userInfo
var text

function checkUserInfo() {
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

//want to use the userID to show the first and last name of the person, if it exsits 

//NOT WORKING
function showInfo() {
	var userId = localStorage.getItem("userID");
	if (userId) {
		$.ajax(
				{
					type : "GET",
					url  : "/cs480/user/" + userId,
					data : {
					},
					success : function(result) {
						$('#result_Fname').text(result.Fname);
						$('#result_Lname').text(result.Lname);
					},
					error: function (jqXHR, exception) {
						alert("Failed to get the user.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
}