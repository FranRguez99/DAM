package safa.fran.ad_proyectohospital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Controlador de nuestra interfaz gráfica
 *
 * @author Francisco Rodríguez Espinosa
 */

public class ControllerHospital {

    @FXML
    private Button bIngresosBorrar;

    @FXML
    private Button bIngresosEditar;

    @FXML
    private Button bIngresosNuevo;

    @FXML
    private Button bIngresosVolver;

    @FXML
    private Button bMainIngresos;

    @FXML
    private Button bMainMedicos;

    @FXML
    private Button bMainPacientes;

    @FXML
    private Button bMedicosAceptar;

    @FXML
    private Button bMedicosBorrar;

    @FXML
    private Button bMedicosCancelar;

    @FXML
    private Button bMedicosEditar;

    @FXML
    private Button bMedicosNuevo;

    @FXML
    private Button bMedicosVolver;

    @FXML
    private Button bPacientesAceptar;

    @FXML
    private Button bPacientesAceptar11;

    @FXML
    private Button bPacientesBorrar;

    @FXML
    private Button bPacientesCancelar;

    @FXML
    private Button bPacientesCancelar11;

    @FXML
    private Button bPacientesEditar;

    @FXML
    private Button bPacientesNuevo;

    @FXML
    private Button bPacientesVolver;

    @FXML
    private ComboBox<?> cbMedico;

    @FXML
    private ComboBox<?> cbPaciente;

    @FXML
    private Pane containerPane;

    @FXML
    private Pane formIngresosPane;

    @FXML
    private Pane formMedicosPane;

    @FXML
    private Pane formPacientesHeaderPane;

    @FXML
    private Pane formPacientesPane;

    @FXML
    private Pane ingresosHeaderPane;

    @FXML
    private Pane ingresosPane;

    @FXML
    private TableView<?> ingresosTable;

    @FXML
    private Pane mainPane;

    @FXML
    private Pane medicosHeaderPane;

    @FXML
    private Pane medicosPane;

    @FXML
    private TableView<?> medicosTable;

    @FXML
    private Pane pacientesHeaderPane;

    @FXML
    private Pane pacientesPane;

    @FXML
    private TableView<?> pacientesTable;

    @FXML
    private TextField tfApellidoMedico;

    @FXML
    private TextField tfApellidoPaciente;

    @FXML
    private TextField tfCargo;

    @FXML
    private TextField tfDomicilio;

    @FXML
    private TextField tfEspecialidad;

    @FXML
    private TextField tfFechaIngreso;

    @FXML
    private TextField tfIdMedico;

    @FXML
    private TextField tfNombreMedico;

    @FXML
    private TextField tfNombrePaciente;

    @FXML
    private TextField tfNumCama;

    @FXML
    private TextField tfNumColegiado;

    @FXML
    private TextField tfNumHistorial;

    @FXML
    private TextField tfNumPlanta;

    @FXML
    private TextField tfObservacionesIngresos;

    @FXML
    private TextField tfObservacionesMedicos;

    @FXML
    private TextField tfObservacionesPacientes;

    @FXML
    private TextField tfPoblacion;

    @FXML
    private TextField tfProcedencia;

    @FXML
    private TextField tfProvincia;

    @FXML
    private TextField tfSegSocial;

    @FXML
    private TextField tfTelefono;

    /* Variables de control para la edición de datos */
    private boolean editaPaciente;
    private boolean editaMedico;
    private boolean editaIngreso;

    @FXML
    void aceptarIngreso(ActionEvent event) {

    }

    @FXML
    void aceptarMedico(ActionEvent event) {

    }

    @FXML
    void aceptarPaciente(ActionEvent event) {

    }

    @FXML
    void borrarIngreso(ActionEvent event) {

    }

    @FXML
    void borrarMedico(ActionEvent event) {

    }

    @FXML
    void borrarPaciente(ActionEvent event) {

    }

    @FXML
    void cancelarIngreso(ActionEvent event) {

    }

    @FXML
    void cancelarMedico(ActionEvent event) {

    }

    @FXML
    void cancelarPaciente(ActionEvent event) {

    }

    @FXML
    void editarIngreso(ActionEvent event) {

    }

    @FXML
    void editarMedico(ActionEvent event) {

    }

    @FXML
    void editarPaciente(ActionEvent event) {

    }

    /**
     * Accede al formulario de creación o edición de ingresos en nuestra interfaz
     */
    @FXML
    void nuevoIngreso(ActionEvent event) {
        editaPaciente = false;
        pacientesPane.setVisible(false);
        formPacientesPane.setVisible(true);
    }

    /**
     * Accede al formulario de creación o edición de médicos en nuestra interfaz
     */
    @FXML
    void nuevoMedico(ActionEvent event) {
        editaMedico = false;
        medicosPane.setVisible(false);
        formMedicosPane.setVisible(true);
    }

    /**
     * Accede al formulario de creación o edición de pacientes en nuestra interfaz
     */
    @FXML
    void nuevoPaciente(ActionEvent event) {
        editaPaciente = false;
        pacientesPane.setVisible(false);
        formPacientesPane.setVisible(true);
    }

    /**
     * Muestra el panel de ingresos en nuestra interfaz
     */
    @FXML
    void verIngresos() {
        mainPane.setVisible(false);
        ingresosPane.setVisible(true);
    }

    /**
     * Muestra el panel de médicos en nuestra interfaz
     */
    @FXML
    void verMedicos() {
        mainPane.setVisible(false);
        medicosPane.setVisible(true);
    }

    /**
     * Muestra el panel de pacientes en nuestra interfaz
     */
    @FXML
    void verPacientes() {
        mainPane.setVisible(false);
        pacientesPane.setVisible(true);
    }

    /**
     * Vuelve al menú principal
     */
    @FXML
    void volver() {
        pacientesPane.setVisible(false);
        medicosPane.setVisible(false);
        ingresosPane.setVisible(false);
        mainPane.setVisible(true);
    }

}
