package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.EntregadorDAOImpl;
import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.dao.PessoaDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarEntregador", urlPatterns = {"/ListarEntregador"})
public class ListarEntregador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        try {
            GenericDAO dao = new EntregadorDAOImpl();
            request.setAttribute("entregador", dao.listar());           
            
            //request.getRequestDispatcher("DadosEntrega").forward(request, response);                    
            
            request.getRequestDispatcher("Layout/Entregadores/Listar-Entregadores.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas ao listar entregador CONTROLLER! ERRO: " + ex.getMessage());
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
