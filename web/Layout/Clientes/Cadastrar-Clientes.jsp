<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../sidebar.jsp"/>
<section>
    <div class="container-fluid">
        <h1 class="title-h1">Cadastrar Clientes</h1>
        <form action="${pageContext.request.contextPath}\CadastrarCliente" method="POST" class="row g-4">
            <div class="row">
                <div class="col-md-1">
                    <label for="clienteido" class="form-label">ID</label>
                    <input type="number" name="pessoaido" class="form-control" value="${cliente.pessoaido}" readonly>
                </div>
                <div class="col-md-8">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" class="form-control" value="${cliente.nome}"/>                        
                </div>
                <div class="col-md-2">
                    <label for="datanascimento" class="form-label">Data Nascimento</label>
                    <input type="date" name="datanascimento" id="datanascimento" value="${cliente.datanascimento}" onblur="calculaIdade()" class="form-control"/>                        
                </div>
                <div class="col-md-1">
                    <label for="idade" class="form-label">Idade</label>
                    <input type="number" name="idade" id="idade" value="${cliente.idade}" class="form-control"/>
                </div>
            </div>

            <div class="row">
                <div class="col-md-2">
                    <label for="cep" class="form-label">Cep</label>
                    <input type="text" name="cep" id="cep" value="${cliente.cep}" onblur="pesquisacep(this.value);" class="form-control" required=""/>
                </div>
                <div class="col-md-4">
                    <label for="bairro" class="form-label text-decoration-none">Bairro</label>
                    <input type="text" id="bairro" name="bairro" value="${cliente.bairro}" class="form-control" required=""/>
                </div>
                <div class="col-md-5">
                    <label for="logradouro" class="form-label">Logradouro</label>
                    <input type="text" id="rua" name="logradouro" value="${cliente.logradouro}" class="form-control"/>                        
                </div>
                <div class="col-md-1">
                    <label for="numero" class="form-label">Numero</label>
                    <input type="text" name="numero" class="form-control" value="${cliente.numero}"/>                        
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <label for="cidade" class="form-label">Cidade</label>
                    <select class="form-select" aria-label="Lista de cidades" name="cidadeido">
                        <c:forEach var="cidade" items="${cidade}">
                            <option value="${cidade.cidadeido}"${cidade.cidadeido==cliente.cidade.cidadeido?'selected':''}>${cidade.nome}</option>
                        </c:forEach>                   
                    </select>
                </div>
                <div class="col-md-7">
                    <label for="complemento" class="form-label">Complemento</label>
                    <input type="text" name="complemento" class="form-control" value="${cliente.complemento}">
                </div>                        

                <div class="col-md-2">
                    <label for="telefone" class="form-label">Telefone</label>
                    <input type="tel" name="telefone" class="form-control" value="${cliente.telefone}"/>                        
                </div>
            </div>
            <div class="row">
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
