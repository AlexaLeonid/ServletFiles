<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Страница регистрации</title>
  </head>
  <body>
    <h2>Страница регистрации</h2>
    <form action="./registration" method="post" >
     <p><b>Логин:</b><br>
        <input type="text" name="login" size="30"></p>
     <p><b>Пароль:</b><br>
         <input type="text" name="password" size="30"></p>
     <p><b>Email:</b><br>
         <input type="text" name="email" size="30"></p>
       <button type="submit" name="commit" >
          <span class="Text">Sign up</span>
       </button>
    </form>
   </body>
</html>