<%-- 
    Document   : cadastrar
    Created on : 23/02/2022, 21:10:56
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Situação</title>
    </head>
    <body>
        <h1>Cadastrar Situação</h1>
        
        <form action="${pageContext.request.contextPath}\CadastrarSituacao" method="POST">
            <p>Descrição: <input type="text" name="descricao"/></p>            
            <p><input type="submit" value="Cadastrar"/></p>
        </form>
            ${mensagem}
    </body>
</html>
