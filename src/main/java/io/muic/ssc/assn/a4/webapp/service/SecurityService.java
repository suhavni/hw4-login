/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ssc.assn.a4.webapp.service;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.muic.ssc.assn.a4.webapp.DatabaseConnection;
import java.security.SecureRandom;

public class SecurityService {
    private Connection connection;
    private PreparedStatement st;

    public SecurityService() {
        addUser("admin", "123456");
        addUser("muic", "1111");
    }

    private final Map<String, String> userCredentials = new HashMap<String, String>() {{
        put("admin", "123456");
        put("muic", "1111");
    }};

    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
       return (username != null && userCredentials.containsKey(username));
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
//        String passwordInDB = userCredentials.get(username);
        try {
            connection = DatabaseConnection.initializeDatabase();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            rs.next();
            String hashedPassword = rs.getString("hashed_password");
            boolean isMatched = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified;
            st.close();
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
            st = connection.prepareStatement("INSERT INTO users VALUES(?, ?);");
            st.setString(1, username);
            String hashed = BCrypt.with(new SecureRandom()).hashToString(12, password.toCharArray());
            st.setString(2, hashed);
            st.executeUpdate();
            st.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        userCredentials.put(username, password);
    }

    public void removeUser(String username) {
        userCredentials.remove(username);
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public boolean hasUsername(String username) {
        return userCredentials.containsKey(username);
    }
}
