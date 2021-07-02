package io.muic.ssc.assn.a4.webapp.servlet;

import io.muic.ssc.assn.a4.webapp.service.SecurityService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import io.muic.ssc.assn.a4.webapp.Routable;

public class AddUserServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add-user.jsp");
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            if (! securityService.hasUsername(username)) {
                securityService.addUser(username, password);
                response.sendRedirect("/users");
            } else {
                String error = "This username already exists.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add-user.jsp");
                rd.include(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add-user.jsp");
            rd.include(request, response);
        }
    }

    @Override
    public String getMapping() {
        return "/add-user";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
