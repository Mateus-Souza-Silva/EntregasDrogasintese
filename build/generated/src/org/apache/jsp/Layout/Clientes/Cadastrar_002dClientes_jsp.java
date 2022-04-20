package org.apache.jsp.Layout.Clientes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Cadastrar_002dClientes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../sidebar.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("    <main>\n");
      out.write("        <section>\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <h1 class=\"title-h1\">Cadastrar Clientes</h1>\n");
      out.write("                <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\\CadastrarCliente\" method=\"POST\" class=\"row g-4\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-md-1\">\n");
      out.write("                            <label for=\"clienteido\" class=\"form-label\">ID</label>\n");
      out.write("                            <input type=\"number\" name=\"clienteido\" class=\"form-control\" readonly>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-md-8\">\n");
      out.write("                            <label for=\"nome\" class=\"form-label\">Nome</label>\n");
      out.write("                            <input type=\"text\" name=\"nome\" class=\"form-control\"/>                        \n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-md-2\">\n");
      out.write("                            <label for=\"datanascimento\" class=\"form-label\">Data Nascimento</label>\n");
      out.write("                            <input type=\"date\" name=\"datanascimento\" id=\"datanascimento\" onblur=\"calculaIdade()\" class=\"form-control\"/>                        \n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-md-1\">\n");
      out.write("                            <label for=\"idade\" class=\"form-label\">Idade</label>\n");
      out.write("                            <input type=\"number\" name=\"idade\" id=\"idade\" class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-md-2\">\n");
      out.write("                            <label for=\"cep\" class=\"form-label\">Cep</label>\n");
      out.write("                            <input type=\"text\" name=\"cep\" id=\"cep\" onblur=\"pesquisacep(this.value);\" class=\"form-control\" required=\"\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-md-4\">\n");
      out.write("                            <label for=\"bairro\" class=\"form-label text-decoration-none\">Bairro</label>\n");
      out.write("                            <input type=\"text\" id=\"bairro\" name=\"bairro\" class=\"form-control\" required=\"\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-md-5\">\n");
      out.write("                            <label for=\"logradouro\" class=\"form-label\">Logradouro</label>\n");
      out.write("                            <input type=\"text\" id=\"rua\" name=\"logradouro\" class=\"form-control\"/>                        \n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-md-1\">\n");
      out.write("                            <label for=\"numero\" class=\"form-label\">Numero</label>\n");
      out.write("                            <input type=\"text\" name=\"numero\" class=\"form-control\"/>                        \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-md-3\">\n");
      out.write("                            <label for=\"cidade\" class=\"form-label\">Cidade</label>\n");
      out.write("                            <select class=\"form-select\" aria-label=\"Lista de cidades\" name=\"cidadeido\">\n");
      out.write("                                ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("                   \n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-md-7\">\n");
      out.write("                            <label for=\"complemento\" class=\"form-label\">Complemento</label>\n");
      out.write("                            <input type=\"text\" name=\"complemento\" class=\"form-control\" required=\"\">\n");
      out.write("                        </div>                        \n");
      out.write("\n");
      out.write("                        <div class=\"col-md-2\">\n");
      out.write("                            <label for=\"telefone\" class=\"form-label\">Telefone</label>\n");
      out.write("                            <input type=\"tel\" name=\"telefone\" class=\"form-control\"/>                        \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-md-12 botao-cadastrar-div\">\n");
      out.write("                            <button type=\"submit\" class=\"btn botao-cadastrar\">Cadastrar</button>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${mensagem}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("\n");
      out.write("    </main>\n");
      out.write("\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\\assets\\dist\\js\\bootstrap.bundle.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\\sidebars.js\" type=\"text/javascript\"></script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setVar("cidade");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cidade}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                    <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cidade.cidadeido}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('"');
          out.write(' ');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cidade.cidadeido==cliente.cidade.cidadeido?'selected':''}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cidade.nome}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</option>\n");
          out.write("                                ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
