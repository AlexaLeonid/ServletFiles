<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Error</title>
  </head>
  <body>
    <h2>Error: ${error}</h2>
    <form action="./registration" method="get">
      <button  type="submit" name="signUp" >
      <span class="Text">Sign up</span>
      </button>
    </form>
   </body>
</html>