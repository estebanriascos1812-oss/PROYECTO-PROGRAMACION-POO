package uts.edu.co.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public boolean generarFactura(int idCita, String fecha, double total, String estado) {
        Connection con = Conexion.getConexion();
        if (con == null) return false;

        String sql = "INSERT INTO factura(id_cita, fecha_emision, total, estado) VALUES(?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            ps.setString(2, fecha);
            ps.setDouble(3, total);
            ps.setString(4, estado);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Object[]> listarFacturas() {
        List<Object[]> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();
        if (con == null) return lista;

        // Triple Join para traer el nombre del paciente dueño de la cita facturada
        String sql = "SELECT f.id_factura, (u.nombre || ' ' || u.apellido) AS paciente, f.fecha_emision, f.total, f.estado " +
                     "FROM factura f " +
                     "INNER JOIN cita c ON f.id_cita = c.id_cita " +
                     "INNER JOIN paciente p ON c.id_paciente = p.id_paciente " +
                     "INNER JOIN usuario u ON p.id_usuario = u.id_usuario";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id_factura"),
                    rs.getString("paciente"),
                    rs.getString("fecha_emision"),
                    "$" + rs.getDouble("total"),
                    rs.getString("estado")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}