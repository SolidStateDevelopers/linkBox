<html>

<head>
    <title>Link saver page</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
</head>

<body onload="dragEvents()">    
    <div id = "target">
    <div>
        Simple skeleton where user can save links
    </div>

    <hr>

    <div>
        <div>
            <label>Bookmark Lists</label>
        <div>
        <div>
                <#list bookmarks as data>
                        <table border="1" style="display: inline-block;">
                            <tr>
                                <td><a href="${data.category}">${data.category}</a></td>
                                <td><button onclick="addBookmark('${data.category}')">Add</button></td> 
                            </tr>
                        </table>
                </#list>
            
        </div>
        
        <hr>
        
        <div>
            <label>Add URL</label>
            <table border="1">
                <tr>
                    <td>Category</td>
                    <td>URL</td>                     
                    <td>Add</td>
                </tr>                
                <tr>
                    <td><input type="text" id="input_category"></td>  
                    <td><input type="text" id="input_bookmark"></td> 
                    <td><button onclick="addBookmark()">Add</button></td>
                </tr>
            </table>
        </div>

        <hr>

    </div>
    </div>
    
</body>

</html>