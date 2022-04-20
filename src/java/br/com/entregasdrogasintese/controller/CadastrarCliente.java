package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.ClienteDAOImpl;
import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.model.Cidade;
import br.com.entregasdrogasintese.model.Cliente;
import br.com.entregasdrogasintese.util.Conversoes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarCliente", urlPatterns = {"/CadastrarCliente"})
public class CadastrarCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");

        String mensagem = null;

        Cliente cliente = new Cliente();
        cliente.setBairro(request.getParameter("bairro").toUpperCase());
        cliente.setCep(request.getParameter("cep"));
        cliente.setCidade(new Cidade(Integer.parseInt(request.getParameter("cidadeido"))));
        cliente.setComplemento(request.getParameter("complemento").toUpperCase());
        cliente.setDatanascimento(Conversoes.converterData(request.getParameter("datanascimento")));
        cliente.setIdade(Integer.parseInt(request.getParameter("idade")));
        cliente.setLogradouro(request.getParameter("logradouro").toUpperCase());
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setNivel("C");
        cliente.setNome(request.getParameter("nome").toUpperCase());
        cliente.setNumero(request.getParameter("numero"));

        try {
            GenericDAO dao = new ClienteDAOImpl();

            if (request.getParameter("pessoaido").equals("")) {
                if (dao.cadastrar(cliente)) {
                    mensagem = "Cliente cadastrado com sucesso!";
                } else {
                    mensagem = "Problemas ao cadastrar cliente! Controler";
                }
            }else if (!request.getParameter("pessoaido").equals("")) {
                cliente.setPessoaido(Integer.parseInt(request.getParameter("pessoaido")));
                if (dao.alterar(cliente)) {
                    mensagem = "Cliente alterado com sucesso!";
                }else{
                    mensagem = "Problemas ao alterar cliente!";
                }
            }
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("ListarCidade").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar cliente Controler! Erro" + ex.getMessage());
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
