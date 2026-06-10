package uts.edu.co.controlador;

import uts.edu.co.modelo.FacturaDAO;
import uts.edu.co.modelo.CitaDAO;
import uts.edu.co.vista.FacturaCRUDView;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaController {
    private FacturaCRUDView vista;
    private FacturaDAO dao;
    private List<Integer> idCitasLista = new ArrayList<>();

    public FacturaController(FacturaCRUDView vista) {
        this.vista = vista;
        this.dao = new FacturaDAO();
        
        cargarCitas();
        actualizarTabla();
        
        this.vista.btnFacturar.addActionListener(e -> {
            if(vista.cbCitas.getSelectedIndex() == -1) return;
            
            int idCita = idCitasLista.get(vista.cbCitas.getSelectedIndex());
            String fecha = vista.txtFecha.getText();
            double total = Double.parseDouble(vista.txtTotal.getText());
            String estado = vista.cbEstado.getSelectedItem().toString();
            
            if(dao.generarFactura(idCita, fecha, total, estado)){
                JOptionPane.showMessageDialog(vista, "¡Factura generada exitosamente!");
                actualizarTabla();
            }
        });
        
        this.vista.setVisible(true);
    }

    private void cargarCitas() {
        vista.cbCitas.removeAllItems();
        idCitasLista.clear();
        CitaDAO citaDAO = new CitaDAO();
        for (Object[] c : citaDAO.listarCitas()) {
            idCitasLista.add((Integer) c[0]); // Guarda ID de la cita
            vista.cbCitas.addItem("Cita #" + c[0] + " - " + c[1]); // Muestra ID y Paciente
        }
    }

    private void actualizarTabla() {
        vista.modeloTabla.setRowCount(0);
        for (Object[] f : dao.listarFacturas()) {
            vista.modeloTabla.addRow(f);
        }
    }
}