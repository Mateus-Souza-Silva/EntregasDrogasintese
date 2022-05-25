package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.ClienteDAOImpl;
import br.com.entregasdrogasintese.dao.EntregaDAOImpl;
import br.com.entregasdrogasintese.dao.EntregadorDAOImpl;
import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.dao.PagamentoDAOImpl;
import br.com.entregasdrogasintese.dao.SituacaoDAOImpl;
import br.com.entregasdrogasintese.model.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListarEntrega", urlPatterns = {"/ListarEntrega"})
public class ListarEntrega extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");

        HttpSession session = request.getSession(true);
        Pessoa pessoa = (Pessoa) session.getAttribute("pessoalogada");
        
        Integer pagina = Integer.parseInt(request.getParameter("pagina"));

        try {
            EntregaDAOImpl dao = new EntregaDAOImpl();
            request.setAttribute("entregas", dao.listar(pagina));
            request.setAttribute("pagina", pagina);            

            if (pessoa.getNivel().equals("E")) {
                request.getRequestDispatcher("DadosEntregadorLogado").forward(request, response);
            } else if (pessoa.getNivel().equals("F")) {
                request.getRequestDispatcher("Layout/Entregas/Listar-Entregas.jsp?pagina=1").forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao listar entregas! Erro: " + ex.getMessage());
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
