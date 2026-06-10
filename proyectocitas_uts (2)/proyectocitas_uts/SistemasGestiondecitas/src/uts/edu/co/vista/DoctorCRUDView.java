package uts.edu.co.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DoctorCRUDView extends JFrame {
    public JTextField txtCedula = new JTextField(10);
    public JTextField txtNombre = new JTextField(15);
    public JTextField txtApellido = new JTextField(15);
    public JTextField txtEmail = new JTextField(15);
    public JTextField txtPassword = new JPasswordField(10);
    public JTextField txtEspecialidad = new JTextField(15);
    public JTextField txtLicencia = new JTextField(15);
    
    public JButton btnRegistrar = new JButton("Registrar Doctor");
    public JButton btnEliminar = new JButton("Eliminar Seleccionado"); // Botón añadido
    public JTable tablaDatos = new JTable();
    public DefaultTableModel modeloTabla = new DefaultTableModel();

    public DoctorCRUDView() {
        setTitle("Administración de Personal Médico - UTS");
        setSize(850, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        JPanel panelEntradas = new JPanel(new GridLayout(8, 2, 6, 6));
        panelEntradas.add(new JLabel("  Cédula:")); panelEntradas.add(txtCedula);
        panelEntradas.add(new JLabel("  Nombre:")); panelEntradas.add(txtNombre);
        panelEntradas.add(new JLabel("  Apellido:")); panelEntradas.add(txtApellido);
        panelEntradas.add(new JLabel("  Correo Electrónico:")); panelEntradas.add(txtEmail);
        panelEntradas.add(new JLabel("  Contraseña de Acceso:")); panelEntradas.add(txtPassword);
        panelEntradas.add(new JLabel("  Especialidad:")); panelEntradas.add(txtEspecialidad);
        panelEntradas.add(new JLabel("  Número de Licencia:")); panelEntradas.add(txtLicencia);
        
        JPanel panelAcciones = new JPanel(new FlowLayout());
        panelAcciones.add(btnRegistrar);
        panelAcciones.add(btnEliminar);
        panelEntradas.add(panelAcciones);

        add(panelEntradas, BorderLayout.NORTH);

        modeloTabla.addColumn("ID Doctor");
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Nombre Médico");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Especialidad");
        modeloTabla.addColumn("N° Licencia");
        tablaDatos.setModel(modeloTabla);
        
        add(new JScrollPane(tablaDatos), BorderLayout.CENTER);
    }
}