package uts.edu.co.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    
    public boolean insertar(Persona p, String Flyweight, String numeroLicencia) {
        Connection con = Conexion.getConexion();
        if (con == null) return false;
        
        String sqlUsuario = "INSERT INTO usuario(cedula, nombre, apellido, email, password, rol, estado) VALUES(?,?,?,?,?,?,1)";
        String sqlDoctor = "INSERT INTO doctor(id_usuario, especialidad, numero_licencia) VALUES(?, ?, ?)";
        
        try {
            con.setAutoCommit(false);
            int idUsuarioGenerado = -1;
            
            try (PreparedStatement ps1 = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, p.getCedula());
                ps1.setString(2, p.getNombre());
                ps1.setString(3, p.getApellido());
                ps1.setString(4, p.getEmail());
                ps1.setString(5, p.getPassword());
                ps1.setString(6, "Doctor");
                ps1.executeUpdate();
                
                try (ResultSet rs = ps1.getGeneratedKeys()) {
                    if (rs.next()) {
                        idUsuarioGenerado = rs.getInt(1);
                    }
                }
            }
            
            if (idUsuarioGenerado == -1) {
                con.rollback();
                return false;
            }
            
            try (PreparedStatement ps2 = con.prepareStatement(sqlDoctor)) {
                ps2.setInt(1, idUsuarioGenerado);
                ps2.setString(2, Flyweight);
                ps2.setString(3, numeroLicencia);
                ps2.executeUpdate();
            }
            
            con.commit();
            return true;
        } catch (SQLException ex) {
            try { con.rollback(); } catch (SQLException e) { e.printStackTrace(); }
            ex.printStackTrace();
            return false;
        }
    }

    public List<Object[]> listarDoctores() {
        List<Object[]> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();
        if (con == null) return lista;
        
        String sql = "SELECT d.id_doctor, u.cedula, (u.nombre || ' ' || u.apellido) AS nombre_completo, u.email, d.especialidad, d.numero_licencia " +
                     "FROM usuario u INNER JOIN doctor d ON u.id_usuario = d.id_usuario";
        
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id_doctor"),
                    rs.getString("cedula"),
                    rs.getString("nombre_completo"),
                    rs.getString("email"),
                    rs.getString("especialidad"),
                    rs.getString("numero_licencia")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public boolean eliminar(int idDoctor) {
        Connection con = Conexion.getConexion();
        if (con == null) return false;
        
        String sqlBuscarUsuario = "SELECT id_usuario FROM doctor WHERE id_doctor = ?";
        String sqlBorrarUsuario = "DELETE FROM usuario WHERE id_usuario = ?";
        
        try {
            int idUsuario = -1;
            try (PreparedStatement ps1 = con.prepareStatement(sqlBuscarUsuario)) {
                ps1.setInt(1, idDoctor);
                try (ResultSet rs = ps1.executeQuery()) {
                    if (rs.next()) idUsuario = rs.getInt("id_usuario");
                }
            }
            
            if (idUsuario != -1) {
                try (PreparedStatement ps2 = con.prepareStatement(sqlBorrarUsuario)) {
                    ps2.setInt(1, idUsuario);
                    ps2.executeUpdate();
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}