package com.example.one_hundred_and_twenty_fourth.listeners;

import com.example.one_hundred_and_twenty_fourth.db.DataBaseConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        String dbUrl = context.getInitParameter("dbUrl");
        String driver = context.getInitParameter("driver");

        try {
            DataBaseConnectionManager dbManager = new DataBaseConnectionManager(username, password, dbUrl, driver);
            Connection connection = dbManager.getConnection();
            context.setAttribute("dbConnection", connection);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ((Connection) sce.getServletContext().getAttribute("dbConnection")).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
