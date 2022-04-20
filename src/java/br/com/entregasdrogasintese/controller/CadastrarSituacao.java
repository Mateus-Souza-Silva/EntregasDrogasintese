package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.dao.SituacaoDAOImpl;
import br.com.entregasdrogasintese.model.Situacao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarSituacao", urlPatterns = {"/CadastrarSituacao"})
public class CadastrarSituacao extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        
        String mensagem = null;
        
        Situacao situacao = new Situacao();
        situacao.setDescricao(request.getParameter("descricao").toUpperCase());
        
        try {
            GenericDAO dao = new SituacaoDAOImpl();
            if (dao.cadastrar(situacao)) {
                mensagem = "Situacao Cadastrada com sucesso!";
            }else{
                mensagem = "Problemas ao cadastrar Situacao! Controller";
            }
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("Situacao/cadastrar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Situacao Controller Erro: " + ex.getMessage());
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
