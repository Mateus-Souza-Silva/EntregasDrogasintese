<%-- 
    Document   : cadastrar
    Created on : 23/02/2022, 20:52:59
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Pagamento</title>
    </head>
    <body>
        <h1>Cadastrar Pagamento</h1>
        
        <form action="${pageContext.request.contextPath}\CadastrarPagamento" method="POST">
            <p>Descrição: <input type="text" name="descricao" required/></p>
            <p><input type="submit" value="Cadastrar"/></p>
        </form>
            ${mensagem}
    </body>
</html>
