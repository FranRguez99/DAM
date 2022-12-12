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
import java.util.ResourceBundle;

/**
 * Controlador de nuestra interfaz gráfica
 * todo AÑADIR CONTROLADOR DE CLICK EN LA TABLA
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
    private TextField tfProvincia;

    @FXML
    private TextField tfSegSocial;

    @FXML
    private TextField tfTelefono;

    // Variables de control para la edición de datos
    private boolean editaPaciente;
    private boolean editaMedico;
    private boolean editaIngreso;

    // Conexión con la BD
    Connection con;


    @FXML
    void aceptarIngreso(ActionEvent event) {

    }

    @FXML
    void aceptarMedico(ActionEvent event) {

    }

    /**
     * Función que controla el ingreso o actualización de los pacientes, comprobando que todos los campos sean correctos
     */
    @FXML
    void aceptarPaciente() {
        reseteaPacientes();
        if(editaPaciente){
            tfSegSocial.setEditable(false);
        }
        try { // Leemos los datos de todos los campos y comprobamos los errores
            // Flag para lanzar el mensaje de error
            boolean error = false;
            // Variables para guardar el mensaje de error y los valores del formulario
            String mensajeError = "Error en los siguientes campos:\n";
            String seg_social, nombre, apellidos, domicilio, poblacion, provincia, cod_postal, num_tlfn, num_historial, observaciones;

            // Guardamos los valores del formulario
            seg_social = tfSegSocial.getText();
            nombre = tfNombrePaciente.getText();
            apellidos = tfApellidoPaciente.getText();
            domicilio = tfDomicilio.getText();
            poblacion = tfPoblacion.getText();
            provincia = tfProvincia.getText();
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
            if (nombre.length() == 0 || nombre.length() > 20) { // Nombre
                tfNombrePaciente.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " NOMBRE (Máximo 20 caracteres)\n";
            }
            if (apellidos.length() == 0 || apellidos.length() > 20) { // Apellidos
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
            if (provincia.length() == 0 || provincia.length() > 20) { // Provincia
                tfProvincia.setStyle("-fx-effect:  innershadow(three-pass-box, red, 5 , 0.5, 1, 1);");
                error = true;
                mensajeError = mensajeError + " PROVINCIA (Máximo 20 caracteres)\n";
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
            } else {
                // Comprobamos con la variable editaPaciente si haremos update o insert
                if(!editaPaciente){ // INSERT
                    PreparedStatement insertQuery = con.prepareStatement("INSERT INTO pacientes (seg_social, nombre, apellidos," +
                            "domicilio, poblacion, provincia, num_tlfn, num_historial, observaciones) VALUES (?,?,?,?,?,?,?,?,?)");
                    // Construimos la query
                    insertQuery.setString(1, seg_social);
                    insertQuery.setString(2, nombre);
                    insertQuery.setString(3, apellidos);
                    insertQuery.setString(4, domicilio);
                    insertQuery.setString(5, poblacion);
                    insertQuery.setString(6, provincia);
                    insertQuery.setString(7, num_tlfn);
                    insertQuery.setString(8, num_historial);
                    insertQuery.setString(9, observaciones);

                    if (insertQuery.executeUpdate() == 1){
                        // Ventana de confirmación
                        Dialog<String> confirmInsert = new Dialog<>();
                        confirmInsert.setTitle("INSERTAR PACIENTE");
                        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                        confirmInsert.setContentText("Paciente insertado con éxito");
                        confirmInsert.getDialogPane().getButtonTypes().add(type);
                        confirmInsert.showAndWait();
                        nuevaFila("PACIENTES", pacientesTable);
                    }
                } else { // UPDATE

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

    }

    @FXML
    void borrarMedico(ActionEvent event) {

    }

    @FXML
    void borrarPaciente(ActionEvent event) {

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

    }

    @FXML
    void editarMedico(ActionEvent event) {

    }

    /**
     * Carga el formulario de pacientes con los datos del paciente seleccionado en la tabla
     */
    @FXML
    void editarPaciente(ActionEvent event) {
        try{
            String seg_social = seleccionTabla(pacientesTable);
            // Cargamos el paciente seleccionado de la base de datos
            ResultSet datosPaciente = con.createStatement().executeQuery("SELECT * FROM pacientes WHERE seg_social = " + seg_social);
            if(datosPaciente.next()){ // Rellena los campos con los datos del paciente
                tfSegSocial.setText(datosPaciente.getObject(1).toString());
                tfNombrePaciente.setText(datosPaciente.getObject(2).toString());
                tfApellidoPaciente.setText(datosPaciente.getObject(3).toString());
                tfDomicilio.setText(datosPaciente.getObject(4).toString());
                tfPoblacion.setText(datosPaciente.getObject(5).toString());
                tfProvincia.setText(datosPaciente.getObject(6).toString());
                tfCodPostal.setText(datosPaciente.getObject(7).toString());
                tfTelefono.setText(datosPaciente.getObject(8).toString());
                tfNumHistorial.setText(datosPaciente.getObject(9).toString());
                tfObservacionesPacientes.setText(datosPaciente.getObject(10).toString());
            }
            editaPaciente = true;
            pacientesPane.setVisible(false);
            formPacientesPane.setVisible(true);

        } catch (NullPointerException npe){
            // Ventana de error
            Dialog<String> editError = new Dialog<>();
            editError.setTitle("ERROR");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            editError.setContentText("No hay ningún paciente seleccionado");
            editError.getDialogPane().getButtonTypes().add(type);
            editError.showAndWait();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }



    }

    /**
     * Accede al formulario de creación o edición de ingresos en nuestra interfaz
     */
    @FXML
    void nuevoIngreso(ActionEvent event) {
        editaIngreso = false;
        ingresosPane.setVisible(false);
        formIngresosPane.setVisible(true);
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
        reseteaPacientes();
        vaciaPacientes();
        editaPaciente = false;
        pacientesPane.setVisible(false);
        formPacientesPane.setVisible(true);
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
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                // Añadir las columnas a la tabla dada como parámetro
                tabla.getColumns().addAll(col);
            }

            rellenaTabla(tabla, datos, rs);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

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
     * @param nomTabla nombre de la tabla en nuestra base de datos
     * @param tabla objeto TableView dentro de nuestra interfaz
     */
    public void nuevaFila(String nomTabla, TableView tabla){
        ObservableList<ObservableList> data;
        data = FXCollections.observableArrayList();
        try{
            // Recogemos los datos de nuestra tabla
            String SQL = "SELECT * from " + nomTabla;
            ResultSet rs = con.createStatement().executeQuery(SQL);

            rellenaTabla(tabla, data, rs);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error al añadir la fila");
        }
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
            if (aux % 1 == 0) {
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
     * Restaura los campos del formulario de pacientes a su diseño original
     */
    private void reseteaPacientes() {
        // Estilo original
        tfSegSocial.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfNombrePaciente.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfApellidoPaciente.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfDomicilio.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfPoblacion.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfProvincia.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfTelefono.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfNumHistorial.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
        tfObservacionesPacientes.setStyle("-fx-effect:  innershadow(three-pass-box, gray, 5 , 0.5, 1, 1);");
    }

    /**
     * Pone en blanco todos los campos del formulario de pacientes
     */
    private void vaciaPacientes(){
        tfSegSocial.setText("");
        tfNombrePaciente.setText("");
        tfApellidoPaciente.setText("");
        tfDomicilio.setText("");
        tfPoblacion.setText("");
        tfProvincia.setText("");
        tfCodPostal.setText("");
        tfTelefono.setText("");
        tfNumHistorial.setText("");
        tfObservacionesPacientes.setText("");
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
        // Cargamos la tabla de nuestra base de datos
        cargaTabla("pacientes", pacientesTable);
        cargaTabla("medicos", medicosTable);
        cargaTabla("ingresos", ingresosTable);
    }
}
