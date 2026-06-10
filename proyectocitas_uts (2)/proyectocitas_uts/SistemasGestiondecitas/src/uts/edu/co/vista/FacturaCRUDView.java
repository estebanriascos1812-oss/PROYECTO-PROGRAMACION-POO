package uts.edu.co.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FacturaCRUDView extends JFrame {
    public JComboBox<String> cbCitas = new JComboBox<>();
    public JTextField txtFecha = new JTextField("2026-06-10", 10);
    public JTextField txtTotal = new JTextField("50000", 10);
    public JComboBox<String> cbEstado = new JComboBox<>(new String[]{"Pagada", "Pendiente"});
    
    public JButton btnFacturar = new JButton("Generar Cobro");
    public JTable tablaFacturas = new JTable();
    public DefaultTableModel modeloTabla = new DefaultTableModel();

    public FacturaCRUDView() {
        setTitle("Módulo de Facturación y Cobros - UTS");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelEntradas = new JPanel(new GridLayout(5, 2, 5, 5));
        panelEntradas.add(new JLabel("  Seleccione la Cita:")); panelEntradas.add(cbCitas);
        panelEntradas.add(new JLabel("  Fecha Emisión:")); panelEntradas.add(txtFecha);
        panelEntradas.add(new JLabel("  Valor Total ($):")); panelEntradas.add(txtTotal);
        panelEntradas.add(new JLabel("  Estado del Pago:")); panelEntradas.add(cbEstado);
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnFacturar);
        panelEntradas.add(panelBoton);
        add(panelEntradas, BorderLayout.NORTH);

        modeloTabla.addColumn("N° Factura");
        modeloTabla.addColumn("Paciente");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Monto");
        modeloTabla.addColumn("Estado");
        tablaFacturas.setModel(modeloTabla);
        add(new JScrollPane(tablaFacturas), BorderLayout.CENTER);
    }
}