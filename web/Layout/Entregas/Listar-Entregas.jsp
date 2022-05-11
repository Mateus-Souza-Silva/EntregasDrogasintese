<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>



<section>
    <div class="container-fluid">
        <h1 class="title-h1">Listar Entregas</h1>
        <div class="row">
            <div class="table-responsive">
                <table id="datatable" class="table align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Id Entrega</th>
                            <th scope="col">Data Entrega</th>
                            <th scope="col">Produtos</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Data Pagamento</th>
                            <th scope="col">Pagamento</th>
                            <th scope="col">Situacao</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Entregador</th>
                            <th scope="col">Alterar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${entrega}" var="entrega">
                            <tr>
                                <th class="fundoEntrega${entrega.situacao.descricao}" scope="row">${entrega.entregaido}</th>
                                <td class="fundoEntrega${entrega.situacao.descricao}"><fmt:formatDate pattern="dd/MM/yyy" value="${entrega.dataentrega}"/></td>
                                <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.produtos}</td>
                                <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.valor}</td>
                                <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.datapagamento}</td>
                                <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.pagamento.descricao}</td>
                                <td class="fundoEntrega${entrega.situacao.descricao} corStatusEntrega${entrega.situacao.descricao}">${entrega.situacao.descricao}</td>
                                <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.cliente.nome}</td>
                                <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.entregador.nome}</td>
                                <td class="fundoEntrega${entrega.situacao.descricao}">
                                    <a type="button" href="CarregarEntrega?entregaido=${entrega.entregaido}" class="btn btn-dark">
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
                        <a class="page-link" href="#">Próximo</a>
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
