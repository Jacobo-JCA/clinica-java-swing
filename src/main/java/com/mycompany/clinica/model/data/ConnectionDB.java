
package com.mycompany.clinica.model.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jacob
 */
public class ConnectionDB { 
    private static String URL = "jdbc:postgresql://localhost:5432/clinica_praxell";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "1234";
    //Pilito2024
    private static Connection connection;
    
    public static Connection getInstance() throws SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
