<%-- 
    Document   : cadastrar
    Created on : 23/02/2022, 21:57:36
    Author     : Mateus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Entrega</title>
    </head>
    <body>
        <h1>Cadastrar Entrega</h1>
        <form action="${pageContext.request.contextPath}\CadastrarEntrega" method="POST">
            <p>Data Entrega: <input type="date" name="dataentrega"/></p>
            <p>Produtos: <input type="text" name="produtos"/></p>
            <p>Valor: <input type="number" name="valor"/></p>
            <p>Recebedor: <input type="text" name="recebedor"/></p>
            <p>Observação: <input type="text" name="observacao"/></p>
            <p>Entregador:
                <select name="entregadorido">
                    <c:forEach var="entregador" items="${entregador}">
                        <option value="${entregador.entregadorido}" ${entregador.entregadorido==entrega.entregador.entregadorido?'selected':''}>${entregador.nome}</option>
                    </c:forEach>                    
                </select>
            </p>
            <p>Pagamento:
                <select name="pagamentoido">
                    <c:forEach var="pagamento" items="${pagamento}">
                        <option value="${pagamento.pagamentoido}" ${pagamento.pagamentoido==entrega.pagamento.pagamentoido?'selected':''}>${pagamento.descricao}</option>
                    </c:forEach>
                </select>
            </p>
            <p>Situação:
                <select name="situacaoido">
                    <c:forEach var="situacao" items="${situacao}">
                        <option value="${situacao.situacaoido}" ${situacao.situacaoido==entrega.situacao.situacaoido?'selected':''}>${situacao.descricao}</option>
                    </c:forEach>
                </select>
            </p>
            <p>Cliente: 
                <select name="clienteido">
                    <c:forEach var="cliente" items="${cliente}">
                        <option value="${cliente.clienteido}" ${cliente.clienteido==entrega.cliente.clienteido?'selected':''}>${cliente.nome}</option>
                    </c:forEach>
                </select>
            </p>
            <p><input type="submit" value="Cadastrar"/></p>
        </form>
            ${mensagem}
    </body>
</html>
