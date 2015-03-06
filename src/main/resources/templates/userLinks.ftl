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
    
   <div>
      <td><select id="Sort By" onchange = "sortList()">
      <option value = "0">    </option>
      <option value = "1">Ascending</option>
      <option value = "2">Descending</option>
      <option value = "3">Oldest</option>
      <option value = "4"> Newest</option>
      </select></td>
   </div>

    <hr>
</div>
    <div>
        <div>
            <label>${category}</label>
        <div>
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
    </div>
    
</body>

</html>