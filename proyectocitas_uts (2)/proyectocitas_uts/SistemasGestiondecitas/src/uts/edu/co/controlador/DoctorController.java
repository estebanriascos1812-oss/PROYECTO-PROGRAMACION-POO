package uts.edu.co.controlador;

import uts.edu.co.modelo.DoctorDAO;
import uts.edu.co.modelo.Persona;
import uts.edu.co.vista.DoctorCRUDView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DoctorController implements ActionListener {
    private DoctorCRUDView vistaCrud;
    private DoctorDAO dao;

    public DoctorController(DoctorCRUDView vista) {
        this.vistaCrud = vista;
        this.dao = new DoctorDAO();
        
        this.vistaCrud.btnRegistrar.addActionListener(this);
        this.vistaCrud.btnEliminar.addActionListener(this);
        
        actualizarEsquemaTabla();
        this.vistaCrud.setVisible(true);
    }

    private void actualizarEsquemaTabla() {
        vistaCrud.modeloTabla.setRowCount(0);
        List<Object[]> registros = dao.listarDoctores();
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
            
            String esp = vistaCrud.txtEspecialidad.getText();
            String lic = vistaCrud.txtLicencia.getText();
            
            if (dao.insertar(p, esp, lic)) {
                JOptionPane.showMessageDialog(vistaCrud, "¡Doctor Guardado!");
                actualizarEsquemaTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vistaCrud, "Error al guardar el doctor.");
            }
        }
        
        if (e.getSource() == vistaCrud.btnEliminar) {
            int filaSeleccionada = vistaCrud.tablaDatos.getSelectedRow();
            
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vistaCrud, "Por favor, seleccione un doctor de la tabla para eliminar.");
                return;
            }
            
            int confirmar = JOptionPane.showConfirmDialog(vistaCrud, "¿Está seguro de eliminar este doctor?", "Confirmar", JOptionPane.YES_NO_OPTION);
            
            if (confirmar == JOptionPane.YES_OPTION) {
                int idDoctor = (int) vistaCrud.tablaDatos.getValueAt(filaSeleccionada, 0);
                
                if (dao.eliminar(idDoctor)) {
                    JOptionPane.showMessageDialog(vistaCrud, "¡Doctor Eliminado!");
                    actualizarEsquemaTabla();
                } else {
                    JOptionPane.showMessageDialog(vistaCrud, "Error al eliminar el doctor.");
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
        vistaCrud.txtEspecialidad.setText("");
        vistaCrud.txtLicencia.setText("");
    }
}