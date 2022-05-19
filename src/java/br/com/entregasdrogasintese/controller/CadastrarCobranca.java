package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.CobrancaDAOImpl;
import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.model.Cliente;
import br.com.entregasdrogasintese.model.Cobranca;
import br.com.entregasdrogasintese.model.Pagamento;
import br.com.entregasdrogasintese.model.Setor;
import br.com.entregasdrogasintese.model.Situacao;
import br.com.entregasdrogasintese.model.TipoPagamento;
import br.com.entregasdrogasintese.util.Conversoes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarCobranca", urlPatterns = {"/CadastrarCobranca"})
public class CadastrarCobranca extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");

        String mensagem = null;

        Cobranca cobranca = new Cobranca();
        cobranca.setCliente(new Cliente(Integer.parseInt(request.getParameter("clienteido"))));
        cobranca.setDatacobranca(Conversoes.converterData(request.getParameter("datacobranca")));
        cobranca.setDatapagamento(Conversoes.converterData(request.getParameter("datapagamento")));
        cobranca.setNfcobranca(request.getParameter("nfcobranca").toUpperCase());
        cobranca.setObservacao(request.getParameter("observacao").toUpperCase());

        if (request.getParameter("pagamentoido").equals("")) {
            cobranca.setPagamento(null);
        } else {
            cobranca.setPagamento(new Pagamento(Integer.parseInt(request.getParameter("pagamentoido"))));
        }

        cobranca.setSetor(new Setor(Integer.parseInt(request.getParameter("setorido"))));
        cobranca.setSituacao(new Situacao(Integer.parseInt(request.getParameter("situacaoido"))));

        if (request.getParameter("tipopagamentoido").equals("")) {
            cobranca.setTipopagamento(null);
        } else {
            cobranca.setTipopagamento(new TipoPagamento(Integer.parseInt(request.getParameter("tipopagamentoido"))));
        }

        cobranca.setValor(Double.parseDouble(request.getParameter("valor")));
        cobranca.setVencimento(Conversoes.converterData(request.getParameter("vencimento")));

        try {
            GenericDAO dao = new CobrancaDAOImpl();

            if (request.getParameter("cobrancaido").equals("")) {
                if (dao.cadastrar(cobranca)) {
                    mensagem = "Cobranca cadastrada com sucesso!";
                } else {
                    mensagem = "Problemas ao cadastrar cobranca";
                }
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("DadosCobranca").forward(request, response);
            } else if (!request.getParameter("cobrancaido").equals("")) {
                cobranca.setCobrancaido(Integer.parseInt(request.getParameter("cobrancaido")));
                if (dao.alterar(cobranca)) {
                    mensagem = "Cobranca alterada com sucesso!";
                } else {
                    mensagem = "Problemas ao alterar cobranca!";
                }
                request.getRequestDispatcher("ListarCobranca?pagina=1").forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar cobranca! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
