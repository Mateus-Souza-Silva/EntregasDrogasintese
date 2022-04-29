<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>



<section>
    <div class="container-fluid">
        <h1 class="title-h1">Listar Cobran�a</h1>
        <div class="row">
            <div class="table-responsive">
                <table id="datatable" class="table align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Id Cobran�a</th>
                            <th scope="col">Data Cobran�a</th>
                            <th scope="col">Data Vencimento</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Valor</th>
                            <th scope="col">NF</th>
                            <th scope="col">Observa��o</th>
                            <th scope="col">Data Pagamento</th>
                            <th scope="col">Pagamento</th>
                            <th scope="col">Setor</th>
                            <th scope="col">Tipo Pagamento</th>
                            <th scope="col">Situa��o</th>
                            <th scope="col">Alterar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cobranca}" var="cobranca">
                            <tr>
                                <th class="fundo${cobranca.situacao.descricao}" scope="row">${cobranca.cobrancaido}</th>
                                <td class="fundo${cobranca.situacao.descricao}"><fmt:formatDate pattern="dd/MM/yyy" value="${cobranca.datacobranca}"/></td>
                                <td class="fundo${cobranca.situacao.descricao}"><fmt:formatDate pattern="dd/MM/yyy" value="${cobranca.vencimento}"/></td>
                                <td class="fundo${cobranca.situacao.descricao}">${cobranca.cliente.nome}</td>
                                <td class="fundo${cobranca.situacao.descricao}">${cobranca.valor}</td>
                                <td class="fundo${cobranca.situacao.descricao}">${cobranca.nfcobranca}</td>
                                <td class="fundo${cobranca.situacao.descricao}">${cobranca.observacao}</td>
                                <td class="fundo${cobranca.situacao.descricao}"><fmt:formatDate pattern="dd/MM/yyy" value="${cobranca.datapagamento}"/></td>
                                <td class="fundo${cobranca.situacao.descricao}">${cobranca.pagamento.descricao}</td>
                                <td class="fundo${cobranca.situacao.descricao}">${cobranca.setor.descricao}</td>
                                <td class="fundo${cobranca.situacao.descricao}">${cobranca.tipopagamento.descricao}</td>
                                <td class="fundo${cobranca.situacao.descricao} corStatus${cobranca.situacao.descricao}">${cobranca.situacao.descricao}</td>
                                <td class="fundo${cobranca.situacao.descricao}">
                                    <a type="button" href="CarregarCobranca?cobrancaido=${cobranca.cobrancaido}" class="btn btn-dark">
                                        <svg class="bi" width="16" height="16"><use xlink:href="#alterar"/></svg>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <nav aria-label="...">
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <span class="page-link">Voltar</span>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item active" aria-current="page">
                        <span class="page-link">2</span>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Pr�ximo</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</section>

</main>
<script src="${pageContext.request.contextPath}\assets\dist\js\bootstrap.bundle.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}\css\estilo.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}\sidebars.js" type="text/javascript"></script>
</body>
</html>
