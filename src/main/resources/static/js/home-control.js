
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
function addBookmark() {

	var category = $('#input_category').val();
	var bookmark = $('#input_bookmark').val();

	if (userId) {
		$.ajax(
				{
					type : "POST",
					url  : "/cs480/submit/temp",
					data : {
						"User" : userId,
						"Category": category,
                                                "Bookmark": bookmark
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
}