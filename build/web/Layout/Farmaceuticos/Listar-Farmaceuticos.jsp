<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>



<section>
    <div class="container-fluid">
        <h1 class="title-h1">Listar Farmaceuticos</h1>
        <div class="row">
            <div class="table-responsive">
                <table id="datatable" class="table align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Id Farmaceutico</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Data Nascimento</th>
                            <th scope="col">Idade</th>
                            <th scope="col">Fun��o</th>
                            <th scope="col">Alterar</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${farmaceutico}" var="farmaceutico">
                        <tr>
                            <th scope="row">${farmaceutico.farmaceuticoido}</th>
                            <td>${farmaceutico.nome}</td>
                            <td><fmt:formatDate pattern="dd/MM/yyy" value="${farmaceutico.datanascimento}"/></td>
                        <td>${farmaceutico.idade}</td>
                        <td>${farmaceutico.funcao}</td>
                        <td>
                            <a type="button" href="CarregarFarmaceutico?farmaceuticoido=${farmaceutico.farmaceuticoido}" class="btn btn-primary">
                                <svg class="bi" width="16" height="16"><use xlink:href="#alterar"/></svg>
                            </a>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

</main>
<script src="${pageContext.request.contextPath}\assets\dist\js\bootstrap.bundle.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}\css\estilo.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}\sidebars.js" type="text/javascript"></script>
</body>
</html>
