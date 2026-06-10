package uts.edu.co.controlador;

import uts.edu.co.vista.MainMenuView;
import uts.edu.co.vista.PacienteCRUDView;
import uts.edu.co.vista.DoctorCRUDView;
import uts.edu.co.vista.CitaCRUDView;
import uts.edu.co.vista.FacturaCRUDView; // Importación de la vista de facturas
import javax.swing.*;

public class MainMenuController {
    private MainMenuView vistaMenu;

    public MainMenuController(MainMenuView vista) {
        this.vistaMenu = vista;
        inicializarEventos();
        this.vistaMenu.setVisible(true);
    }

    private void inicializarEventos() {
        // Evento para abrir el formulario de Pacientes
        vistaMenu.itemPacientes.addActionListener(e -> {
            PacienteCRUDView vistaCrud = new PacienteCRUDView();
            new PacienteController(vistaCrud);
        });

        // Evento para abrir el formulario de Médicos (Doctores)
        vistaMenu.itemMedicos.addActionListener(e -> {
            DoctorCRUDView vistaDoc = new DoctorCRUDView();
            new DoctorController(vistaDoc);
        });

        // Evento para abrir el Módulo de Citas
        vistaMenu.itemCitas.addActionListener(e -> {
            CitaCRUDView vistaCita = new CitaCRUDView();
            new CitaController(vistaCita);
        });

        // Evento para abrir el Módulo de Facturación
        vistaMenu.itemFacturas.addActionListener(e -> {
            FacturaCRUDView vistaFact = new FacturaCRUDView();
            new FacturaController(vistaFact);
        });

        // Evento para cerrar la sesión del empleado de forma segura
        vistaMenu.itemSalir.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "La sesión de empleado ha finalizado exitosamente.");
            System.exit(0);
        });
    }
}