<%-- 
    Document   : cadastrar
    Created on : 22/02/2022, 20:49:15
    Author     : Mateus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Cliente</title>
    </head>
    <body>
        <h1>Cadastro Cliente</h1>
        <form action="${pageContext.request.contextPath}\CadastrarCliente" method="POST">
            <p>Bairro: <input type="text" name="bairro" required/></p>
            <p>Cep: <input type="text" name="cep" required/></p>
            <p>Cidade:
                <select name="cidadeido">
                    <c:forEach var="cidade" items="${cidade}">
                        <option value="${cidade.cidadeido}" ${cidade.cidadeido==cliente.cidade.cidadeido?'selected':''}>${cidade.nome}</option>
                    </c:forEach>
                </select>
            </p>
            <p>Complemento: <input type="text" name="complemento" required/></p>
            <!--<p>Data Cadastro: <input type="date" value="2022-02-28" readonly name="datacadastro" required/></p>-->
            <p>Data Nascimento: <input type="date" name="datanascimento" required/></p>
            <p>Idade: <input type="number" name="idade" required/></p>
            <p>Logradouro: <input type="text" name="logradouro" required/></p>
            <p>Nivel: <input type="text" name="nivel" required/></p>
            <p>Nome: <input type="text" name="nome" required/></p>
            <p>Telefone: <input type="text" name="telefone" required/></p>
            <p>Numero: <input type="text" name="numero" required/></p>
            ${mensagem}
            <p><input type="submit" value="Cadastrar"/></p>
        </form>
    </body>
</html>
