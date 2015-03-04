
function healthCheck() {
	$.ajax(
			{
				type : "GET",
				url  : "/cs480/ping",
				data : {
				},
				success : function(result) {
					$('#status').text(result);
				},
				error: function (jqXHR, exception) {
					$('#status').text("Failed to get the status");
				}
			});
}


function deleteUser(userId) {
	$.ajax(
			{
				type : "DELETE",
				url  : "/user/" + userId,
				data : {
				},
				success : function(result) {
					location.reload();
				},
				error: function (jqXHR, exception) {
					alert("Failed to delete the photo.");
				}
			});
}

function addUser() {

	var userId = $('#input_id').val();
	var userName = $('#input_Fname').val();
	var userMajor = $('#input_Lname').val();

	if (userId) {
		$.ajax(
				{
					type : "POST",
					url  : "/user/" + userId,
					data : {
						"name" : userName,
						"major" : userMajor
					},
					success : function(result) {
						location.reload();
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the user. Please check the inputs.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
}

function getUser(userId) {
	var userId = $('#query_id').val();
	if (userId) {
		$.ajax(
				{
					type : "GET",
					url  : "/user/" + userId,
					data : {
					},
					success : function(result) {
						$('#result_id').text(result.id);
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
function addBookmark(presentCategory) {
    var category, bookmark;
    if(presentCategory){
        category = presentCategory;
        bookmark = window.prompt("copy or type url here");
    } else {
        category = $('#input_category').val();
        bookmark = $('#input_bookmark').val();
    }
    
	
	var userId = localStorage.getItem("userName");

	if (bookmark) {
		$.ajax(
				{
					type : "POST",
					url  : "/cs480/BookmarkController/" + userId,
					data : {
                                                "User ID":  userId,
                                                "Bookmark": bookmark,
						"Category": category                      
					},
					success : function(result) {
						location.reload();
                                                alert("URL succesfully saved!");
					},
					error: function (jqXHR, exception) {
						alert("Couldn't save link.");
					}
				});
	} else {
		alert("Could not save link");
	}
}

//this is called after the DOM is created so that we can get
//document elements
function dragEvents() {
// target events
    var target = document.getElementById('target');
    target.addEventListener('dragover', function(e) {
        e.preventDefault();
        return false;
    });

    target.addEventListener('dragenter', function(e) {
        e.target.classList.add("dragover");
    });

    // we want to call this from dragleave and drop
    function onLeave(e) {
        e.target.classList.remove("dragover");
    }

    target.addEventListener('dragleave', onLeave);

    target.addEventListener('drop', function(e) {
        // don't let the browser switch to an image!
        e.preventDefault();
        onLeave(e);
        
        var category = window.prompt("Which category do you want this link in?", "Default");
        var userId = localStorage.getItem("userName");
        var bookmark = e.dataTransfer.getData('Text');
        
        if (category != null) {
		$.ajax(
				{
					type : "POST",
					url  : "/cs480/ControlPanel/" + userId,
					data : {
                                                "User ID":  userId,
                                                "Bookmark": bookmark,
						"Category": category                      
					},
					success : function(result) {
						location.reload();
					},
					error: function (jqXHR, exception) {
						alert("Couldn't save link.");
					}
				});
	} else {
		alert("Could not save link");
	}
		//get the text of the url, set it to <a> and place it into the target div
//		var urlText = e.dataTransfer.getData('Text');
//		var url = document.createElement("a");
//		url.setAttribute('href', urlText);
//		url.innerHTML = urlText;
//		target.appendChild(url);
		
    });
}

function goToSettings() {
    var userName = localStorage.getItem("userName")
    if (userName) {
        $.ajax(
                {
                    data: {
                    },
                    success: function (result) {
                        //can open links to the localhost!
                        window.open("/cs480/ControlPanel/"+ userName, "_self");
                    },
                    error: function (jqXHR, exception) {
                        alert("Could not go to settings");
                    }
                });
    } else {
        alert("Please try again!");
    }
}
