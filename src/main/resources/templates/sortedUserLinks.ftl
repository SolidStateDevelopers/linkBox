<html>

<head>
    <title>Link saver page</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
    <link href="/css/accountStyle.css" rel="stylesheet" type="text/css" />
</head>

<body onload="dragEvents()">    
    <div id = "target">
    <div class="banner">
        Simple skeleton where user can save links
            <td><button onclick ="deleteLink()">Delete Link</button></td>
    
   <div>
      <td><select id="Sort" onchange = "sortList('${category}')">
      <option>    </option>
      <option value = "Ascending">Ascending</option>
      <option value = "Descending">Descending</option>
      <option value = "Oldest">Oldest</option>
      <option value = "Newest">Newest</option>
      </select></td>
   </div>

    <hr>
</div>
    <div>
        <div>
            <label id="categoryName">${category}</label>
        </div>
        <div>
            <#list bookmarks as data>
                <table class="linkTable" style="display: inline-block;">
                    <tr>
                        <td><a href = "http://${data.bookmark}">${data.bookmark}</a></td> 
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
    
</body>

</html>