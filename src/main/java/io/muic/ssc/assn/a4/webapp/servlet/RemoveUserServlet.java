package io.muic.ssc.assn.a4.webapp.servlet;

import io.muic.ssc.assn.a4.webapp.Routable;
import io.muic.ssc.assn.a4.webapp.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveUserServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/remove-user.jsp");
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String username = request.getParameter("delete");
        if (request.getSession().getAttribute("delete") == null) {
            request.getSession().setAttribute("delete", username);
        }
        if (!StringUtils.isBlank(password)) {
            if (securityService.authenticate((String) request.getSession().getAttribute("username"), password, request)) {
                securityService.removeUser((String) request.getSession().getAttribute("delete"));
                request.getSession().removeAttribute("delete");
                response.sendRedirect("/users");
            } else {
                String error = "Wrong password";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/remove-user.jsp");
                rd.include(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/remove-user.jsp");
            rd.include(request, response);
        }
    }

    @Override
    public String getMapping() {
        return "/remove-user";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
