<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Страница авторизации</title>
  </head>
  <body>
    <h2>Страница авторизации</h2>
    <form action="./authorization" method="post" >
       <p><b>Логин:</b><br>
           <input type="text" name="login" size="30"></p>
       <p><b>Пароль:</b><br>
           <input type="text" name="password" size="30"></p>
       <button type="submit" name="commit" >
           <span class="Text">Log in</span>
       </button>
    </form>
    <form action="./registration" method="get" >
           <button type="submit" name="commit" >
               <span class="Text">Sign up</span>
           </button>
        </form>
   </body>
</html>