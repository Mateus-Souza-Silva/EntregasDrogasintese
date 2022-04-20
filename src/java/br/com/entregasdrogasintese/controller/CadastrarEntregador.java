package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.EntregadorDAOImpl;
import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.model.Entregador;
import br.com.entregasdrogasintese.util.Conversoes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarEntregador", urlPatterns = {"/CadastrarEntregador"})
public class CadastrarEntregador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");

        String mensagem = null;

        Entregador entregador = new Entregador();
        entregador.setDatanascimento(Conversoes.converterData(request.getParameter("datanascimento")));
        entregador.setIdade(Integer.parseInt(request.getParameter("idade")));
        entregador.setNivel("E");
        entregador.setNome(request.getParameter("nome").toUpperCase());
        entregador.setSenha(request.getParameter("senha"));

        try {
            GenericDAO dao = new EntregadorDAOImpl();

            if (request.getParameter("pessoaido").equals("")) {
                if (dao.cadastrar(entregador)) {
                    mensagem = "Entregador cadastrado com sucesso!";
                } else {
                    mensagem = "Problemas ao cadastrar entregador! Controller";
                }
            }else if (!request.getParameter("pessoaido").equals("")) {
                entregador.setPessoaido(Integer.parseInt(request.getParameter("pessoaido")));
                if (dao.alterar(entregador)) {
                    mensagem = "Entregador alterado com sucesso!";
                }else{
                    mensagem = "Problemas ao alterar entregador!";
                }
            }
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("Layout/Entregadores/Cadastrar-Entregadores.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar entregador Controller! Erro: " + ex.getMessage());
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
