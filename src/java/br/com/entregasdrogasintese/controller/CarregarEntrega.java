package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.ClienteDAOImpl;
import br.com.entregasdrogasintese.dao.EntregaDAOImpl;
import br.com.entregasdrogasintese.dao.EntregadorDAOImpl;
import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.dao.PagamentoDAOImpl;
import br.com.entregasdrogasintese.dao.SituacaoDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mateus
 */
@WebServlet(name = "CarregarEntrega", urlPatterns = {"/CarregarEntrega"})
public class CarregarEntrega extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        try {
            int entregaido = Integer.parseInt(request.getParameter("entregaido"));
            String nivel = request.getParameter("nivel");

            GenericDAO daoEntregador = new EntregadorDAOImpl();
            request.setAttribute("entregador", daoEntregador.listar());

            GenericDAO daoPagamento = new PagamentoDAOImpl();
            request.setAttribute("pagamento", daoPagamento.listar());

            GenericDAO daoSituacao = new SituacaoDAOImpl();
            request.setAttribute("situacao", daoSituacao.listar());

            GenericDAO daoCliente = new ClienteDAOImpl();
            request.setAttribute("cliente", daoCliente.listar());

            GenericDAO dao = new EntregaDAOImpl();
            request.setAttribute("entrega", dao.carregar(entregaido));

            if (nivel.equals("E")) {
                request.getRequestDispatcher("Layout/PaginaEntregador/Entrega/Cadastrar-Entrega.jsp").forward(request, response);
            } else if(nivel.equals("F")){
                request.getRequestDispatcher("Layout/Entregas/Cadastrar-Entrega.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println("Erro ao carregar EntregaCTR! Erro: " + ex.getMessage());
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
