<%@page import="java.util.List"%>
<%@page import="br.com.entregasdrogasintese.model.Entrega"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>

<c:choose>
    <c:when test="${pessoalogada.nivel eq 'F'}">

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

                                <%
                                    List<Entrega> entregas = (List<Entrega>) request.getAttribute("entregas");
                                    Entrega entrega = entregas.get(0);
                                    int tamanho = entrega.getTotalRegistros();
                                    System.out.println("T: " + tamanho);
                                %>

                                <c:forEach items="${entregas}" var="entrega">
                                    <tr>
                                        <th class="fundoEntrega${entrega.situacao.descricao}" scope="row">${entrega.entregaido}</th>
                                        <td class="fundoEntrega${entrega.situacao.descricao}"><fmt:formatDate pattern="dd/MM/yyy" value="${entrega.dataentrega}"/></td>
                                        <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.produtos}</td>
                                        <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.valor}</td>
                                        <td class="fundoEntrega${entrega.situacao.descricao}"><fmt:formatDate pattern="dd/MM/yyy" value="${entrega.datapagamento}"/></td>
                                        <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.pagamento.descricao}</td>
                                        <td class="fundoEntrega${entrega.situacao.descricao} corStatusEntrega${entrega.situacao.descricao}">${entrega.situacao.descricao}</td>
                                        <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.cliente.nome}</td>
                                        <td class="fundoEntrega${entrega.situacao.descricao}">${entrega.entregador.nome}</td>
                                        <td class="fundoEntrega${entrega.situacao.descricao}">
                                            <a type="button" href="${pageContext.request.contextPath}/CarregarEntrega?entregaido=${entrega.entregaido}&nivel=F" class="btn btn-dark">
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
                                <a class="page-link" href="${pageContext.request.contextPath}/ListarEntrega?pagina=<%= pagina - 1%>">Voltar</a>
                            </li>
                            <%
                                int paginas = tamanho / 10;
                                for (int i = 0; i <= paginas; i++) {
                            %>                    
                            <li class="page-item active" aria-current="page">
                                <a class="page-link" href="${pageContext.request.contextPath}/ListarEntrega?pagina=<%= i + 1%>"><%= i + 1%></a>
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
                                <a class="page-link" href="${pageContext.request.contextPath}/ListarEntrega?pagina=<%= pagina2 + 1%>">Próximo</a>
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
</c:when>
<c:otherwise>
    <c:redirect url="../../index.jsp" ></c:redirect>
</c:otherwise>

</c:choose>