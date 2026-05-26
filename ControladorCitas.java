package controlador;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Cita; // Importamos tu clase del Paso 3

public class ControladorCitas {

    // MÉTODO PARA REGISTRAR (Con validación de duplicidad)
    public void agendarNuevaCita(String fecha, String hora, String docPac, String docMed) {
        String sql = "INSERT INTO citas (fecha, hora, doc_paciente, doc_medico) VALUES (?,?,?,?)";
        
        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setString(3, docPac);
            ps.setString(4, docMed);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cita agendada exitosamente.");
            
        } catch (SQLException e) {
            // Validación de cruce de horarios (Error 19 en SQLite es UNIQUE constraint)
            if (e.getErrorCode() == 19) {
                JOptionPane.showMessageDialog(null, "ERROR: El médico ya tiene una cita asignada en esa fecha y hora.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agendar: " + e.getMessage());
            }
        }
    }

    // MÉTODO PARA BUSCAR (Requerimiento funcional de Búsqueda)
    public ResultSet buscarCitaPorFecha(String fechaBusqueda) {
        Connection con = Conexion.conectar();
        String sql = "SELECT * FROM citas WHERE fecha = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fechaBusqueda);
            return ps.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }
}