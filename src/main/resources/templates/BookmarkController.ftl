<html>


<head>
<style>
header {
    background-color: #cee3f8;
    color:white;
    text-align:center;
    padding:5px;	 
}

</style>
    <title>Link saver page</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
    <link href="/css/accountStyle.css" rel="stylesheet" type="text/css" />
</head>

<body onload="dragEvents()">    
    <div id = "target">
       
<header>
<h1>${userId} Home Page</h1>
</header>

<hr>

        <div>

            <div>

               <div>
                  <label>Bookmark Lists</label>
               </div>
                <#list bookmarks as data>
                    <table class="categoryTable" style="display: inline-block;">
                        <tr>
                            <td><a id='categoryLink' href="/cs480/BookmarkController/${data.id}/${data.category}">${data.category}</a></td>
                            <td></td> 
                        </tr>
                        <tr>
                            <td colspan = '2'><button onclick="addBookmark('${data.category}')">Add Link</button></td>
                            <td colspan = '3'><button style="background-color: palevioletred" onclick ="deleteCategory('${data.category}')">Remove Folder</button></td> 
                        </tr>
                    </table>
                </#list> 
            </div>
<hr> 

            <div>
                <label>Add Category and URL</label>
                <table border="1" align = "center">
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