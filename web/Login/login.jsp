<%-- 
    Document   : login
    Created on : 09/03/2022, 19:12:22
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
    <center>
        <h1>Login</h1>        
        <form action="${pageContext.request.contextPath}/login" method="POST">
            Login <input type="text" name="nome"/><br/>
            Senha <input type="password" name="senha"/><br/>
            <input type="submit" value="Entrar"/>
        </form>
    </center>
    </body>
</html>
