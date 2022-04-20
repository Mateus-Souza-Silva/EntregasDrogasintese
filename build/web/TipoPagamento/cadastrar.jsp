<%-- 
    Document   : cadastrar
    Created on : 28/02/2022, 16:16:23
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Tipo Pagamento</title>
    </head>
    <body>
        <h1>Cadastrar Tipo Pagamento</h1>
        <form action="${pageContext.request.contextPath}\CadastrarTipoPagamento" method="POST">
            <p>Descrição: <input type="text" name="descricao"/></p>
            <p><input type="submit" value="Cadastrar"/></p>
        </form>
            ${mensagem}
    </body>
</html>
