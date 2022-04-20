<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>

<section>
    <div class="container-fluid">
        <h1 class="title-h1">Cadastrar Entregadores</h1>
        <form action="${pageContext.request.contextPath}\CadastrarEntregador" method="POST" class="row g-4">
            <div class="row">
                <div class="col-md-1">
                    <label for="entregadorido" class="form-label">ID</label>
                    <input type="number" name="pessoaido" class="form-control" value="${entregador.pessoaido}" readonly>
                </div>
                <div class="col-md-7">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" class="form-control" value="${entregador.nome}" required="">
                </div>
                <div class="col-md-4">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="password" name="senha" class="form-control" value="${entregador.senha}" required="">
                </div>
            </div>
            <div class="row">                        
                <div class="col-md-2">
                    <label for="dataentrega" class="form-label text-decoration-none">Data Nascimento</label>
                    <input type="date" name="datanascimento" class="form-control" id="datanascimento" value="${entregador.datanascimento}" onblur="calculaIdade()" required=""/>
                </div>
                <div class="col-md-1">
                    <label for="idade" class="form-label">Idade</label>
                    <input type="number" name="idade" id="idade" class="form-control" value="${entregador.idade}" required=""/>
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
</div>
</main>

<script src="${pageContext.request.contextPath}\assets\dist\js\bootstrap.bundle.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}\css\estilo.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}\sidebars.js" type="text/javascript"></script>
</body>
</html>
