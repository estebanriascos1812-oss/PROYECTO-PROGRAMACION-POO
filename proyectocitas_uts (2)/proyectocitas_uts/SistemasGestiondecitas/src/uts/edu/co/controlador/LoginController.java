package uts.edu.co.controlador;

import uts.edu.co.modelo.Conexion;
import uts.edu.co.vista.LoginView;
import uts.edu.co.vista.MainMenuView;
import javax.swing.*;
import java.sql.*;

public class LoginController {
    private LoginView vistaLogin;

    public LoginController(LoginView vista) {
        this.vistaLogin = vista;
        this.vistaLogin.btnIngresar.addActionListener(e -> validarCredenciales());
        this.vistaLogin.setVisible(true);
    }

    private void validarCredenciales() {
        String usuario = vistaLogin.txtUsuario.getText();
        String clave = new String(vistaLogin.txtPassword.getPassword());
       String sql = "SELECT rol FROM usuario WHERE email = ? AND password = ?";

        Connection con = Conexion.getConexion();
        if (con == null) {
            JOptionPane.showMessageDialog(vistaLogin, "¡Error! Archivo bd_personas.db no encontrado en la raíz.", "Error Conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String rolObtenido = rs.getString("rol");
                vistaLogin.dispose();
                
                MainMenuView menu = new MainMenuView(rolObtenido);
                new MainMenuController(menu);
            } else {
                JOptionPane.showMessageDialog(vistaLogin, "Credenciales incorrectas de Acceso.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}