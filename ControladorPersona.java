package controlador;
import java.sql.*;
import javax.swing.JOptionPane;

public class ControladorPersona {

    public void registrarPaciente(String doc, String nom, String hist) {
        String sql = "INSERT INTO pacientes (documento, nombre, historial) VALUES (?,?,?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doc);
            ps.setString(2, nom);
            ps.setString(3, hist);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void eliminarPaciente(String documento) {
        String sql = "DELETE FROM pacientes WHERE documento = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, documento);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro eliminado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar.");
        }
    }
}