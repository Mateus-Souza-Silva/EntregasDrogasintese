package br.com.entregasdrogasintese.controller;

import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Mateus
 */
@WebServlet(name = "Relatorio", urlPatterns = {"/Relatorio"})
public class Relatorio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer param = Integer.parseInt(request.getParameter("param"));

        if (param == 1) {

            ServletOutputStream servletOutputStream = response.getOutputStream();

            String caminho = "/Layout/Relatorios/";
            String relatorio = caminho + "RelatorioEntregas.jasper";

            InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream(relatorio);

            Connection conn = null;

            try {
                conn = ConnectionFactory.getConnection();
                System.out.println("Conexão do relatório com o banco de dados realizada com sucesso!");

                //envia o relatório em formato PDF para o browser
                response.setContentType("application/pdf");

                //para gerar o relatório no formato PDF o método runReportToPdfStream foi usado
                JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, new HashMap(), conn);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                servletOutputStream.flush();
                servletOutputStream.close();

                try {
                    ConnectionFactory.closeConnection(conn);
                } catch (SQLException ex) {
                    Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    System.out.println("Problemas ao fechar parâmetros de conexão do relatório! Erro: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

        } else if (param == 2) {
            ServletOutputStream servletOutputStream = response.getOutputStream();

            String caminho = "/Layout/Relatorios/";
            String relatorio = caminho + "RelatorioEntregasCadastradas.jasper";

            InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream(relatorio);

            Connection conn = null;

            try {
                conn = ConnectionFactory.getConnection();
                System.out.println("Conexão do relatório com o banco de dados realizada com sucesso!");

                //envia o relatório em formato PDF para o browser
                response.setContentType("application/pdf");

                //para gerar o relatório no formato PDF o método runReportToPdfStream foi usado
                JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, new HashMap(), conn);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                servletOutputStream.flush();
                servletOutputStream.close();

                try {
                    ConnectionFactory.closeConnection(conn);
                } catch (SQLException ex) {
                    Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    System.out.println("Problemas ao fechar parâmetros de conexão do relatório! Erro: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }else if (param == 3) {
            ServletOutputStream servletOutputStream = response.getOutputStream();

            String caminho = "/Layout/Relatorios/";
            String relatorio = caminho + "RelatorioEntregasFinalizadas.jasper";

            InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream(relatorio);

            Connection conn = null;

            try {
                conn = ConnectionFactory.getConnection();
                System.out.println("Conexão do relatório com o banco de dados realizada com sucesso!");

                //envia o relatório em formato PDF para o browser
                response.setContentType("application/pdf");

                //para gerar o relatório no formato PDF o método runReportToPdfStream foi usado
                JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, new HashMap(), conn);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                servletOutputStream.flush();
                servletOutputStream.close();

                try {
                    ConnectionFactory.closeConnection(conn);
                } catch (SQLException ex) {
                    Logger.getLogger(ChamarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    System.out.println("Problemas ao fechar parâmetros de conexão do relatório! Erro: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
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
