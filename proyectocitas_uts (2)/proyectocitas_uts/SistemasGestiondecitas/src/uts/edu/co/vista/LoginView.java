package uts.edu.co.vista;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public JTextField txtUsuario = new JTextField(15);
    public JPasswordField txtPassword = new JPasswordField(15);
    public JButton btnIngresar = new JButton("Autenticar");

    public LoginView() {
        setTitle("Acceso Seguro - UTS");
        setSize(360, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 8, 8));

        add(new JLabel("  Nombre de Usuario:"));
        add(txtUsuario);
        add(new JLabel("  Clave secreta:"));
        add(txtPassword);
        add(new JLabel(""));
        add(btnIngresar);
    }
}