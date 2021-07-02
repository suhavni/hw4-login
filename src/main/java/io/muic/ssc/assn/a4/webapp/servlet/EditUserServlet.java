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

public class EditUserServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit-user.jsp");
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newUsername = request.getParameter("new_username");
        String newPassword = request.getParameter("new_password");
        String username = (String) request.getSession().getAttribute("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        if (!StringUtils.isBlank(password) && securityService.authenticate(username, password, request)) {
            if (!StringUtils.isBlank(newUsername) && securityService.hasUsername(newUsername)) {
                String error = "This username already exists.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit-user.jsp");
                rd.include(request, response);

            } else {
                if (!StringUtils.isBlank(newUsername)) {
                    securityService.updateColumn("username", username, newUsername);
                    request.getSession().setAttribute("username", newUsername);
                    username = newUsername;
                }
                if (!StringUtils.isBlank(newPassword)) {
                    securityService.updateColumn("encrypted_password", username, securityService.encryptPassword(newPassword));
                    password = newPassword;
                }
                if (!StringUtils.isBlank(firstname)) {
                    securityService.updateColumn("firstname", username, firstname);
                }
                if (!StringUtils.isBlank(firstname)) {
                    securityService.updateColumn("lastname", username, lastname);
                }
                securityService.authenticate(username, password, request);
                response.sendRedirect("/users");
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit-user.jsp");
            rd.include(request, response);
        }
    }

    @Override
    public String getMapping() {
        return "/edit-user";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
