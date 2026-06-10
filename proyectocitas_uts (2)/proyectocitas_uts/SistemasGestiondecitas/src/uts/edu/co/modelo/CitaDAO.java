package uts.edu.co.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public boolean insertarCita(int idPaciente, int idDoctor, String fechaHora, String motivo, String estado) {
        Connection con = Conexion.getConexion();
        if (con == null) return false;

        String sql = "INSERT INTO cita(id_paciente, id_doctor, fecha_hora, motivo, estado) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ps.setInt(2, idDoctor);
            ps.setString(3, fechaHora);
            ps.setString(4, motivo);
            ps.setString(5, estado);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Object[]> listarCitas() {
        List<Object[]> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();
        if (con == null) return lista;

        String sql = "SELECT c.id_cita, " +
                     "(u1.nombre || ' ' || u1.apellido) AS paciente_nom, " +
                     "(u2.nombre || ' ' || u2.apellido) AS doctor_nom, " +
                     "c.fecha_hora, c.motivo, c.estado " +
                     "FROM cita c " +
                     "INNER JOIN paciente p ON c.id_paciente = p.id_paciente " +
                     "INNER JOIN usuario u1 ON p.id_usuario = u1.id_usuario " +
                     "INNER JOIN doctor d ON c.id_doctor = d.id_doctor " +
                     "INNER JOIN usuario u2 ON d.id_usuario = u2.id_usuario";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id_cita"),
                    rs.getString("paciente_nom"),
                    rs.getString("doctor_nom"),
                    rs.getString("fecha_hora"),
                    rs.getString("motivo"),
                    rs.getString("estado")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}