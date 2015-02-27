<html>

<head>
    <title>Link saver page</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
</head>

<body>    
    
    <div>
        Simple skeleton where user can save links
    </div>

    <hr>

    <div>
        <div>
            <label>Bookmark List</label>
            <table border="1">            
                <tr>
                    <td>Category</td>
                    <td>URL</td> 
                </tr>
                <#list bookmarks as data>
                        <tr>
                            <td>${data.bookmark}</td>
                            <td>${data.category}</td>                
                        </tr>
                </#list>
            </table>
        </div>
        
        <hr>
        
        <div>
            <label>Add User</label>
            <table border="1">
                <tr>
                    <td>Category</td>
                    <td>URL</td>                     
                    <td>Add</td>
                </tr>                
                <tr>
                    <td><input type="text" id="input_bookmark"></td>  
                    <td><input type="text" id="input_category"></td>               
                    <td><button onclick="addBookmark()">Add</button></td>
                </tr>
            </table>
        </div>

        <hr>

    </div>
    
    
</body>

</html>