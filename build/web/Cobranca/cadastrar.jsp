<%-- 
    Document   : cadastrar
    Created on : 28/02/2022, 17:31:28
    Author     : Mateus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Cobrança</title>
    </head>
    <body>
        <h1>Cadastrar Cobrança</h1>
        <form action="${pageContext.request.contextPath}\CadastrarCobranca" method="POST">
            <p>Data Cobrança: <input type="date" name="datacobranca"/></p>
            <p>Nf Cobrança: <input type="text" name="nfcobranca"/></p>
            <p>Cliente: 
                <select name="clienteido">
                    <c:forEach var="cliente" items="${cliente}">
                        <option value="${cliente.clienteido}" ${cliente.clienteido==cobranca.cliente.clienteido?'selected':''}>${cliente.nome}</option>
                    </c:forEach>
                </select>
            </p>
            <p>Valor: <input type="number" name="valor"/></p>
            <p>Vencimento: <input type="date" name="vencimento"/></p>
            <p>Setor:
                <select name="setorido">
                    <c:forEach var="setor" items="${setor}">
                        <option value="${setor.setorido}" ${setor.setorido==cobranca.setor.setorido?'selected':''}>${setor.descricao}</option>
                    </c:forEach>
                </select>
            </p>
            <p>Observacao: <input type="text" name="observacao"/></p>
            <p>Pagamento:
                <select name="pagamentoido">
                    <c:forEach var="pagamento" items="${pagamento}">
                        <option value="${pagamento.pagamentoido}" ${pagamento.pagamentoido==cobranca.pagamento.pagamentoido?'selected':''}>${pagamento.descricao}</option>
                    </c:forEach>
                </select>
            </p>
            <p>Situação:
                <select name="situacaoido">
                    <c:forEach var="situacao" items="${situacao}">
                        <option value="${situacao.situacaoido}" ${situacao.situacaoido==cobranca.situacao.situacaoido?'selected':''}>${situacao.descricao}</option>
                    </c:forEach>
                </select>
            </p>
            <p>Data Pagamento: <input type="date" name="datapagamento"/></p>
            <p>Tipo Pagamento:
                <select name="tipopagamentoido">
                    <c:forEach var="tipopagamento" items="${tipopagamento}">
                        <option value="${tipopagamento.tipopagamentoido}" ${tipopagamento.tipopagamentoido==cobranca.tipopagamento.tipopagamentoido?'selected':''}>${tipopagamento.descricao}</option>
                    </c:forEach>
                </select>
            </p>
            <p><input type="submit" value="Cadastrar"/></p>
        </form>
        ${mensagem}
    </body>
</html>
