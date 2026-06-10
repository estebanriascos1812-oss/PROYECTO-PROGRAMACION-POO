package uts.edu.co.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PacienteCRUDView extends JFrame {
    public JTextField txtCedula = new JTextField(10);
    public JTextField txtNombre = new JTextField(15);
    public JTextField txtApellido = new JTextField(15);
    public JTextField txtEmail = new JTextField(15);
    public JTextField txtPassword = new JPasswordField(10);
    public JTextField txtFechaNac = new JTextField(10);
    public JTextField txtTelefono = new JTextField(10);
    public JTextField txtDireccion = new JTextField(15);
    
    public JButton btnRegistrar = new JButton("Registrar Paciente");
    public JButton btnEliminar = new JButton("Eliminar Seleccionado");
    public JTable tablaDatos = new JTable();
    public DefaultTableModel modeloTabla = new DefaultTableModel();

    public PacienteCRUDView() {
        setTitle("Administración de Pacientes - UTS");
        setSize(850, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        JPanel panelEntradas = new JPanel(new GridLayout(9, 2, 6, 6));
        panelEntradas.add(new JLabel("  Cédula:")); panelEntradas.add(txtCedula);
        panelEntradas.add(new JLabel("  Nombre:")); panelEntradas.add(txtNombre);
        panelEntradas.add(new JLabel("  Apellido:")); panelEntradas.add(txtApellido);
        panelEntradas.add(new JLabel("  Correo Electrónico:")); panelEntradas.add(txtEmail);
        panelEntradas.add(new JLabel("  Contraseña:")); panelEntradas.add(txtPassword);
        panelEntradas.add(new JLabel("  Fecha Nacimiento (AAAA-MM-DD):")); panelEntradas.add(txtFechaNac);
        panelEntradas.add(new JLabel("  Teléfono:")); panelEntradas.add(txtTelefono);
        panelEntradas.add(new JLabel("  Dirección:")); panelEntradas.add(txtDireccion);
        
        JPanel panelAcciones = new JPanel(new FlowLayout());
        panelAcciones.add(btnRegistrar);
        panelAcciones.add(btnEliminar);
        panelEntradas.add(panelAcciones);

        add(panelEntradas, BorderLayout.NORTH);

        modeloTabla.addColumn("ID Paciente");
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Nombre Completo");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Dirección");
        tablaDatos.setModel(modeloTabla);
        
        add(new JScrollPane(tablaDatos), BorderLayout.CENTER);
    }
}