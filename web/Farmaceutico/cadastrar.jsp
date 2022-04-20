<%-- 
    Document   : cadastrar
    Created on : 16/02/2022, 22:02:05
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Farmaceutico</title>
    </head>
    <body>
        <h1>Cadastrar Farmaceutico</h1>
        <form action="${pageContext.request.contextPath}\CadastrarFarmaceutico" method="POST">
            <p>Data Nascimento: <input type="date" name="datanascimento" required/></p>
            <p>Funcao: <input type="text" name="funcao" required/></p>
            <p>Idade: <input type="number" name="idade" required/></p>
            <p>Nivel: <input type="text" name="nivel" required/></p>
            <p>Nome: <input type="text" name="nome" required/></p>
            <p>Senha: <input type="password" name="senha" required/></p>
            <p><input type="submit" value="Cadastrar"/></p>
        </form>
            ${mensagem}
    </body>
</html>
