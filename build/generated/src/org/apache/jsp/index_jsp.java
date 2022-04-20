package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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

      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Layout/sidebar.jsp", out, false);
      out.write("\n");
      out.write("<section>\n");
      out.write("    <div class=\"container-fluid\">\n");
      out.write("        <div class=\"row row-cols-1 row-cols-md-3 g-4\">\n");
      out.write("            <div class=\"col\">\n");
      out.write("                <div class=\"card h-100\">\n");
      out.write("                    <img src=\"...\" class=\"card-img-top\" alt=\"...\">\n");
      out.write("                    <div class=\"card-body\">\n");
      out.write("                        <h5 class=\"card-title\">Card title</h5>\n");
      out.write("                        <p class=\"card-text\">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"card-footer\">\n");
      out.write("                        <small class=\"text-muted\">Last updated 3 mins ago</small>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col\">\n");
      out.write("                <div class=\"card h-100\">\n");
      out.write("                    <img src=\"...\" class=\"card-img-top\" alt=\"...\">\n");
      out.write("                    <div class=\"card-body\">\n");
      out.write("                        <h5 class=\"card-title\">Card title</h5>\n");
      out.write("                        <p class=\"card-text\">This card has supporting text below as a natural lead-in to additional content.</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"card-footer\">\n");
      out.write("                        <small class=\"text-muted\">Last updated 3 mins ago</small>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col\">\n");
      out.write("                <div class=\"card h-100\">\n");
      out.write("                    <img src=\"...\" class=\"card-img-top\" alt=\"...\">\n");
      out.write("                    <div class=\"card-body\">\n");
      out.write("                        <h5 class=\"card-title\">Card title</h5>\n");
      out.write("                        <p class=\"card-text\">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"card-footer\">\n");
      out.write("                        <small class=\"text-muted\">Last updated 3 mins ago</small>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("\n");
      out.write("</main>\n");
      out.write("\n");
      out.write("<script src=\"assets/dist/js/bootstrap.bundle.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\\css\\estilo.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<script src=\"sidebars.js\" type=\"text/javascript\"></script>\n");
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
}
