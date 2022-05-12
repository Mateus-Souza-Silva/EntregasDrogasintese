package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.EntregadorDAOImpl;
import br.com.entregasdrogasintese.dao.PessoaDAOImpl;
import br.com.entregasdrogasintese.model.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String mensagem = request.getParameter("acao");
        
        if (request.getParameter("acao").equals("logar")) {
            String nome = request.getParameter("nome").toUpperCase();
            String senha = request.getParameter("senha");

            if (!nome.equals("") && !senha.equals("")) {
                try {
                    PessoaDAOImpl dao = new PessoaDAOImpl();
                    Pessoa pessoa = dao.LogarPessoa(nome, senha);
                    if (pessoa != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("pessoalogada", pessoa);
                        if (pessoa.getNivel().equals("F")) {
                            request.getRequestDispatcher("DadosFarmaceuticoLogado").forward(request, response);
                        } else if (pessoa.getNivel().equals("E")) {
                            
                            try{
                                EntregadorDAOImpl daoEntregador = new EntregadorDAOImpl();
                                session.setAttribute("entregador", daoEntregador.carregar(pessoa.getPessoaido()));
                            } catch(Exception ex){
                                System.out.println("Problemas ao trazer dados do entregador! Erro: " + ex.getMessage());
                                ex.printStackTrace();
                            }
                            
                            request.getRequestDispatcher("DadosEntregadorLogado").forward(request, response);
                        }
                    } else {
                        mensagem = "Login ou Senha inválidos!";
                        request.setAttribute("mensagem", mensagem);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } catch (Exception ex) {
                    System.out.println("Problemas ao Logar Pessoa"
                            + "\n Erro: " + ex.getMessage());
                    ex.printStackTrace();
                }finally{
                    mensagem = "Erro ao Logar, entre em contato com o programador!";
                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                mensagem = "Login ou senha inválidos!";
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else if (request.getParameter("acao").equals("logout")) {
            HttpSession session = request.getSession(true);
            session.invalidate();
            response.sendRedirect("login.jsp");
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
