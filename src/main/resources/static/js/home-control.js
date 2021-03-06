
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

function deleteLink(bookmark) {
   var userName = localStorage.getItem("userName");
   var link;
   if(bookmark){
       link = bookmark;
   } else {
        link = window.prompt("Type your url here to delete");
   }
   console.log(link);
	$.ajax(
			{
				type : "POST",
				url  : "/user/deleteBookmark/" + userName,
				data : {
                                    "bookmark" : link
				},
				success : function(result) {
                                    alert(link + " Link has been deleted");
                                    location.reload();
				},
				error: function (jqXHR, exception) {
                                    alert("Failed to delete the bookmark.");
				}
			});
}

function deleteCategory(folder) {
   if(confirm("Are you sure you want to delete?\nThis will remove all links and the category")){
   var userName = localStorage.getItem("userName");
   var category;
   if(folder){
       category = folder;
   } else {
        category = window.prompt("Type your category here to delete");
   }
   console.log(category);
	$.ajax(
			{
				type : "POST",
				url  : "/user/deleteCategory/" + userName,
				data : {
                                    "category" : category
				},
				success : function(result) {
                                    alert(category + "\n Link has been deleted");
                                    location.reload(true);
				},
				error: function (jqXHR, exception) {
                                    alert("Failed to delete the bookmark.");
				}
			});
    } else {
                        
    }
    location.reload();
}

function addBookmark(presentCategory) {
    var category, bookmark;
    if($('#input_bookmark').val()){
        bookmark = $('#input_bookmark').val();
    } else {
        bookmark = window.prompt("copy or type url here");
    }
    if(presentCategory){
        category = presentCategory;
    } else {
        
        category = $('#input_category').val();
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
    var categories = document.getElementsByClassName('categoryTable');
    //this for loop will add drag events to the tables specifically. 
    //that way they can just drag into a category without typing it out
    
    document.addEventListener('dragover', function(e) {
        e.preventDefault();
        return false;
    });

    // we want to call this from dragleave and drop
    function onLeave(e) {
        e.target.classList.remove("dragover");
    }

    document.addEventListener('dragleave', onLeave);

    document.addEventListener('drop', function(e) {
        // don't let the browser switch to an image!
        e.preventDefault();
        onLeave(e);
        var category;
        if(document.getElementById('categoryName')){
            category = document.getElementById('categoryName').innerHTML;
        } else {
            category = window.prompt("Which category do you want this link in?", "Default");
        }
        var userId = localStorage.getItem("userName");
        var bookmark = e.dataTransfer.getData('Text');
        
        if (category !== null) {
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
    var userName = localStorage.getItem("userName");
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



function sortList(presentCategory)
{
   var category = presentCategory;
   var userName = localStorage.getItem("userName");
   var x = document.getElementById("Sort").selectedIndex; // holds the index values
   var y = document.getElementsByTagName("option")[x].value //holds the value at the index in the dropdown menu
   //alert(x.selectedIndex);
   //var category = presentCategory;
   //var userName = localStorage.getItem("userName");
   //var x = document.getElementById("Sort");
   //alert(x);
   //alert(y);
   if (x == 1) //Ascending
   {
      $.ajax(
              {
                 data: {
                    
                 },
                 success: function(result){
                    window.open("/cs480/BookmarkController/" + userName + "/" + category + "/" + y, "_self");
                 },
                 error: function(jqXHR, exception) {
                    alert("Error")
                 }
              });
      
   } 
   else if (x == 2) //Descending
   {
      $.ajax(
              {
                 data: {
                    
                 },
                 success: function(result){
                    window.open("/cs480/BookmarkController/" + userName + "/" + category + "/" + y, "_self");
                 },
                 error: function(jqXHR, exception) {
                    alert("Error")
                 }
              });
      
   } 
   else if (x == 3) // Oldest 
   {
      $.ajax(
              {
                 data: {
                    
                 },
                 success: function(result){
                    window.open("/cs480/BookmarkController/" + userName + "/" + category + "/" + y, "_self");
                 },
                 error: function(jqXHR, exception) {
                    alert("Error")
                 }
              });    
   } 
   else if (x == 4) //Newest 
   {
      $.ajax(
              {
                 data: {
                    
                 },
                 success: function(result){
                    window.open("/cs480/BookmarkController/" + userName + "/" + category + "/" + y, "_self");
                 },
                 error: function(jqXHR, exception) {
                    alert("Error")
                 }
              });
   }
   
}


function goToHome() {
    var userName = localStorage.getItem("userName");
    if (userName) {
        $.ajax(
                {
                    data: {
                    },
                    success: function (result) {
                        //can open links to the localhost!
                        window.open("/cs480/BookmarkController/"+ userName, "_self");
                    },
                    error: function (jqXHR, exception) {
                        alert("Could not go to home");
                    }
                });
    } else {
        alert("Please try again!");
    }
}