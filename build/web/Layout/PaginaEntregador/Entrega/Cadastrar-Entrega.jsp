<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>

<section>
    <div class="container-fluid">
        <h1 class="title-h1">Cadastrar Entrega</h1>
        <form action="${pageContext.request.contextPath}\CadastrarEntrega?nivel=E" method="POST" class="row g-4">
            <div class="row">
                <div class="col-md-1">
                    <label for="entregaido" class="form-label">ID</label>
                    <input type="number" name="entregaido" class="form-control" value="${entrega.entregaido}" readonly>
                </div>
                <div class="col-md-2">
                    <label for="dataentrega" class="form-label text-decoration-none">Data Entrega</label>
                    <input type="date" name="dataentrega" class="form-control" required="" value="${entrega.dataentrega}" readonly=""/>
                </div>
                <div class="col-md-7">
                    <label for="produtos" class="form-label">Produtos</label>
                    <input type="text" name="produtos" class="form-control" required="" value="${entrega.produtos}" readonly=""/>
                </div>
                <div class="col-md-2">
                    <label for="valor" class="form-label">Valor</label>
                    <input type="number" name="valor" class="form-control" required="" value="${entrega.valor}">
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">  
                    <label for="recebedor" class="form-label">Recebedor</label>
                    <input type="text" name="recebedor" class="form-control" value="${entrega.recebedor}"/>
                </div>
                <div class="col-md-9">
                    <label for="observacao" class="form-label">Observação</label>
                    <textarea class="form-control" name="observacao" rows="3" >${entrega.observacao}</textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label for="entregador" class="form-label">Entregador</label>
                    <select readonly="readonly" class="form-select" aria-label="Lista de entregadores" name="entregadorido">
                        <c:forEach var="entregador" items="${entregador}">
                            <option value="${entregador.entregadorido}" ${entregador.entregadorido==entrega.entregador.entregadorido?'selected':''}>${entregador.nome}</option>
                        </c:forEach>                    
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="pagamento" class="form-label">Pagamento</label>
                    <select class="form-select" aria-label="Lista de Formas de Pagamento" name="pagamentoido">
                        <option value="">SELECIONAR</option>
                        <c:forEach var="pagamento" items="${pagamento}">
                            <option value="${pagamento.pagamentoido}" ${pagamento.pagamentoido==entrega.pagamento.pagamentoido?'selected':''}>${pagamento.descricao}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="datapagamento" class="form-label text-decoration-none">Data Pagamento</label>
                    <input type="date" name="datapagamento" class="form-control" value="${entrega.datapagamento}"/>
                </div>
                <div class="col-md-2">
                    <label for="situacao" class="form-label">Situação</label>
                    <select class="form-select" aria-label="Lista de Situação da entrega" name="situacaoido">
                        <c:forEach var="situacao" items="${situacao}">
                            <option value="${situacao.situacaoido}" ${situacao.situacaoido==entrega.situacao.situacaoido?'selected':''}>${situacao.descricao}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="cliente" class="form-label">Cliente</label>
                    <select readonly="readonly" class="form-select" aria-label="Lista de Clientes cadastrados" name="clienteido">
                        <c:forEach var="cliente" items="${cliente}">
                            <option value="${cliente.clienteido}" ${cliente.clienteido==entrega.cliente.clienteido?'selected':''}>${cliente.nome}</option>
                        </c:forEach>
                    </select>                        
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 botao-cadastrar-div">
                    <button type="submit" class="btn botao-cadastrar">Cadastrar</button>
                </div>
            </div>
        </form>
        ${mensagem}

    </div>
</section>

</main>

<script src="${pageContext.request.contextPath}\assets\dist\js\bootstrap.bundle.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}\css\estilo.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}\sidebars.js" type="text/javascript"></script>
</body>
</html>
