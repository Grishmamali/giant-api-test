<html>
<head><title>Home Page</title></head>
<body>
<h1>Hello !</h1>
<p> <span th:text="${appName}"></span></p>
     <h3>Here's your Game Search</h3>
     <table>
   <tr th:each="result : ${root.results}">
   <td><img th:src="${result.image.icon_url}" th:alt="${result.image.icon_url}" border=3 height=100 width=100></img></td>
   <td th:text="${result.aliases}"></td>
   <td><form action="/checkout" method="GET">
   <input type="hidden" id="guid" name="guid" th:value="${result.guid}"/>
    <button type="submit">Checkout</button>
</form></td>
        </tr>
  </table>
    </body>
</html>