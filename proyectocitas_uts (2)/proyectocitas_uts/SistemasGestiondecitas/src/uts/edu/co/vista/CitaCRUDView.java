package uts.edu.co.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CitaCRUDView extends JFrame {
    public JComboBox<String> cbPacientes = new JComboBox<>();
    public JComboBox<String> cbDoctores = new JComboBox<>();
    public JTextField txtFechaHora = new JTextField("2026-06-15 08:00", 15);
    public JTextField txtMotivo = new JTextField(20);
    public JComboBox<String> cbEstado = new JComboBox<>(new String[]{"Pendiente", "Completada", "Cancelada"});
    
    public JButton btnAsignar = new JButton("Agendar Cita Médica");
    public JTable tablaCitas = new JTable();
    public DefaultTableModel modeloTabla = new DefaultTableModel();

    public CitaCRUDView() {
        setTitle("Módulo de Asignación de Citas Médicas - UTS");
        setSize(850, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        JPanel panelEntradas = new JPanel(new GridLayout(6, 2, 6, 6));
        panelEntradas.add(new JLabel("  Seleccione Paciente:")); panelEntradas.add(cbPacientes);
        panelEntradas.add(new JLabel("  Seleccione Médico Especialista:")); panelEntradas.add(cbDoctores);
        panelEntradas.add(new JLabel("  Fecha y Hora (AAAA-MM-DD HH:MM):")); panelEntradas.add(txtFechaHora);
        panelEntradas.add(new JLabel("  Motivo de la Consulta:")); panelEntradas.add(txtMotivo);
        panelEntradas.add(new JLabel("  Estado Inicial:")); panelEntradas.add(cbEstado);
        
        JPanel panelAcciones = new JPanel(new FlowLayout());
        panelAcciones.add(btnAsignar);
        panelEntradas.add(panelAcciones);

        add(panelEntradas, BorderLayout.NORTH);

        modeloTabla.addColumn("ID Cita");
        modeloTabla.addColumn("Paciente");
        modeloTabla.addColumn("Médico Asignado");
        modeloTabla.addColumn("Fecha / Hora");
        modeloTabla.addColumn("Motivo");
        modeloTabla.addColumn("Estado Cita");
        tablaCitas.setModel(modeloTabla);
        
        add(new JScrollPane(tablaCitas), BorderLayout.CENTER);
    }
}