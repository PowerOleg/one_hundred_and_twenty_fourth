package com.example.one_hundred_and_twenty_fourth.servlets;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "dbservlet", value = "/dbservlet")
public class DBServlet extends HttpServlet {
//    public void init() {
//    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        Connection connection = (Connection) request.getServletContext().getAttribute("dbConnection");

        String query = "select * from user";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                out.println("<html><body>");
                out.println("<p>" + resultSet.getString("username") + " ");
                out.println(resultSet.getString("email") + "</p>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void destroy() {
//    }
}