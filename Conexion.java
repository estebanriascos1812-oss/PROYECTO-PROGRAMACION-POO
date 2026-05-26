package controlador;
import java.sql.*;

public class Conexion {
    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:clinica.db");
            Statement st = con.createStatement();
            // Creamos las tablas según tus requerimientos
            st.execute("CREATE TABLE IF NOT EXISTS pacientes (documento TEXT PRIMARY KEY, nombre TEXT, historial TEXT)");
            st.execute("CREATE TABLE IF NOT EXISTS medicos (documento TEXT PRIMARY KEY, nombre TEXT, especialidad TEXT)");
            // La restricción UNIQUE cumple el RF de Validación de Horarios
            st.execute("CREATE TABLE IF NOT EXISTS citas (id INTEGER PRIMARY KEY, fecha TEXT, hora TEXT, doc_paciente TEXT, doc_medico TEXT, UNIQUE(fecha, hora, doc_medico))");
        } catch (Exception e) { System.err.println(e.getMessage()); }
        return con;
    }
}