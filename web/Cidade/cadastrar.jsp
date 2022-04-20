<%-- 
    Document   : cadastrar
    Created on : 22/02/2022, 21:23:01
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Cidade</title>
    </head>
    <body>
        <h1>Cadastrar Cidade</h1>
        
        <form action="${pageContext.request.contextPath}\CadastrarCidade" method="POST">
            <p>Cidade: <input type="text" name="nome" required/></p>
            <p><input type="submit" value="Cadastrar"/></p>
        </form>
            ${mensagem}
    </body>
</html>
