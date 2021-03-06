/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * Web application lifecycle listener.
 *
 * @author Belizwp
 */
public class Init implements ServletContextListener {

    private DataSource dataSource;
    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            dataSource = getRetsdb();
            connection = dataSource.getConnection();
            sce.getServletContext().setAttribute("dataSource", dataSource);
            sce.getServletContext().setAttribute("connection", connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DataSource getRetsdb() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/retsdb");
    }
}
