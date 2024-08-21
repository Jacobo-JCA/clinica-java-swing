
package com.mycompany.clinica.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jacob
 */
public class ConnectionDB {
    
    private static String URL = "jdbc:postgresql://";
    private static String USERNAME = "postgres";
    private static String PASSWORD = " ";
    
    
    private static Connection connection;
    
    public static Connection getInstance() throws SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
