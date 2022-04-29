package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.dao.EntregaDAOImpl;
import br.com.entregasdrogasintese.dao.GenericDAO;
import br.com.entregasdrogasintese.model.Cliente;
import br.com.entregasdrogasintese.model.Entrega;
import br.com.entregasdrogasintese.model.Entregador;
import br.com.entregasdrogasintese.model.Pagamento;
import br.com.entregasdrogasintese.model.Situacao;
import br.com.entregasdrogasintese.util.Conversoes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarEntrega", urlPatterns = {"/CadastrarEntrega"})
public class CadastrarEntrega extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");

        String mensagem = null;

        Entrega entrega = new Entrega();
        entrega.setDataentrega(Conversoes.converterData(request.getParameter("dataentrega")));
        entrega.setEntregador(new Entregador(Integer.parseInt(request.getParameter("entregadorido"))));
        entrega.setCliente(new Cliente(Integer.parseInt(request.getParameter("clienteido"))));
        entrega.setObservacao(request.getParameter("observacao").toUpperCase());
        entrega.setProdutos(request.getParameter("produtos").toUpperCase());
        entrega.setRecebedor(request.getParameter("recebedor").toUpperCase());
        entrega.setSituacao(new Situacao(Integer.parseInt(request.getParameter("situacaoido"))));
        entrega.setPagamento(new Pagamento(Integer.parseInt(request.getParameter("pagamentoido"))));
        entrega.setValor(Double.parseDouble(request.getParameter("valor")));

        try {
            GenericDAO dao = new EntregaDAOImpl();

            if (request.getParameter("entregaido").equals("")) {
                if (dao.cadastrar(entrega)) {
                    mensagem = "Entrega Cadastrada com sucesso!";
                } else {
                    mensagem = "Problemas ao cadastrar entrega Controller";
                }
            } else if (!request.getParameter("entregaido").equals("")) {
                entrega.setEntregaido(Integer.parseInt(request.getParameter("entregaido")));
                if (dao.alterar(entrega)) {
                    mensagem = "Entrega Alterada com sucesso!";
                } else {
                    mensagem = "Problemas ao alterar entrega!";
                }
            }
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("DadosEntrega").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar entrega! Erro: " + ex.getMessage());
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
