<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}\Imagens\logo-Drogasintese.png">
        <title>DrogaSintese</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}\assets\dist\css\bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}\css\estilo.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}\css\sidebar.css" rel="stylesheet" />
        <!--<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet" />-->

        <script src="${pageContext.request.contextPath}\css\estilo.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!--<script src="https://code.jquery.com/jquery-3.3.1.js"></script>-->
        <!--<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>-->
        <script src="https://getbootstrap.com/docs/5.0/examples/sidebars/"></script>
        <!-- Core theme JS-->
        <script src="${pageContext.request.contextPath}\css\sidebar.js"></script>
    </head>

    <script>
        function calculaIdade() {
            var datanascimento = document.getElementById("datanascimento").value;

            var dataNasc = datanascimento;
            var dataAtual = new Date();

            var anoAtual = dataAtual.getFullYear();

            var anoNascParts = dataNasc.split('-');
            var anoNasc = anoNascParts[0];
            var mesNasc = anoNascParts[1];
            var diaNasc = anoNascParts[2];

            var idade = anoAtual - anoNasc;

            var mesAtual = dataAtual.getMonth() + 1;

            //Se mes atual for menor que o nascimento, nao fez aniversario ainda;  

            if (mesAtual < mesNasc) {

                idade--;

            } else {

                //Se estiver no mes do nascimento, verificar o dia

                if (mesAtual == mesNasc) {

                    if (new Date().getDate() < diaNasc) {

                        //Se a data atual for menor que o dia de nascimento ele ainda nao fez aniversario

                        idade--;

                    }

                }

            }

            document.getElementById('idade').value = idade;

            // return idade;
            console.log(idade);
        }

        function limpa_formulário_cep() {
            //Limpa valores do formulário de cep.
            document.getElementById('rua').value = ("");
            document.getElementById('bairro').value = ("");
        }

        function meu_callback(conteudo) {
            if (!("erro" in conteudo)) {
                //Atualiza os campos com os valores.
                document.getElementById('rua').value = (conteudo.logradouro);
                document.getElementById('bairro').value = (conteudo.bairro);
            } //end if.
            else {
                //CEP não Encontrado.
                limpa_formulário_cep();
                alert("CEP não encontrado.");
            }
        }

        function pesquisacep(valor) {

            //Nova variável "cep" somente com dígitos.
            var cep = valor.replace(/\D/g, '');

            //Verifica se campo cep possui valor informado.
            if (cep != "") {

                //Expressão regular para validar o CEP.
                var validacep = /^[0-9]{8}$/;

                //Valida o formato do CEP.
                if (validacep.test(cep)) {

                    //Preenche os campos com "..." enquanto consulta webservice.
                    document.getElementById('rua').value = "...";
                    document.getElementById('bairro').value = "...";

                    //Cria um elemento javascript.
                    var script = document.createElement('script');

                    //Sincroniza com o callback.
                    script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';

                    //Insere script no documento e carrega o conteúdo.
                    document.body.appendChild(script);

                } //end if.
                else {
                    //cep é inválido.
                    limpa_formulário_cep();
                    alert("Formato de CEP inválido.");
                }
            } //end if.
            else {
                //cep sem valor, limpa formulário.
                limpa_formulário_cep();
            }
        }
        ;

    </script>

    <body>

        <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="home" viewBox="0 0 16 16">
        <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"/>
    </symbol>

    <symbol id="table" viewBox="0 0 16 16">
        <path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm15 2h-4v3h4V4zm0 4h-4v3h4V8zm0 4h-4v3h3a1 1 0 0 0 1-1v-2zm-5 3v-3H6v3h4zm-5 0v-3H1v2a1 1 0 0 0 1 1h3zm-4-4h4V8H1v3zm0-4h4V4H1v3zm5-3v3h4V4H6zm4 4H6v3h4V8z"/>
    </symbol>

    <symbol id="people-circle" viewBox="0 0 16 16">
        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
    </symbol>

    <symbol id="grid" viewBox="0 0 16 16">
        <path d="M1 2.5A1.5 1.5 0 0 1 2.5 1h3A1.5 1.5 0 0 1 7 2.5v3A1.5 1.5 0 0 1 5.5 7h-3A1.5 1.5 0 0 1 1 5.5v-3zM2.5 2a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 1h3A1.5 1.5 0 0 1 15 2.5v3A1.5 1.5 0 0 1 13.5 7h-3A1.5 1.5 0 0 1 9 5.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zM1 10.5A1.5 1.5 0 0 1 2.5 9h3A1.5 1.5 0 0 1 7 10.5v3A1.5 1.5 0 0 1 5.5 15h-3A1.5 1.5 0 0 1 1 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 9h3a1.5 1.5 0 0 1 1.5 1.5v3a1.5 1.5 0 0 1-1.5 1.5h-3A1.5 1.5 0 0 1 9 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3z"/>
    </symbol>

    <symbol id="entrega" viewBox="0 0 16 16">
        <path d="M0 3.5A1.5 1.5 0 0 1 1.5 2h9A1.5 1.5 0 0 1 12 3.5V5h1.02a1.5 1.5 0 0 1 1.17.563l1.481 1.85a1.5 1.5 0 0 1 .329.938V10.5a1.5 1.5 0 0 1-1.5 1.5H14a2 2 0 1 1-4 0H5a2 2 0 1 1-3.998-.085A1.5 1.5 0 0 1 0 10.5v-7zm1.294 7.456A1.999 1.999 0 0 1 4.732 11h5.536a2.01 2.01 0 0 1 .732-.732V3.5a.5.5 0 0 0-.5-.5h-9a.5.5 0 0 0-.5.5v7a.5.5 0 0 0 .294.456zM12 10a2 2 0 0 1 1.732 1h.768a.5.5 0 0 0 .5-.5V8.35a.5.5 0 0 0-.11-.312l-1.48-1.85A.5.5 0 0 0 13.02 6H12v4zm-9 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm9 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/>
    </symbol>

    <symbol id="cliente" viewBox="0 0 16 16">
        <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
        <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
    </symbol>

    <symbol id="cobranca" viewBox="0 0 16 16">
        <path d="M12 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h8zM4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4z"/>
        <path d="M4 2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5v-2zm0 4a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1zm0 3a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1zm0 3a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1zm3-6a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1zm0 3a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1zm0 3a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1zm3-6a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1zm0 3a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-4z"/>
    </symbol>
    <symbol id="entregador" viewBox="0 0 16 16">      
        <path fill-rule="evenodd" d="M10.854 8.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L7.5 10.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
        <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"/>
    </symbol>
    <symbol id="relatorio" viewBox="0 0 16 16">
        <path d="M11 2a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v12h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1v-3a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3h1V7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7h1V2zm1 12h2V2h-2v12zm-3 0V7H7v7h2zm-5 0v-3H2v3h2z"/>
    </symbol>
    <symbol id="sair" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0v2z"/>
        <path fill-rule="evenodd" d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"/>
    </symbol>
    <symbol id="listar" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
    </symbol>
    <symbol id="cadastrar" viewBox="0 0 16 16">
        <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
    </symbol>
    <symbol id="excluir" viewBox="0 0 16 16">
        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
    </symbol>
    <symbol id="alterar" viewBox="0 0 16 16">
        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
    </symbol>

    </svg>
    <main>
        <div style="width: 100%" class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="border-end bg-dark text-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom text-white bg-dark">
                    <a class="text-white bg-dark" href="${pageContext.request.contextPath}\DadosFarmaceuticoLogado">
                        <svg class="bi me-2" width="0" height="0"><img height="30" src="${pageContext.request.contextPath}\Imagens\logo-Drogasintese.png"/></svg>
                        <span class="fs-4">DrogaSintese</span>
                    </a>
                </div>
                <div class="list-group list-group-flush">
                    <div class="accordion accordion-flush bg-dark" id="accordionFlushExample">

                        <div class="accordion-item bg-dark text-white">
                            <h2 class="accordion-header text-white" id="flush-headingSix">
                                <button class="accordion-button collapsed text-white bg-dark" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseSix" aria-expanded="false" aria-controls="flush-collapseSix">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#entrega"/></svg>
                                    Entregas
                                </button>
                            </h2>
                            <div id="flush-collapseSix" class="accordion-collapse text-white collapse" aria-labelledby="flush-headingSix" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\DadosEntrega">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#cadastrar"/></svg>
                                        Cadastrar
                                    </a>
                                </div>
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\ListarEntrega?pagina=1&nivel=F">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#listar"/></svg>
                                        Listar
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="accordion-item bg-dark text-white">
                            <h2 class="accordion-header text-white" id="flush-headingFive">
                                <button class="accordion-button collapsed text-white bg-dark" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFive" aria-expanded="false" aria-controls="flush-collapseFive">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#entregador"/></svg>
                                    Entregadores
                                </button>
                            </h2>
                            <div id="flush-collapseFive" class="accordion-collapse text-white collapse" aria-labelledby="flush-headingFive" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\Layout\Entregadores\Cadastrar-Entregadores.jsp">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#cadastrar"/></svg>
                                        Cadastrar
                                    </a>
                                </div>
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\ListarEntregador">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#listar"/></svg>
                                        Listar
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="accordion-item bg-dark text-white">
                            <h2 class="accordion-header text-white" id="flush-headingFour">
                                <button class="accordion-button collapsed text-white bg-dark" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFour" aria-expanded="false" aria-controls="flush-collapseFour">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#cliente"/></svg>
                                    Clientes
                                </button>
                            </h2>
                            <div id="flush-collapseFour" class="accordion-collapse text-white collapse" aria-labelledby="flush-headingFour" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\ListarCidade">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#cadastrar"/></svg>
                                        Cadastrar
                                    </a>
                                </div>
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\ListarCliente?pagina=1">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#listar"/></svg>
                                        Listar
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="accordion-item bg-dark text-white">
                            <h2 class="accordion-header text-white" id="flush-headingOne">
                                <button class="accordion-button collapsed text-white bg-dark" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#cobranca"/></svg>
                                    Cobrança
                                </button>
                            </h2>
                            <div id="flush-collapseOne" class="accordion-collapse text-white collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\DadosCobranca">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#cadastrar"/></svg>
                                        Cadastrar
                                    </a>
                                </div>
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\ListarCobranca?pagina=1&nivel=F">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#listar"/></svg>
                                        Listar
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="accordion-item bg-dark text-white">
                            <h2 class="accordion-header text-white" id="flush-headingThree">
                                <button class="accordion-button collapsed text-white bg-dark" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#people-circle"/></svg>
                                    Farmacêuticos
                                </button>
                            </h2>
                            <div id="flush-collapseThree" class="accordion-collapse text-white collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\Layout\Farmaceuticos\Cadastrar-Farmaceuticos.jsp">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#cadastrar"/></svg>
                                        Cadastrar
                                    </a>
                                </div>
                                <div class="accordion-body">
                                    <a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\ListarFarmaceutico">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#listar"/></svg>
                                        Listar
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="accordion-item bg-dark text-white">
                            <h2 class="accordion-header text-white" id="flush-headingTwo">
                                <button class="accordion-button collapsed text-white bg-dark" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#relatorio"/></svg>
                                    Relatórios
                                </button>
                            </h2>
                            <div id="flush-collapseTwo" class="accordion-collapse text-white collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body"><a class="list-group-item-action text-white bg-dark" href="${pageContext.request.contextPath}\Relatorio?param=4">
                                        <svg class="bi me-2" width="16" height="16"><use xlink:href="#listar"/></svg>
                                        Relatório de Entregas
                                    </a></div>
                                <div class="accordion-body">Teste Relatorio</div>
                                <div class="accordion-body">Teste Relatorio</div>
                            </div>
                        </div>
                        <a class="list-group-item botao-sair list-group-item-action text-white bg-dark p-3" href="${pageContext.request.contextPath}/Login?acao=logout">
                            <svg class="bi me-2" width="16" height="16"><use xlink:href="#sair"/></svg>
                            Sair
                        </a>
                    </div>
                </div>
            </div>
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark border-bottom">
                    <div class="container-fluid">
                        <button class="btn" id="sidebarToggle"><span class="navbar-toggler-icon"></span></button>>  

                    </div>
                </nav>
