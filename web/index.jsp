<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Layout/sidebar.jsp"/>
<section>
    <div class="container-fluid">
        <div class="row row-cols-1 row-cols-md-3 p-3">
            <div class="col">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title text-center">Entregas Cadastradas</h5>
                        <p class="card-text text-center p-1">
                            <c:forEach items="${entrega}" var="entrega">
                                ${entrega.qtdFinalizada}
                            </c:forEach>
                        </p>
                    </div>
                    <button type="button" class="btn btn-light botao-card">
                        <div>
                            Ver Relatório
                        </div>
                    </button>
                </div>
            </div>
            <div class="col">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title  text-center">Entregas Finalizadas</h5>
                        <p class="card-text text-center p-1">
                            <c:forEach items="${entrega}" var="entrega">
                                ${entrega.qtdCadastrada}
                            </c:forEach>
                        </p>
                    </div>
                    <button type="button" class="btn btn-light botao-card">
                        <div>
                            Ver Relatório
                        </div>
                    </button>
                </div>
            </div>
            <div class="col">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title text-center">Cobranças Recebidas</h5>
                        <p class="card-text text-center p-1">
                            <c:forEach items="${cobranca}" var="cobranca">
                                ${cobranca.qtdCobranca}
                            </c:forEach>
                        </p>
                    </div>
                    <button type="button" class="btn btn-light botao-card">
                        <div>
                            Ver Relatório
                        </div>
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>


</main>

<script src="assets/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}\css\estilo.css" rel="stylesheet" type="text/css"/>
<script src="sidebars.js" type="text/javascript"></script>
</body>
</html>
