package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.ClienteDAOImpl;
import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.dao.PagamentoDAOImpl;
import br.com.entregasdrogasintese.dao.SetorDAOImpl;
import br.com.entregasdrogasintese.dao.SituacaoDAOImpl;
import br.com.entregasdrogasintese.dao.TipoPagamentoDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DadosCobranca", urlPatterns = {"/DadosCobranca"})
public class DadosCobranca extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        try {
            //            listar cliente
            GenericDAO daocliente = new ClienteDAOImpl();
            request.setAttribute("cliente", daocliente.listar());
            
//            listar setor
            GenericDAO daosetor = new SetorDAOImpl();
            request.setAttribute("setor", daosetor.listar());
            
//            listar pagamento
            GenericDAO daopagamento = new PagamentoDAOImpl();
            request.setAttribute("pagamento", daopagamento.listar());
            
//            listar situacao
            GenericDAO daosituacao = new SituacaoDAOImpl();
            request.setAttribute("situacao", daosituacao.listar());

//            listar tipopagamento
            GenericDAO daotipopagamento = new TipoPagamentoDAOImpl();
            request.setAttribute("tipopagamento", daotipopagamento.listar());
            
            request.getRequestDispatcher("Layout/Cobranca/Cadastrar-Cobranca.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas ao listar DadosEntrega! Erro: " + ex.getMessage());
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
