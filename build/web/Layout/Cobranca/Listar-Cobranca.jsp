<%@page import="br.com.entregasdrogasintese.model.Cobranca"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>



<section>
    <div class="container-fluid">
        <h1 class="title-h1">Listar Cobrança</h1>
        <div class="row">
            <div class="table-responsive">
                <table id="datatable" class="table align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Id Cobrança</th>
                            <th scope="col">Data Cobrança</th>
                            <th scope="col">Data Vencimento</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Data Pagamento</th>
                            <th scope="col">Pagamento</th>
                            <th scope="col">Setor</th>
                            <th scope="col">Tipo Pagamento</th>
                            <th scope="col">Situação</th>
                            <th scope="col">Alterar</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            List<Cobranca> cobrancas = (List<Cobranca>) request.getAttribute("cobrancas");
                            Cobranca cobranca = cobrancas.get(0);
                            int tamanho = cobranca.getTotalRegistros();
                            System.out.println("T: " + tamanho);
                        %>

                        <c:forEach items="${cobrancas}" var="cobranca">
                            <tr>
                                <th class="fundo${cobranca.situacao.descricao}" scope="row">${cobranca.cobrancaido}</th>
                                <td class="fundo${cobranca.situacao.descricao}"><fmt:formatDate pattern="dd/MM/yyy" value="${cobranca.datacobranca}"/></td>
                                <td class="fundo${cobranca.situacao.descricao}"><fmt:formatDate pattern="dd/MM/yyy" value="${cobranca.vencimento}"/></td>
                                <td class="fundo${cobranca.situacao.descricao}">${cobranca.cliente.nome}</td>
                                <td class="fundo${cobranca.situacao.descricao}">${cobranca.valor}</td>
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
            <%
                int ultimaPagina = 0;
                int pagina = (Integer) request.getAttribute("pagina");
                int pagina2 = pagina;
                if (pagina == 1) {
                    pagina = pagina + 1;
                }
            %>
            <nav aria-label="...">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/ListarCobranca?pagina=<%= pagina - 1%>">Voltar</a>
                    </li>
                    <%
                        int paginas = tamanho / 10;
                        for (int i = 0; i <= paginas; i++) {
                    %>                    
                    <li class="page-item active" aria-current="page">
                        <a class="page-link" href="${pageContext.request.contextPath}/ListarCobranca?pagina=<%= i + 1%>"><%= i + 1%></a>
                    </li>
                    <% ultimaPagina++;
                        } %>                    
                    <%
                        if (pagina == ultimaPagina) {
                            System.out.println("entrou");
                            pagina = pagina - 1;
                            pagina2 = pagina2 - 1;
                        }
                    %>
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/ListarCobranca?pagina=<%= pagina2 + 2%>">Próximo</a>
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
