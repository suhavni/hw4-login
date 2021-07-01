/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ssc.assn.a4.webapp.servlet;

import io.muic.ssc.assn.a4.webapp.Routable;
import io.muic.ssc.assn.a4.webapp.service.SecurityService;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    public String getMapping() {
        return "/users";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            // do MVC in here
            String username = (String) request.getSession().getAttribute("username");
            request.setAttribute("username", username);
            request.setAttribute("table", printTable(username));
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/users");
    }

    private String printTable(String currentUser) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "<table style=\"width:40%\">" );
        stringBuilder.append("<tr><th style=\"text-align:left\">Username</th><th style=\"text-align:left\">Action</th></tr>");
        for (String username : securityService.getUsers()) {
            stringBuilder.append("<tr><td>" + username + "</td><td>" + getButton(currentUser, username) + "</td></tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private String getButton(String currentUser, String username) {
        StringBuilder stringBuilder = new StringBuilder();
        if (currentUser.equals(username)) {
            stringBuilder.append("<form action=\"/edit-user\" method=\"post\">");
            stringBuilder.append("<input type=\"submit\" value=\"Edit User\">");
        } else {
            stringBuilder.append("<form action=\"/remove-user\" method=\"post\">");
            stringBuilder.append("<input type = \"hidden\" name = \"delete\" value = ").append(username).append(" />");
            stringBuilder.append("<input type=\"submit\" value=\"Remove User\">");
        }
        stringBuilder.append("</form>");
        return stringBuilder.toString();
    }
}
