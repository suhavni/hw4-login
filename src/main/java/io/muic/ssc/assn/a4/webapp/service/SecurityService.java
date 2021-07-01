/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ssc.assn.a4.webapp.service;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.muic.ssc.assn.a4.webapp.DatabaseConnection;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class SecurityService {
    private Connection connection;
    private PreparedStatement st;
    private Statement statement;

    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
        return username != null && hasUsername(username);
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
//        String passwordInDB = userCredentials.get(username);
        try {
            connection = DatabaseConnection.initializeDatabase();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT username, hashed_password FROM users WHERE username = '" + username + "'");
            rs.next();
            String hashedPassword = rs.getString("hashed_password");
            boolean isMatched = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified;
            statement.close();
            connection.close();
            if (isMatched) {
                request.getSession().setAttribute("username", username);
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void addUser(String username, String password) {
        try {
            connection = DatabaseConnection.initializeDatabase();
            st = connection.prepareStatement("INSERT INTO users(username, hashed_password) VALUES(?, ?);");
            st.setString(1, username);
            String hashed = BCrypt.with(new SecureRandom()).hashToString(12, password.toCharArray());
            st.setString(2, hashed);
            st.executeUpdate();
            st.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public Iterable<String> getUsers() {
        List<String> users = new ArrayList<>();
        try {
            connection = DatabaseConnection.initializeDatabase();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT username FROM users");
            while(rs.next()) {
                users.add(rs.getString("username"));
            }
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public boolean hasUsername(String username) {
        try {
            connection = DatabaseConnection.initializeDatabase();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            boolean containsUsername = rs.next();
            statement.close();
            connection.close();
            return containsUsername;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void removeUser(String username) {
        try {
            connection = DatabaseConnection.initializeDatabase();
            st = connection.prepareStatement("DELETE FROM users WHERE username = '" + username + "'");
            st.executeUpdate();
            st.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}