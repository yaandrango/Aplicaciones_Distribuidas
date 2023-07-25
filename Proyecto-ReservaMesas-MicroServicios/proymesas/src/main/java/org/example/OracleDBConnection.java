package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class OracleDBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:data"; // Reemplaza con tu URL de conexión a Oracle
        String username = "yoselin"; // Reemplaza con tu nombre de usuario de Oracle
        String password = "12345"; // Reemplaza con tu contraseña de Oracle

        return DriverManager.getConnection(url, username, password);
    }
}
