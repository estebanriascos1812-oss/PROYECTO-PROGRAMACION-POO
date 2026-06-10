package uts.edu.co.vista;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
    private String rolActivo;
    public JMenuBar barraMenu = new JMenuBar();
    public JMenu menuPrincipal = new JMenu("Gestión del Menú");
    public JMenuItem itemPacientes = new JMenuItem("Usuarios / Pacientes (CRUD)");
    public JMenuItem itemMedicos = new JMenuItem("Personal Médico (CRUD)");
    public JMenuItem itemCitas = new JMenuItem("Asignación de Citas Médicas");
    public JMenuItem itemFacturas = new JMenuItem("Facturación y Pagos"); // Última adición del diagrama
    public JMenuItem itemSalir = new JMenuItem("Cerrar Sesión Activa");

    public MainMenuView(String rol) {
        this.rolActivo = rol;
        setTitle("Panel de Control - Perfil: " + rolActivo);
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Agregamos todos los módulos en orden al menú desplegable
        menuPrincipal.add(itemPacientes);
        menuPrincipal.add(itemMedicos); 
        menuPrincipal.add(itemCitas);
        menuPrincipal.add(itemFacturas);
        menuPrincipal.addSeparator();
        menuPrincipal.add(itemSalir);
        
        barraMenu.add(menuPrincipal);
        setJMenuBar(barraMenu);

        // Letrero de bienvenida UTS
        JLabel lblBienvenido = new JLabel("<html><center><h1>SISTEMA UTS</h1><h3>Conexión Establecida con Éxito</h3></center></html>", SwingConstants.CENTER);
        add(lblBienvenido, BorderLayout.CENTER);
    }
}