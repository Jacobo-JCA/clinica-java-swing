
package com.mycompany.clinica.infrastructure.persistence.config;


import java.io.IOException;
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.StandardOpenOption; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnection { 
    private static final Properties props = new Properties();
    private static Connection connection;
    
    private static void loadConfig() {
        try {
            props.load(Files.newInputStream(Path.of("db.properties"), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Connection getInstance() throws SQLException {
        loadConfig();
        if(connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                    props.getProperty("url"), 
                    props.getProperty("username"), 
                    props.getProperty("password"));
        }
        return connection;
    }
}