<%-- 
    Document   : cadastrar.jsp
    Created on : 28/02/2022, 16:24:25
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Setor</title>
    </head>
    <body>
        <h1>Cadastrar Setor</h1>
        <form action="${pageContext.request.contextPath}\CadastrarSetor" method="POST">
            <p>Descricao: <input type="text" name="descricao"/></p>
            <p><input type="submit" value="Cadastrar"/></p>
        </form>
            ${mensagem}
    </body>
</html>
