<html>

<head>
    <title>CS480 Demo Web Service</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
</head>

<body>    
    
    <div>
        This is a simple page to demonstrate the web UI development. 
        The key tools and techniques used include:
        <ul>
            <li>HTML - Obviously</li>
            <li><a href="http://freemarker.org/">FreeMarker</a></li>
            <li><a href="http://jquery.com/">JQuery</a></li>
            <li><a href="http://api.jquery.com/jquery.ajax/">JQuery - AJAX</a></li>
        </ul>
    </div>

    <hr>

    <div>
        <div>
            <label>User List</label>
            <table border="1">            
                <tr>
                    <td>ID</td> 
                    <td>Creation Time</td>
                    <td>Delete</td>
                </tr>
<!--
Where is the list coming from?

Error would not load the page if user.Fname and user.Lname was posted
So I'm assuming that the list is not getting all of the JSON information
-->
                <#list users as user>
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.creationTime}</td>
                            <td><button onclick="deleteUser('${user.id}')">Delete</button></td>
                        </tr>
                </#list>
            </table>
        </div>
        
        <hr>
        
        <div>
            <label>Add User</label>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>First Name</td> 
                    <td>Last Name</td>                     
                    <td>Add</td>
                </tr>                
                <tr>
                    <td><input type="text" id="input_id"></td>
                    <td><input type="text" id="input_Fname"></td>
                    <td><input type="text" id="input_Lname"></td>                    
                    <td><button onclick="addUser()">Add</button></td>
                </tr>
            </table>
        </div>

        <hr>

        <div>
            <label>Query User</label>
            <input type="text" id="query_id"><button onclick="getUser()">Get</button>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Major</td>
                </tr>
                <tr>
                    <td><label id="result_id"></td>
                    <td><label id="result_Fname"></td>
                    <td><label id="result_Lname"></td>
                </tr>
            </table>
        </div>
    </div>
    
    
</body>

</html>