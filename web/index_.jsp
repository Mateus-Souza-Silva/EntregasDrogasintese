<%-- 
    Document   : index
    Created on : 09/02/2022, 20:16:33
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Entregas DrogaSintese</title>
    </head>
    <body>
        <h1>Menu</h1>
        <a href="Farmaceutico/cadastrar.jsp">Farmaceutico</a><br/>
        <a href="ListarCidade">Cliente</a><br/>
        <a href="Cidade/cadastrar.jsp">Cidade</a><br/>
        <a href="Entregador/cadastrar.jsp">Entregador</a><br/>
        <a href="Pagamento/cadastrar.jsp">Pagamento</a><br/>
        <a href="Situacao/cadastrar.jsp">Situacao</a><br/>
        <a href="${pageContext.request.contextPath}\DadosEntrega">Entrega</a><br/>
        <a href="TipoPagamento/cadastrar.jsp">Tipo Pagamento</a><br/>
        <a href="Setor/cadastrar.jsp">Setor</a><br/>
        <a href="DadosCobranca">Cobranca</a><br/>
    </body>
</html>
