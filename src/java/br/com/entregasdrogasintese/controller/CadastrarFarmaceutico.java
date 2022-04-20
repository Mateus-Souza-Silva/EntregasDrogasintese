package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.FarmaceuticoDAOImpl;
import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.model.Farmaceutico;
import br.com.entregasdrogasintese.util.Conversoes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarFarmaceutico", urlPatterns = {"/CadastrarFarmaceutico"})
public class CadastrarFarmaceutico extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");

        String mensagem = null;

        Farmaceutico farmaceutico = new Farmaceutico();
        farmaceutico.setDatanascimento(Conversoes.converterData(request.getParameter("datanascimento")));
        farmaceutico.setFuncao(request.getParameter("funcao").toUpperCase());
        farmaceutico.setIdade(Integer.parseInt(request.getParameter("idade")));
        farmaceutico.setNivel("F");
        farmaceutico.setNome(request.getParameter("nome").toUpperCase());
        farmaceutico.setSenha(request.getParameter("senha"));

        try {
            GenericDAO dao = new FarmaceuticoDAOImpl();

            if (request.getParameter("pessoaido").equals("")) {
                if (dao.cadastrar(farmaceutico)) {
                    mensagem = "Farmaceutico Cadastrado com sucesso!";
                } else {
                    mensagem = "Erro ao cadastrar Farmaceutico Controller!";
                }
            }else if (!request.getParameter("pessoaido").equals("")) {
                farmaceutico.setPessoaido(Integer.parseInt(request.getParameter("pessoaido")));
                if (dao.alterar(farmaceutico)) {
                    mensagem = "Farmaceutico alterado com sucesso!";
                }else{
                    mensagem = "Problemas ao alterar Farmaceutico!";
                }
            }
            
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("Layout/Farmaceuticos/Cadastrar-Farmaceuticos.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Farmaceutico Controller! Erro: " + ex.getMessage());
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
