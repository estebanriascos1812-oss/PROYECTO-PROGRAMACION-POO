package uts.edu.co.controlador;

import uts.edu.co.modelo.PacienteDAO;
import uts.edu.co.modelo.Persona;
import uts.edu.co.vista.PacienteCRUDView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PacienteController implements ActionListener {
    private PacienteCRUDView vistaCrud;
    private PacienteDAO dao;

    public PacienteController(PacienteCRUDView vista) {
        this.vistaCrud = vista;
        this.dao = new PacienteDAO();
        
        this.vistaCrud.btnRegistrar.addActionListener(this);
        this.vistaCrud.btnEliminar.addActionListener(this);
        
        actualizarEsquemaTabla();
        this.vistaCrud.setVisible(true);
    }

    private void actualizarEsquemaTabla() {
        vistaCrud.modeloTabla.setRowCount(0);
        List<Object[]> registros = dao.listarPacientes();
        for (Object[] fila : registros) {
            vistaCrud.modeloTabla.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCrud.btnRegistrar) {
            Persona p = new Persona();
            p.setCedula(vistaCrud.txtCedula.getText());
            p.setNombre(vistaCrud.txtNombre.getText());
            p.setApellido(vistaCrud.txtApellido.getText());
            p.setEmail(vistaCrud.txtEmail.getText());
            p.setPassword(vistaCrud.txtPassword.getText());
            
            String fechaNac = vistaCrud.txtFechaNac.getText();
            String tel = vistaCrud.txtTelefono.getText();
            String dir = vistaCrud.txtDireccion.getText();
            
            if (dao.insertar(p, fechaNac, tel, dir)) {
                JOptionPane.showMessageDialog(vistaCrud, "¡Paciente Guardado!");
                actualizarEsquemaTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vistaCrud, "Error al guardar el paciente.");
            }
        }
        
        if (e.getSource() == vistaCrud.btnEliminar) {
            int filaSeleccionada = vistaCrud.tablaDatos.getSelectedRow();
            
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vistaCrud, "Por favor, seleccione un paciente de la tabla para eliminar.");
                return;
            }
            
            int confirmar = JOptionPane.showConfirmDialog(vistaCrud, "¿Está seguro de eliminar este paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
            
            if (confirmar == JOptionPane.YES_OPTION) {
                int idPaciente = (int) vistaCrud.tablaDatos.getValueAt(filaSeleccionada, 0);
                
                if (dao.eliminar(idPaciente)) {
                    JOptionPane.showMessageDialog(vistaCrud, "¡Paciente Eliminado!");
                    actualizarEsquemaTabla();
                } else {
                    JOptionPane.showMessageDialog(vistaCrud, "Error al eliminar el paciente.");
                }
            }
        }
    }

    private void limpiarCampos() {
        vistaCrud.txtCedula.setText("");
        vistaCrud.txtNombre.setText("");
        vistaCrud.txtApellido.setText("");
        vistaCrud.txtEmail.setText("");
        vistaCrud.txtPassword.setText("");
        vistaCrud.txtFechaNac.setText("");
        vistaCrud.txtTelefono.setText("");
        vistaCrud.txtDireccion.setText("");
    }
}