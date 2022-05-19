<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>

<section>
    <div class="container-fluid">
        <h1 class="title-h1">Cadastrar Cobrança</h1>
        <form action="${pageContext.request.contextPath}\CadastrarCobranca" method="POST" class="row g-4">
            <div class="row">
                <div class="col-md-1"> 
                    <label for="cobrancaido" class="form-label">ID</label>
                    <input type="number" name="cobrancaido" class="form-control" value="${cobranca.cobrancaido}" readonly>
                </div>
                <div class="col-md-2">
                    <label for="datacobranca" class="form-label">Data Cobrança</label>
                    <input type="date" name="datacobranca" class="form-control" value="${cobranca.datacobranca}" required=""/>
                </div>
                <div class="col-md-2">
                    <label for="vencimento" class="form-label">Data Vencimento</label>
                    <input type="date" name="vencimento" id="vencimento" required="" value="${cobranca.vencimento}" class="form-control"/>                        
                </div>
                <div class="col-md-5">
                    <label for="cliente" class="form-label">Cliente</label>
                    <select class="form-select" aria-label="Lista de clientes" name="clienteido">
                        <c:forEach var="cliente" items="${cliente}">
                            <option value="${cliente.clienteido}" ${cliente.clienteido==cobranca.cliente.clienteido?'selected':''}>${cliente.nome}</option>
                        </c:forEach>                   
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="valor" class="form-label">Valor</label>
                    <input type="number" name="valor" step="0.01" class="form-control" value="${cobranca.valor}"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <label for="nfcobranca" class="form-label">NF Cobrança</label>
                    <input type="text" name="nfcobranca" class="form-control" value="${cobranca.nfcobranca}"/>                        
                </div>                        
                <div class="col-md-9">
                    <label for="observacao" class="form-label">Observação</label>
                    <textarea class="form-control" name="observacao" rows="3">${cobranca.observacao}</textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label for="datapagamento" class="form-label">Data Pagamento</label>
                    <input type="date" name="datapagamento" class="form-control" value="${cobranca.datapagamento}"/>
                </div>                        
                <div class="col-md-2">
                    <label for="pagamento" class="form-label">Pagamento</label>
                    <select class="form-select" aria-label="Lista de Pagamento" name="pagamentoido">
                        <option value="">SELECIONAR</option>
                        <c:forEach var="pagamento" items="${pagamento}">
                            <option value="${pagamento.pagamentoido}" ${pagamento.pagamentoido==cobranca.pagamento.pagamentoido?'selected':''}>${pagamento.descricao}</option>
                        </c:forEach>                   
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="setor" class="form-label">Setor</label>
                    <select class="form-select" aria-label="Lista de setor" name="setorido">
                        <c:forEach var="setor" items="${setor}">
                            <option value="${setor.setorido}" ${setor.setorido==cobranca.setor.setorido?'selected':''}>${setor.descricao}</option>
                        </c:forEach>                   
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="tipopagamento" class="form-label">Tipo Pagamento</label>
                    <select class="form-select" aria-label="Lista de Tipo Pagamento" name="tipopagamentoido">
                        <option value="">SELECIONAR</option>
                        <c:forEach var="tipopagamento" items="${tipopagamento}">
                            <option value="${tipopagamento.tipopagamentoido}" ${tipopagamento.tipopagamentoido==cobranca.tipopagamento.tipopagamentoido?'selected':''}>${tipopagamento.descricao}</option>
                        </c:forEach>                   
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="situacao" class="form-label">Situação</label>
                    <select class="form-select" aria-label="Lista de Situacoes" name="situacaoido">
                        <c:forEach var="situacao" items="${situacao}">
                            <option value="${situacao.situacaoido}" ${situacao.situacaoido==cobranca.situacao.situacaoido?'selected':''}>${situacao.descricao}</option>
                        </c:forEach>                   
                    </select>
                </div>
                <div class="row">
                    <div class="col-md-12 botao-cadastrar-div">
                        <button type="submit" class="btn botao-cadastrar">Cadastrar</button>
                    </div>
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
