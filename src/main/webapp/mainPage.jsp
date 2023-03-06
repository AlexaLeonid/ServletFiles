<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main Page</title>
    <style>
       hr {
        border: none; /* Убираем границу */
        background-color: grey;
        color: grey; /* Цвет линии для IE6-7 */
        height: 1px; /* Толщина линии */
       }
      </style>
</head>
<body>
    <h4>${dateGeneration}</h4>
    <br>
    <form action="./files" method="get" >
    <button type="submit" name="path" value=${parentDirectory} >
    <span class="Text">Вверх</span>
    </button>
    </form>
    <h1>${path}
    <hr>
    <table>
        <thead>
        <tr>
            <th>Файл</th>
            <th>Размер</th>
            <th>Дата</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${files}">
            <tr>
                <td>
                    <c:if test="${item.isFile()}">
                        <form action="./files/download" method="post">
                        <button
                           type="submit"
                           name="path"
                           value="${item.getAbsolutePath()}"
                           >
                            <span class="Text">📄 ${item.getName()}</span>
                            </button>
                            </form>
                    </c:if>
                    <c:if test="${item.isDirectory()}">
                        <form action="./files" method="get">
                        <button
                            type="submit"
                            name="path"
                            value="${item.getAbsolutePath()}"
                            >
                            <span class="Text">📁 ${item.getName()}/</span>
                            </button>
                            </form>
                    </c:if>
                </td>
                <td><c:out value="${item.length()}" /> B</td>
                <td><c:out value="${dateFormat.format(item.lastModified())}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>