
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
                        window.open("main.html", "_self");
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
    var username = $('#user').val();
    var password = $('#pass').val();
    if (username && password) {
        $.ajax(
                {
                    //using a get is not secure. probly better post but first going to 
                    //worry about functionality
                    type: "GET",
                    url: "/user/" + username + "/" + password,
                    data: {
                    },
                    success: function (result) {
                        window.open("main.html", "_self");
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