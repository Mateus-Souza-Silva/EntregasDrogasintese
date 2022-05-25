<%-- 
    Document   : login
    Created on : 25/03/2022, 21:45:08
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}\Imagens\logo-Drogasintese.ico">
        <title>Entregas DrogaSíntese</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <ul class="nav justify-content-center bg-dark">
            <div class="text-white bg-dark p-3">
                <svg class="bi me-2" width="0" height="0"><img height="40" src="${pageContext.request.contextPath}\Imagens\logo-Drogasintese.png"/></svg>
                <span class="fs-4">Entregas DrogaSintese</span>
                </a>
            </div>
        </ul>
        <div class="container-fluid col-md-3 align-items-center">
            <h1 class="text-center">Login</h1>
            <br>
            <form class="row g-3" action="${pageContext.request.contextPath}\Login" method="post">
                <input type="hidden" name="acao" value="logar"/>
                <div class="row">
                    <div class="col-md-12">
                        <label for="inputEmail4" class="form-label">Nome</label>
                        <input type="text" name="nome" class="form-control" autofocus required>
                    </div>
                </div>
                <div class="row">                    
                    <div class="col-md-12">
                        <label for="inputPassword4" class="form-label">Senha</label>
                        <input type="text" name="senha" class="form-control" required id="inputPassword4">
                    </div>
                </div>
                <div class="row align-items-center">
                    <div class="col-md-12 align-items-center">
                        ${mensagem}                        
                    </div>
                </div>
                <div class="row">
                    <input type="submit" class="btn botao-cadastrar" value="Entrar"/>                
                </div>
            </form>
        </div>
    </div>
</body>
</html>
