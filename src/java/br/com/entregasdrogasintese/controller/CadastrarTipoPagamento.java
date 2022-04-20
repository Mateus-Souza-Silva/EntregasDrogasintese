package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.dao.TipoPagamentoDAOImpl;
import br.com.entregasdrogasintese.model.TipoPagamento;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarTipoPagamento", urlPatterns = {"/CadastrarTipoPagamento"})
public class CadastrarTipoPagamento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        
        String mensagem = null;
        
        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setDescricao(request.getParameter("descricao").toUpperCase());
        
        try {
            GenericDAO dao = new TipoPagamentoDAOImpl();
            if (dao.cadastrar(tipoPagamento)) {
                mensagem = "Tipo Pagamento cadastrado com sucesso!";
            }else{
                mensagem = "Problemas ao Cadastrar Pagamento!";
            }
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("TipoPagamento/cadastrar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar TipoPagamento Controller! Erro: " + ex.getMessage());
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
