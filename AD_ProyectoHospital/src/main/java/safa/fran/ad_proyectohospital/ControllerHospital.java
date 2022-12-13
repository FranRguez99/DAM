package safa.fran.ad_proyectohospital;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Date;

/**
 * Controlador de nuestra interfaz gráfica
 *
 * @author Francisco Rodríguez Espinosa
 */

public class ControllerHospital implements Initializable {

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
    private ComboBox<String> cbMedico;

    @FXML
    private ComboBox<String> cbPaciente;

    @FXML
    private ComboBox<String> cbProvincia;

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
    private TableView<ObservableList> ingresosTable;

    @FXML
    private Pane mainPane;

    @FXML
    private Pane medicosHeaderPane;

    @FXML
    private Pane medicosPane;

    @FXML
    private TableView<ObservableList> medicosTable;

    @FXML
    private Pane pacientesHeaderPane;

    @FXML
    private Pane pacientesPane;

    @FXML
    private TableView<ObservableList> pacientesTable;

    @FXML
    private TextField tfApellidoMedico;

    @FXML
    private TextField tfApellidoPaciente;

    @FXML
    private TextField tfCargo;

    @FXML
    private TextField tfCodPostal;

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
    private TextField tfSegSocial;

    @FXML
    private TextField tfTelefono;

    // Variables de control para la edición de datos
    private boolean editaPaciente;
    private boolean editaMedico;
    private boolean editaIngreso;

    // Variables para almacenar las claves primarias cuando carguemos datos
    private String seg_social;
    private String id_medico;
    private String id_ingreso;

    // Conexión con la BD
    Connection con;

