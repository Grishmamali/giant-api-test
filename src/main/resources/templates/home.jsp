<html>
<head><title>Home Page</title></head>
<body>
<h1>Hello !</h1>
<p>Welcome to <span th:text="${appName}">Our App</span></p>
     <h3>Welcome,Find Your Game Here....</h3>
     
     <form  method="GET" 
          action="/search">
  <label for="fname">Search Game:</label>
  <input type="text" id="search" name="search"><br><br>
  <input type="submit" value="Submit">
  
</form>
    </body>
</html>