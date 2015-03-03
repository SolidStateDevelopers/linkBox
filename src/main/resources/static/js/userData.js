
window.onload = enterOnText;

function enterOnText(){
$("#pass").keyup(function(event){
    if(event.keyCode === 13){
        $("#userSubmit").click();
    }
});
};

function addUser() {
    var userName = $('#input_username').val();
    var password = $('#input_password').val();
    var firstName = $('#input_firstname').val();
    var lastname = $('#input_lastname').val();

    if (password && userName) {
        $.ajax(
                {
                    type: "POST",
                    url: "/user/" + userName,
                    data: {
                        "firstName": firstName,
                        "lastName": lastname,
                        "password": password
                    },
                    success: function (result) {
                        alert("User added!");
                        window.open("index.html", "_self");
                    },
                    error: function (jqXHR, exception) {
                        alert("Failed to add the user. Please check the inputs.");
                    }
                });
    } else {
        alert("must enter at least a password and username");
    }
}

function checkUserInfo() {
    var userName = $('#user').val();
    var password = $('#pass').val();
    if (userName && password) {
        $.ajax(
                {
                    //using a get is not secure. probly better post but first going to 
                    //worry about functionality
                    type: "GET",
                    url: "/user/" + userName + "/" + password,
                    data: {
                    },
                    success: function (result) {
                        //can open links to the localhost!
                        localStorage.setItem("userName", userName);
                        window.open("/cs480/BookmarkController/"+ userName, "_self");
                    },
                    error: function (jqXHR, exception) {
                        alert("Failed to get the user or password.");
                    }
                });
    } else {
        alert("must enter username and password");
    }
}


/* 
 * Still need to add a delete user function (maybe?), and
 * getUser funtion (to check if a user is present in the database)
 * 
 */