package uts.edu.co.controlador;

import uts.edu.co.modelo.CitaDAO;
import uts.edu.co.modelo.PacienteDAO;
import uts.edu.co.modelo.DoctorDAO;
import uts.edu.co.vista.CitaCRUDView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CitaController implements ActionListener {
    private CitaCRUDView vista;
    private CitaDAO dao;
    private List<Integer> idPacientesLista = new ArrayList<>();
    private List<Integer> idDoctoresLista = new ArrayList<>();

    public CitaController(CitaCRUDView vista) {
        this.vista = vista;
        this.dao = new CitaDAO();
        
        this.vista.btnAsignar.addActionListener(this);
        cargarDesplegables();
        actualizarTabla();
        this.vista.setVisible(true);
    }

    private void cargarDesplegables() {
        vista.cbPacientes.removeAllItems();
        idPacientesLista.clear();
        PacienteDAO pDAO = new PacienteDAO();
        for (Object[] p : pDAO.listarPacientes()) {
            idPacientesLista.add((Integer) p[0]); // Guarda ID
            vista.cbPacientes.addItem("[" + p[1] + "] " + p[2]); // Cédula y Nombre
        }

        vista.cbDoctores.removeAllItems();
        idDoctoresLista.clear();
        DoctorDAO dDAO = new DoctorDAO();
        for (Object[] d : dDAO.listarDoctores()) {
            idDoctoresLista.add((Integer) d[0]); // Guarda ID
            vista.cbDoctores.addItem("Dr(a). " + d[2] + " (" + d[4] + ")"); // Nombre y Especialidad
        }
    }

    private void actualizarTabla() {
        vista.modeloTabla.setRowCount(0);
        List<Object[]> registros = dao.listarCitas();
        for (Object[] fila : registros) {
            vista.modeloTabla.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAsignar) {
            if (vista.cbPacientes.getSelectedIndex() == -1 || vista.cbDoctores.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(vista, "Debe registrar primero Pacientes y Doctores en el sistema.");
                return;
            }

            int idPaciente = idPacientesLista.get(vista.cbPacientes.getSelectedIndex());
            int idDoctor = idDoctoresLista.get(vista.cbDoctores.getSelectedIndex());
            String fechaHora = vista.txtFechaHora.getText();
            String motivo = vista.txtMotivo.getText();
            String estado = vista.cbEstado.getSelectedItem().toString();

            if (dao.insertarCita(idPaciente, idDoctor, fechaHora, motivo, estado)) {
                JOptionPane.showMessageDialog(vista, "¡Cita Médica Agendada Exitosamente!");
                actualizarTabla();
                vista.txtMotivo.setText("");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agendar la cita. Verifique la conexión.");
            }
        }
    }
}