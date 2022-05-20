<%@page import="br.com.entregasdrogasintese.model.Cliente"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>



<section>
    <div class="container-fluid">
        <h1 class="title-h1">Listar Clientes</h1>
        <div class="row">
            <div class="table-responsive">
                <table id="datatable" class="table align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Id Cliente</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Idade</th>
                            <th scope="col">Cep</th>
                            <th scope="col">Bairro</th>
                            <th scope="col">Logradouro</th>
                            <th scope="col">Número</th>
                            <th scope="col">Cidade</th>
                            <th scope="col">Telefone</th>
                            <th scope="col">Alterar</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <%
                            List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                            Cliente cliente = clientes.get(0);
                            int tamanho = cliente.getTotalRegistros();
                            System.out.println("T: " + tamanho);
                        %>
                        
                        <c:forEach items="${clientes}" var="cliente">
                            <tr>
                                <th scope="row">${cliente.clienteido}</th>
                                <td>${cliente.nome}</td>
                                <td>${cliente.idade}</td>
                                <td>${cliente.cep}</td>
                                <td>${cliente.bairro}</td>
                                <td>${cliente.logradouro}</td>
                                <td>${cliente.numero}</td>
                                <td>${cliente.cidade.nome}</td>
                                <td>${cliente.telefone}</td>
                                <td>
                                    <a type="button" href="CarregarCliente?clienteido=${cliente.clienteido}" class="btn btn-primary">
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
                        <a class="page-link" href="${pageContext.request.contextPath}/ListarCliente?pagina=<%= pagina - 1%>">Voltar</a>
                    </li>
                    <%
                        int paginas = tamanho / 10;
                        for (int i = 0; i <= paginas; i++) {
                    %>                    
                    <li class="page-item active" aria-current="page">
                        <a class="page-link" href="${pageContext.request.contextPath}/ListarCliente?pagina=<%= i + 1%>"><%= i + 1%></a>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/ListarCliente?pagina=<%= pagina2 + 1%>">Próximo</a>
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
