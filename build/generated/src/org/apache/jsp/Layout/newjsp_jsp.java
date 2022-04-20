package org.apache.jsp.Layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\" />\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\n");
      out.write("    <meta name=\"description\" content=\"\" />\n");
      out.write("    <meta name=\"author\" content=\"\" />\n");
      out.write("    <title>Simple Sidebar - Start Bootstrap Template</title>\n");
      out.write("    <!-- Favicon-->\n");
      out.write("    <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />\n");
      out.write("    <!-- Core theme CSS (includes Bootstrap)-->\n");
      out.write("    <link href=\"../css/sidebar.css\" rel=\"stylesheet\" />\n");
      out.write("\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("    <script src=\"https://getbootstrap.com/docs/5.0/examples/sidebars/\"></script>\n");
      out.write("    <!-- Core theme JS-->\n");
      out.write("    <script src=\"../css/sidebar.js\"></script>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("    <div class=\"d-flex\" id=\"wrapper\">\n");
      out.write("        <!-- Sidebar-->\n");
      out.write("        <div class=\"border-end bg-white\" id=\"sidebar-wrapper\">\n");
      out.write("            <div class=\"sidebar-heading border-bottom bg-light\">Start Bootstrap</div>\n");
      out.write("            <div class=\"list-group list-group-flush\">\n");
      out.write("                <a class=\"list-group-item list-group-item-action list-group-item-light p-3\" href=\"#!\">Dashboard</a>\n");
      out.write("                <a class=\"list-group-item list-group-item-action list-group-item-light p-3\" href=\"#!\">Shortcuts</a>\n");
      out.write("                <a class=\"list-group-item list-group-item-action list-group-item-light p-3\" href=\"#!\">Overview</a>\n");
      out.write("                <a class=\"list-group-item list-group-item-action list-group-item-light p-3\" href=\"#!\">Events</a>\n");
      out.write("                <a class=\"list-group-item list-group-item-action list-group-item-light p-3\" href=\"#!\">Profile</a>\n");
      out.write("                <a class=\"list-group-item list-group-item-action list-group-item-light p-3\" href=\"#!\">Status</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- Page content wrapper-->\n");
      out.write("        <div id=\"page-content-wrapper\">\n");
      out.write("            <!-- Top navigation-->\n");
      out.write("            <nav class=\"navbar navbar-expand-lg navbar-light bg-light border-bottom\">\n");
      out.write("                <div class=\"container-fluid\">\n");
      out.write("                    <button class=\"btn btn-primary\" id=\"sidebarToggle\">Toggle Menu</button>\n");
      out.write("                    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\n");
      out.write("                    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n");
      out.write("                        <ul class=\"navbar-nav ms-auto mt-2 mt-lg-0\">\n");
      out.write("                            <li class=\"nav-item active\"><a class=\"nav-link\" href=\"#!\">Home</a></li>\n");
      out.write("                            <li class=\"nav-item\"><a class=\"nav-link\" href=\"#!\">Link</a></li>\n");
      out.write("                            <li class=\"nav-item dropdown\">\n");
      out.write("                                <a class=\"nav-link dropdown-toggle\" id=\"navbarDropdown\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">Dropdown</a>\n");
      out.write("                                <div class=\"dropdown-menu dropdown-menu-end\" aria-labelledby=\"navbarDropdown\">\n");
      out.write("                                    <a class=\"dropdown-item\" href=\"#!\">Action</a>\n");
      out.write("                                    <a class=\"dropdown-item\" href=\"#!\">Another action</a>\n");
      out.write("                                    <div class=\"dropdown-divider\"></div>\n");
      out.write("                                    <a class=\"dropdown-item\" href=\"#!\">Something else here</a>\n");
      out.write("                                </div>\n");
      out.write("                            </li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </nav>\n");
      out.write("            <!-- Page content-->\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <h1 class=\"mt-4\">Simple Sidebar</h1>\n");
      out.write("                <p>The starting state of the menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will change.</p>\n");
      out.write("                <p>\n");
      out.write("                    Make sure to keep all page content within the\n");
      out.write("                    <code>#page-content-wrapper</code> . The top navbar is optional, and just for demonstration. Just create an element with the\n");
      out.write("                    <code>#sidebarToggle</code> ID which will toggle the menu when clicked.\n");
      out.write("                </p>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <!-- Bootstrap core JS-->\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
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