    @FXML
    void aceptarIngreso(ActionEvent event) {
        reseteaIngresos();
        try { // Leemos los datos de todos los campos y comprobamos los errores
            // Flag para lanzar el mensaje de error
            boolean error = false;
            // Variables para guardar el mensaje de error y los valores del formulario
            String mensajeError = "Error en los siguientes campos:\n";
            String procedencia, fecha_ingreso, num_planta, num_cama, observaciones, paciente = null, medico = null;
            // Guardamos los valores del formulario
            procedencia = tfPoblacion.getText();
            fecha_ingreso = tfFechaIngreso.getText();
            num_planta = tfNumPlanta.getText();
            num_cama = tfNumCama.getText();
            observaciones = tfObservacionesIngresos.getText();
            // Comprobaciones
            if (procedencia.length() == 0 || procedencia.length() > 20) { // Procedencia
                tfProcedencia.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " PROCEDENCIA (Máximo 20 caracteres)\n";
            }
            if (!esFecha(fecha_ingreso)){ // Fecha ingreso
                tfFechaIngreso.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " FECHA INGRESO (Formato dd-MM-yyyy y anterior o igual a la fecha actual)\n";
            }
            if (num_planta.length() < 3 || !esNumerico(num_planta)) { // Número planta
                tfNumPlanta.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " NÚMERO PLANTA (Máximo 2 dígitos)\n";
            }
            if (num_cama.length() < 4 || !esNumerico(num_cama)) { // Número cama
                tfNumCama.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " NÚMERO CAMA (Máximo 3 dígitos)\n";
            }
            if (observaciones.length() > 240) { // Observaciones
                tfObservacionesIngresos.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " OBSERVACIONES (Máximo 240 caracteres)";
            }
            try{
                ResultSet rsPaciente = con.createStatement().executeQuery("SELECT seg_social FROM pacientes WHERE " + "apellidos = '" + cbPaciente.getSelectionModel().getSelectedItem().split(",")[0] + "' AND nombre" + " = '" + cbPaciente.getSelectionModel().getSelectedItem().split(",")[1].replace(" ", "") + "'");
                while (rsPaciente.next()) {
                    paciente = rsPaciente.getObject(1).toString();
                }
            } catch (ArrayIndexOutOfBoundsException exception){
                cbPaciente.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " PACIENTE (Debe seleccionar un paciente)";
            }
            try{
                ResultSet rsMedico = con.createStatement().executeQuery("SELECT id_medico FROM medicos WHERE " + "" + "apellidos = '" + cbMedico.getSelectionModel().getSelectedItem().split(",")[0] + "' AND " + "nombre" + " = '" + cbMedico.getSelectionModel().getSelectedItem().split(",")[1].replace(" ", "") + "'");
                while (rsMedico.next()) {
                    medico = rsMedico.getObject(1).toString();
                }
            } catch (ArrayIndexOutOfBoundsException exception){
                cbMedico.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " PACIENTE (Debe seleccionar un médico)";
            }

            //Mostramos mensaje de error en caso de que lo hubiera
            if (error) {
                ventanaError(mensajeError);
            } else {
                // Comprobamos con la variable editaIngreso si haremos update o insert
                if(!editaIngreso){ // INSERT
                    PreparedStatement insertQuery = con.prepareStatement("INSERT INTO ingresos (procedencia, fecha_ingreso," +
                            " num_planta, num_cama, observaciones, paciente, medico) VALUES (?,?,?,?,?,?,?)");
                    construyeQueryIngreso(procedencia, fecha_ingreso, num_planta, num_cama, observaciones, paciente, medico, insertQuery);

                    if (insertQuery.executeUpdate() == 1) {
                        // Ventana de confirmación
                        ventanaDialogo("INSERTAR INGRESO", "Ingreso insertado con éxito");
                        actualizaTabla("INGRESOS", ingresosTable);
                    }
                } else { // UPDATE
                    PreparedStatement updateQuery = con.prepareStatement("UPDATE ingresos SET procedencia = ?, fecha_ingreso = ?, num_planta = ?, " +
                            "num_cama = ?, observaciones = ? WHERE id_ingreso = " + id_ingreso);
                    // Construimos la query
                    construyeQueryIngreso(procedencia, fecha_ingreso, num_planta, num_cama, observaciones, paciente, medico, updateQuery);
                    if(updateQuery.executeUpdate() == 1){
                        // Ventana de confirmación
                        ventanaDialogo("ACTUALIZAR INGRESO", "Ingreso actualizado con éxito");
                        actualizaTabla("INGRESOS", ingresosTable);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
        }

    }



    /**
     * Función que controla el ingreso o actualización de los médicos, comprobando que todos los campos sean correctos
     */
    @FXML
    void aceptarMedico() {
        reseteaMedicos();
        try { // Se leen los campos y se comprueba que son correctos
            // Flag para mensaje de error
            boolean error = false;
            // Variables para guardar el mensaje de error y los valores del formulario
            String mensajeError = "Error en los siguientes campos:\n";
            String nombre, apellidos, especialidad, num_colegiado, cargo, observaciones;

            // Guardamos los valores del formulario
            nombre = tfNombreMedico.getText();
            apellidos = tfApellidoMedico.getText();
            especialidad = tfEspecialidad.getText();
            num_colegiado = tfNumColegiado.getText();
            cargo = tfCargo.getText();
            observaciones = tfObservacionesMedicos.getText();

            // Comprobaciones
            if (nombre.length() == 0 || nombre.length() > 20) { // Nombre
                tfNombreMedico.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " NOMBRE MÉDICO (Máximo 20 caracteres)\n";
            }
            if (apellidos.length() == 0 || apellidos.length() > 50) { // Apellidos
                tfApellidoMedico.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " APELLIDOS MÉDICO (Máximo 50 caracteres)\n";
            }
            if (especialidad.length() == 0 || especialidad.length() > 20) { // Especialidad
                tfEspecialidad.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " ESPECIALIDAD (Máximo 20 caracteres)\n";
            }
            if (num_colegiado.length() != 9 || !esNumerico(num_colegiado)) { // Número colegiado
                tfNumColegiado.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " NÚMERO COLEGIADO (Debe tener 9 dígitos)\n";
            }
            if (cargo.length() == 0 || cargo.length() > 20) { // Cargo
                tfCargo.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " CARGO (Máximo 20 caracteres)\n";
            }
            if (observaciones.length() > 240) { // Observaciones
                tfObservacionesMedicos.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " OBSERVACIONES (Máximo 240 caracteres)";
            }
            // Mostramos mensaje de error en caso de que lo hubiera
            if (error) {
                ventanaError(mensajeError);
            } else {
                // Comprobamos con la variable editaMedico si haremos update o insert
                if (!editaMedico) { // INSERT
                    PreparedStatement insertQuery = con.prepareStatement("INSERT INTO medicos (nombre, apellidos, " + "especialidad, num_colegiado, cargo, observaciones) VALUES (?,?,?,?,?,?)");
                    // Construimos la query
                    construyeQueryMedico(nombre, apellidos, especialidad, num_colegiado, cargo, observaciones, insertQuery);

                    if (insertQuery.executeUpdate() == 1) {
                        // Ventana de confirmación
                        ventanaDialogo("INSERTAR MÉDICO", "Médico insertado con éxito");
                        actualizaTabla("MEDICOS", medicosTable);
                    }
                } else { // UPDATE
                    PreparedStatement updateQuery = con.prepareStatement("UPDATE medicos SET nombre = ?, apellidos = ?," +
                            "especialidad = ?, num_colegiado = ?, cargo = ?, observaciones = ? WHERE id_medico = " + id_medico);
                    // Construimos la query
                    construyeQueryMedico(nombre, apellidos, especialidad, num_colegiado, cargo, observaciones, updateQuery);
                    if (updateQuery.executeUpdate() == 1) {
                        // Ventana de confirmación
                        ventanaDialogo("ACTUALIZAR MÉDICO", "Paciente actualizado con éxito");
                        actualizaTabla("MEDICOS", medicosTable);
                        cargaComboBox(cbMedico, "medicos");
                    }
                }

                formMedicosPane.setVisible(false);
                medicosPane.setVisible(true);
            }

        } catch (Exception e) {
            System.out.println(e.getClass());
        }

    }

    /**
     * Función que controla el ingreso o actualización de los pacientes, comprobando que todos los campos sean correctos
     */
    @FXML
    void aceptarPaciente() {
        reseteaPacientes();
        if (editaPaciente) {
            tfSegSocial.setEditable(false);
        }
        try { // Leemos los datos de todos los campos y comprobamos los errores
            // Flag para lanzar el mensaje de error
            boolean error = false;
            // Variables para guardar el mensaje de error y los valores del formulario
            String mensajeError = "Error en los siguientes campos:\n";
            String seg_social, nombrePaciente, apellidosPaciente, domicilio, poblacion, provincia, cod_postal, num_tlfn, num_historial, observaciones;

            // Guardamos los valores del formulario
            seg_social = tfSegSocial.getText();
            nombrePaciente = tfNombrePaciente.getText();
            apellidosPaciente = tfApellidoPaciente.getText();
            domicilio = tfDomicilio.getText();
            poblacion = tfPoblacion.getText();
            provincia = cbProvincia.getSelectionModel().getSelectedItem();
            cod_postal = tfCodPostal.getText();
            num_tlfn = tfTelefono.getText();
            num_historial = tfNumHistorial.getText();
            observaciones = tfObservacionesPacientes.getText();

            // Comprobaciones
            if (seg_social.length() != 12 || !esNumerico(seg_social)) { // Seguridad social
                tfSegSocial.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " SEGURIDAD SOCIAL (Debe tener 12 dígitos)\n";
            }
            if (nombrePaciente.length() == 0 || nombrePaciente.length() > 20) { // Nombre
                tfNombrePaciente.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " NOMBRE (Máximo 20 caracteres)\n";
            }
            if (apellidosPaciente.length() == 0 || apellidosPaciente.length() > 50) { // Apellidos
                tfApellidoPaciente.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " APELLIDOS (Máximo 50 caracteres)\n";
            }
            if (domicilio.length() == 0 || domicilio.length() > 20) { // Domicilio
                tfDomicilio.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " DOMICILIO (Máximo 20 caracteres)\n";
            }
            if (poblacion.length() == 0 || poblacion.length() > 20) { // Población
                tfPoblacion.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " POBLACIÓN (Máximo 20 caracteres)\n";
            }
            if (cod_postal.length() != 5 || !esNumerico(cod_postal)) { // Código Postal
                tfCodPostal.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " CÓDIGO POSTAL (Debe tener 5 dígitos)\n";
            }
            if (num_tlfn.length() != 9 || !esNumerico(num_tlfn)) { // Teléfono
                tfTelefono.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " TELÉFONO (Debe tener 9 dígitos)\n";
            }
            if (num_historial.length() != 12 || !esNumerico(num_historial)) { // Número de historial
                tfNumHistorial.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " NÚMERO DE HISTORIAL (Debe tener 12 dígitos)\n";
            }
            if (observaciones.length() > 240) { // Observaciones
                tfObservacionesPacientes.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " OBSERVACIONES (Máximo 240 caracteres)";
            }
            // Mostramos mensaje de error en caso de que lo hubiera
            if (error) {
                ventanaError(mensajeError);
            } else {
                // Comprobamos con la variable editaPaciente si haremos update o insert
                if (!editaPaciente) { // INSERT
                    PreparedStatement insertQuery = con.prepareStatement("INSERT INTO pacientes (seg_social, nombre," + "apellidos, domicilio, poblacion, provincia, num_tlfn, num_historial, observaciones) VALUES " + "(?,?,?,?,?,?,?,?,?)");
                    // Construimos la query
                    construyeQueryPaciente(seg_social, nombrePaciente, apellidosPaciente, domicilio, poblacion, provincia, num_tlfn, num_historial, observaciones, insertQuery);
                    try {
                        if (insertQuery.executeUpdate() == 1) {
                            // Ventana de confirmación
                            ventanaDialogo("INSERTAR PACIENTE", "Paciente insertado con éxito");
                            actualizaTabla("PACIENTES", pacientesTable);
                        }
                    } catch (SQLIntegrityConstraintViolationException exception) {
                        // Ventana de error
                        ventanaDialogo("ERROR", "Ese número de Seguridad Social ya se encuentra registrado");
                    }

                } else { // UPDATE
                    PreparedStatement updateQuery = con.prepareStatement("UPDATE pacientes SET nombre = ?, apellidos = ?," + "domicilio = ?, poblacion = ?, provincia = ?, num_tlfn = ?, num_historial = ?, observaciones = ?" + "WHERE seg_social = ?");
                    // Construimos la query
                    construyeQueryPaciente(nombrePaciente, apellidosPaciente, domicilio, poblacion, provincia, num_tlfn, num_historial, observaciones, seg_social, updateQuery);

                    if (updateQuery.executeUpdate() == 1) {
                        // Ventana de confirmación
                        ventanaDialogo("ACTUALIZAR PACIENTE", "Paciente actualizado con éxito");
                        actualizaTabla("PACIENTES", pacientesTable);
                        cargaComboBox(cbPaciente, "pacientes");
                    }
                }
                formPacientesPane.setVisible(false);
                pacientesPane.setVisible(true);
            }


        } catch (Exception e) {
            System.out.println(e.getClass());
        }

    }

    @FXML
    void borrarIngreso(ActionEvent event) {
        id_ingreso = seleccionTabla(ingresosTable);
    }

    /**
     * Borra un médico de nuestra base de datos
     */
    @FXML
    void borrarMedico(ActionEvent event) {
        try {
            id_medico = seleccionTabla(medicosTable);
            // Preparamos la consulta de eliminación
            PreparedStatement deleteQuery = con.prepareStatement("DELETE FROM medicos WHERE id_medico = " + id_medico);
            if (deleteQuery.executeUpdate() == 1) {
                // Ventana de confirmación
                ventanaDialogo("ELIMINAR MÉDICO", "Médico eliminado con éxito");
                actualizaTabla("MEDICOS", medicosTable);
            }
        } catch (NullPointerException npe) {
            ventanaDialogo("ERROR", "No hay ningún médico seleccionado");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Borra un paciente de nuestra base de datos
     */
    @FXML
    void borrarPaciente(ActionEvent event) {
        try {
            String seg_social = seleccionTabla(pacientesTable);
            System.out.println(seg_social);
            // Preparamos la consulta de eliminación
            PreparedStatement deleteQuery = con.prepareStatement("DELETE FROM pacientes WHERE seg_social = " + seg_social);
            if (deleteQuery.executeUpdate() == 1) {
                // Ventana de confirmación
                ventanaDialogo("ELIMINAR PACIENTE", "Paciente eliminado con éxito");
                actualizaTabla("PACIENTES", pacientesTable);
            }
        } catch (NullPointerException npe) {
            ventanaDialogo("ERROR", "No hay ningún paciente seleccionado");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Cancela la introducción o modificación del ingreso y vuelve a la vista de datos
     */
    @FXML
    void cancelarIngreso(ActionEvent event) {
        formIngresosPane.setVisible(false);
        ingresosPane.setVisible(true);
    }

    /**
     * Cancela la introducción o modificación del médico y vuelve a la vista de datos
     */
    @FXML
    void cancelarMedico(ActionEvent event) {
        formMedicosPane.setVisible(false);
        medicosPane.setVisible(true);
    }

    /**
     * Cancela la introducción o modificación del paciente y vuelve a la vista de datos
     */
    @FXML
    void cancelarPaciente(ActionEvent event) {
        formPacientesPane.setVisible(false);
        pacientesPane.setVisible(true);
    }

    @FXML
    void editarIngreso(ActionEvent event) {
        id_ingreso = seleccionTabla(ingresosTable);
    }

    /**
     * Carga el formulario de médicos con los datos del médico seleccionado en la tabla
     */
    @FXML
    void editarMedico(ActionEvent event) {
        try {
            id_medico = seleccionTabla(medicosTable);
            // Cargamos el paciente seleccionado de la base de datos
            ResultSet datosMedico = con.createStatement().executeQuery("SELECT * FROM medicos WHERE id_medico = " + id_medico);
            if (datosMedico.next()) { // Rellena los campos con los datos del paciente
                tfNombreMedico.setText(datosMedico.getObject(2).toString());
                tfApellidoMedico.setText(datosMedico.getObject(3).toString());
                tfEspecialidad.setText(datosMedico.getObject(4).toString());
                tfNumColegiado.setText(datosMedico.getObject(5).toString());
                tfCargo.setText(datosMedico.getObject(6).toString());
                tfObservacionesMedicos.setText(datosMedico.getObject(7).toString());
            }
            editaMedico = true;
            medicosPane.setVisible(false);
            formMedicosPane.setVisible(true);

        } catch (NullPointerException npe) {
            // Ventana de error
            ventanaDialogo("ERROR", "No hay ningún médico seleccionado");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Carga el formulario de pacientes con los datos del paciente seleccionado en la tabla
     */
    @FXML
    void editarPaciente(ActionEvent event) {
        try {
            seg_social = seleccionTabla(pacientesTable);
            // Cargamos el paciente seleccionado de la base de datos
            ResultSet datosPaciente = con.createStatement().executeQuery("SELECT * FROM pacientes WHERE seg_social = " + seg_social);
            if (datosPaciente.next()) { // Rellena los campos con los datos del paciente
                tfSegSocial.setText(datosPaciente.getObject(1).toString());
                tfSegSocial.setEditable(false);
                tfNombrePaciente.setText(datosPaciente.getObject(2).toString());
                tfApellidoPaciente.setText(datosPaciente.getObject(3).toString());
                tfDomicilio.setText(datosPaciente.getObject(4).toString());
                tfPoblacion.setText(datosPaciente.getObject(5).toString());
                cbProvincia.setValue(datosPaciente.getObject(6).toString());
                tfCodPostal.setText(datosPaciente.getObject(7).toString());
                tfTelefono.setText(datosPaciente.getObject(8).toString());
                tfNumHistorial.setText(datosPaciente.getObject(9).toString());
                tfObservacionesPacientes.setText(datosPaciente.getObject(10).toString());
            }
            editaPaciente = true;
            pacientesPane.setVisible(false);
            formPacientesPane.setVisible(true);

        } catch (NullPointerException npe) {
            // Ventana de error
            ventanaDialogo("ERROR", "No hay ningún paciente seleccionado");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Accede al formulario de creación o edición de ingresos en nuestra interfaz
     */
    @FXML
    void nuevoIngreso(ActionEvent event) {
        reseteaIngresos();
        vaciaIngresos();
        editaIngreso = false;
        ingresosPane.setVisible(false);
        formIngresosPane.setVisible(true);
    }

    /**
     * Accede al formulario de creación o edición de médicos en nuestra interfaz
     */
    @FXML
    void nuevoMedico(ActionEvent event) {
        reseteaMedicos();
        vaciaMedicos();
        editaMedico = false;
        medicosPane.setVisible(false);
        formMedicosPane.setVisible(true);
    }

    /**
     * Accede al formulario de creación o edición de pacientes en nuestra interfaz
     */
    @FXML
    void nuevoPaciente(ActionEvent event) {
        reseteaPacientes();
        vaciaPacientes();
        tfSegSocial.setEditable(true);
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

    public void cargaComboBox(ComboBox<String> cb, String nomTabla) {
        // Nos aseguramos de limpiar el combobox
        cb.getItems().clear();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + nomTabla);

            String auxApellido;
            String auxNombre;
            while (rs.next()) {
                auxApellido = rs.getObject(3).toString();
                auxNombre = rs.getObject(2).toString();
                cb.getItems().add(auxApellido + ", " + auxNombre);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    /**
     * Función que recoge los datos de nuestra base de datos y los inserta en una de las tablas
     */
    public void cargaTabla(String nomTabla, TableView tabla) {
        ObservableList<ObservableList> datos; // Tabla en la que cargaremos los datos de la BBDD
        datos = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM " + nomTabla;
            ResultSet rs = con.createStatement().executeQuery(query); // Guardamos los datos de la tabla

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                // Cogemos los nombres de las columnas para añadirlas a nuestra tabla
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                // Añadir las columnas a la tabla dada como parámetro
                tabla.getColumns().addAll(col);
            }

            rellenaTabla(tabla, datos, rs);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    /**
     * Identifica el dato seleccionado de la tabla
     */
    public String seleccionTabla(TableView<ObservableList> tabla) {
        ObservableList<String> data;
        Object value = null;
        data = tabla.getSelectionModel().getSelectedItem();
        value = data.get(0);
        return (String) value;
    }

    /**
     * Llena la tabla de nuestra interfaz con los datos de nuestra BBDD
     *
     * @param tabla tabla que vamos a rellenar
     */
    private static void rellenaTabla(TableView tabla, ObservableList<ObservableList> datos, ResultSet rs) throws SQLException {
        while (rs.next()) {
            // Iteramos las filas y las añadimos a la tabla de datos
            ObservableList<String> fila = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                fila.add(rs.getString(i));
            }
            datos.add(fila);
        }
        // Añadimos los datos a la tabla
        tabla.setItems(datos);
    }

    /**
     * Función que añade una fila a una tabla ya inicializada
     *
     * @param nomTabla nombre de la tabla en nuestra base de datos
     * @param tabla    objeto TableView dentro de nuestra interfaz
     */
    public void actualizaTabla(String nomTabla, TableView tabla) {
        ObservableList<ObservableList> data;
        data = FXCollections.observableArrayList();
        try {
            // Recogemos los datos de nuestra tabla
            String SQL = "SELECT * from " + nomTabla;
            ResultSet rs = con.createStatement().executeQuery(SQL);

            rellenaTabla(tabla, data, rs);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al añadir la fila");
        }
    }

    /**
     * Comprueba que la fecha introducida como cadena tiene el formato correcto para nuestra BBDD
     * @param fecha fecha introducida como cadena
     * @return true si es correcto, false si no lo es
     */
    public boolean esFecha(String fecha){
        boolean esNumero = false;
        try{
            Date date =new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
            if(date.before(Date.from(new Date().toInstant())) || date.equals(new Date().toInstant()))
            esNumero = true;
        } catch (ParseException ignore) {

        }
        return esNumero;
    }

    /**
     * Recibe una cadena de texto y comprueba si es un número
     *
     * @param cadena cadena de texto recibida
     * @return true si es numérico, false si no lo es
     */
    public boolean esNumerico(String cadena) {
        boolean res;
        Double aux;
        try {
            aux = Double.parseDouble(cadena);
            if (aux % 1 == 0 && aux > 0) {
                res = true;
            } else {
                res = false;
            }

        } catch (NumberFormatException nfe) {
            res = false;
        }
        return res;
    }

    /**
     * Construye la query dada como parámetro con los valores dados
     */
    private static void construyeQueryIngreso(String procedencia, String fecha_ingreso, String num_planta, String num_cama, String observaciones, String paciente, String medico, PreparedStatement insertQuery) throws SQLException {
        // Construimos la query
        insertQuery.setString(1, procedencia);
        insertQuery.setString(2, fecha_ingreso);
        insertQuery.setString(3, num_planta);
        insertQuery.setString(4, num_cama);
        insertQuery.setString(5, observaciones);
        insertQuery.setString(6, paciente);
        insertQuery.setString(7, medico);
    }


    /**
     * Construye la query dada como parámetro con los valores dados
     */
    private static void construyeQueryMedico(String nombre, String apellidos, String especialidad, String num_colegiado, String cargo, String observaciones, PreparedStatement insertQuery) throws SQLException {
        insertQuery.setString(1, nombre);
        insertQuery.setString(2, apellidos);
        insertQuery.setString(3, especialidad);
        insertQuery.setString(4, num_colegiado);
        insertQuery.setString(5, cargo);
        insertQuery.setString(6, observaciones);
    }

    /**
     * Construye la query dada como parámetro con los valores dados
     */
    private void construyeQueryPaciente(String valor1, String valor2, String valor3, String valor4, String valor5, String valor6, String valor7, String valor8, String valor9, PreparedStatement updateQuery) throws SQLException {
        updateQuery.setString(1, valor1);
        updateQuery.setString(2, valor2);
        updateQuery.setString(3, valor3);
        updateQuery.setString(4, valor4);
        updateQuery.setString(5, valor5);
        updateQuery.setString(6, valor6);
        updateQuery.setString(7, valor7);
        updateQuery.setString(8, valor8);
        updateQuery.setString(9, valor9);
    }

    /**
     * Restaura los campos del formulario de médicos a su diseño original
     */
    private void reseteaIngresos() {
        // Estilo original
        tfProcedencia.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfFechaIngreso.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfNumPlanta.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfNumCama.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        cbPaciente.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        cbMedico.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfObservacionesIngresos.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
    }

    /**
     * Restaura los campos del formulario de médicos a su diseño original
     */
    private void reseteaMedicos() {
        // Estilo original
        tfNombreMedico.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfApellidoMedico.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfEspecialidad.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfNumColegiado.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfCargo.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfObservacionesMedicos.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
    }

    /**
     * Restaura los campos del formulario de pacientes a su diseño original
     */
    private void reseteaPacientes() {
        // Estilo original
        tfSegSocial.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfNombrePaciente.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfApellidoPaciente.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfDomicilio.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfPoblacion.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        cbProvincia.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfTelefono.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfNumHistorial.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfObservacionesPacientes.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
    }

    /**
     * Pone en blanco todos los campos del formulario de ingresos
     */
    private void vaciaIngresos() {
        tfProcedencia.setText("");
        tfFechaIngreso.setText("");
        tfNumPlanta.setText("");
        tfNumCama.setText("");
        cbPaciente.setValue("");
        cbMedico.setValue("");
        tfObservacionesIngresos.setText("");
    }

    /**
     * Pone en blanco todos los campos del formulario de médicos
     */
    private void vaciaMedicos() {
        tfNombreMedico.setText("");
        tfApellidoMedico.setText("");
        tfEspecialidad.setText("");
        tfNumColegiado.setText("");
        tfCargo.setText("");
        tfObservacionesMedicos.setText("");

    }

    /**
     * Pone en blanco todos los campos del formulario de pacientes
     */
    private void vaciaPacientes() {
        tfSegSocial.setText("");
        tfNombrePaciente.setText("");
        tfApellidoPaciente.setText("");
        tfDomicilio.setText("");
        tfPoblacion.setText("");
        cbProvincia.setValue("Almería");
        ;
        tfCodPostal.setText("");
        tfTelefono.setText("");
        tfNumHistorial.setText("");
        tfObservacionesPacientes.setText("");
    }

    /**
     * Genera una ventana emergente de tipo Dialog<>() para informar al usuario
     *
     * @param titulo  título de la ventana
     * @param mensaje mensaje que se incluira en la ventana emergente
     */
    private static void ventanaDialogo(String titulo, String mensaje) {
        // Ventana de error
        Dialog<String> ventana = new Dialog<>();
        ventana.setTitle(titulo);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ventana.setContentText(mensaje);
        ventana.getDialogPane().getButtonTypes().add(type);
        ventana.showAndWait();
    }

    /**
     * Muestra una ventana de error en caso de que algún campo del formulario sea erróneo
     *
     * @param mensajeError mensaje de error
     */
    private void ventanaError(String mensajeError) {
        if (mensajeError.substring(mensajeError.length() - 1).equals(",")) {
            mensajeError = mensajeError.substring(0, mensajeError.length() - 1) + ".";
        }
        // Creamos ventana de error
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText("ALERTA");
        alert.setContentText(mensajeError);
        alert.initOwner(pacientesPane.getScene().getWindow());
        alert.showAndWait();
    }

    /**
     * Se ejecuta al iniciar la aplicación
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Conexión con nuestra base de datos
        try {
            con = ConexionBD.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Inicializamos el ComboBox de provincias
        cbProvincia.getItems().add("Almería");
        cbProvincia.getItems().add("Cádiz");
        cbProvincia.getItems().add("Córdoba");
        cbProvincia.getItems().add("Granada");
        cbProvincia.getItems().add("Huelva");
        cbProvincia.getItems().add("Jaén");
        cbProvincia.getItems().add("Málaga");
        cbProvincia.getItems().add("Sevilla");
        // Cargamos la tabla de nuestra base de datos
        cargaTabla("pacientes", pacientesTable);
        cargaTabla("medicos", medicosTable);
        cargaTabla("ingresos", ingresosTable);
        // Cargamos los ComboBox del formulario de ingresos
        cargaComboBox(cbPaciente, "pacientes");
        cargaComboBox(cbMedico, "medicos");

    }
}
