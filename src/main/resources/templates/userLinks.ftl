<html>

<head>
<style>
header {
    background-color:#09F;
    color:white;
    text-align:center;
    padding:5px;	 
}
nav {
    line-height:30px;
    background-color:#eeeeee;
    height:100%;
    width:6%;
    float:left;
    padding:5px;
}
section {
    width:80%;
    float:left;
    padding:50px;
}
footer {
    background-color:#09F;
    color:white;
    clear:both;
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
<button id="headerbutton" onclick = "goToHome()">Back To Home</button>
<h1>${category} Bookmark Page</h1>
 
</header>

<nav>
Sort By: <br>
<a href = "${category}/Ascending" target = "_self">Ascending</a>
<a href = "${category}/Descending" target = "_self">Descending</a>
<a href = "${category}/Oldest" target = "_self">Oldest</a>
<a href = "${category}/Newest" target = "_self">Newest</a>
</nav>


<section>
   <div>

        <div class="centered">
            <label>Add URL </label>
            <table border="1" align = "center">
                <tr>
                    <td>URL</td>                     
                    <td>Add</td>
                </tr>                
                <tr> 
                    <td><input type="text" id="input_bookmark"></td> 
                    <td><button onclick="addBookmark('${category}')">Add</button></td>
                </tr>
            </table>
        </div>

        
        <hr>
        
        <div class="centered" class="largeLable">
            <label id="categoryName">${category}</label>
        </div>
        <div>
            <#list bookmarks as data>
                    <table class="linkTable" style="display: inline-block;">
                    <tr>
                        <td><a id="categoryLink" href = "http://${data.bookmark}">${data.bookmark}</a></td> 
                    </tr>
                    <tr>
                        <td colspan = '3'><button id = 'sexybuttons' style="background-color: palevioletred" button onclick ="deleteLink('${data.bookmark}')">Remove</button></td> 
                    </tr>
                </table>
            </#list>
        </div>

        <hr>

    </div>



</section>


</body>
</html>
