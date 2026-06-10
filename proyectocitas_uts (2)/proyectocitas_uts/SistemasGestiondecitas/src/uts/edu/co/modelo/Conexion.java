package uts.edu.co.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection con = null;
    // Buscamos tu archivo de base de datos directamente en la raíz
    private static final String URL = "jdbc:sqlite:bd_personas.db";

    public static Connection getConexion() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection(URL);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexión en SQLite: " + e.getMessage());
        }
        return con;
    }
}