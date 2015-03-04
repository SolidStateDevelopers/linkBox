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
			<label>Bookmark List</label>
			<table border="1">
				<tr>
					<td>Category</td>
					<td>URL</td>
				</tr>
				<#list bookmarks as data>
				<tr>
					<#if data.public>
						<td>${data.category}</td>
						<td>${data.bookmark}</td>
					</if>
				</tr>
				</#list>
			</table>
		</div>
		<hr>
	</div>
	</div>
</body>
</html>