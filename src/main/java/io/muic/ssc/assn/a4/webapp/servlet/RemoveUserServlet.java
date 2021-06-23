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

        String username = request.getParameter("username");

        if (!StringUtils.isBlank(username)) {
            if (securityService.hasUsername(username) && !username.equals((String) request.getSession().getAttribute("username"))) {
                request.getSession().setAttribute("to delete", username);
                response.sendRedirect("/confirmation");
            } else {
                String error = (securityService.hasUsername(username)) ?
                        "You cannot delete your own account." :
                        "This username does not exist";
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

