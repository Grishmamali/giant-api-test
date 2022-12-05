<html>
<head>
<title>Checkout</title>
</head>
<body>
	<h1>Checkout !</h1>
	<p>
		Welcome to <span th:text="${appName}">Our App</span>
	</p>
	<h3>Game:</h3>
	<table>
		<tr>
			<td th:text="${result.name}"></td>
		</tr>
		<tr>
		<td>${result.genres.name}</td>
		</tr>
		
	</table>
</body>
</html>