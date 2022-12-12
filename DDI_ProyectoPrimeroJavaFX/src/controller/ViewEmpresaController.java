/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import help.Help;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import models.Alumno;
import javafx.application.Application;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.ConexionBD;
import models.Tutores;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
/**
 * FXML Controller class
 *
 * @author dam1
 */
public class ViewEmpresaController implements Initializable {
    
  
    @FXML
    private TabPane tPane;
    @FXML
    private TextField tfCodEmpresa;
    @FXML
    private TextField tfMailEmpresa;
    @FXML
    private TextField tfLocalidad;
    @FXML
    private TextField tfCPEmpresa;
    @FXML
    private TextField tfDireccionEmpresa;
    @FXML
    private TextField tfNombreEmpresa;
    @FXML
    private TextField tfCifEmpresa;
    @FXML
    private ComboBox<String> cbJornada;
    @FXML
    private ComboBox<String> cbModalidad;
    @FXML
    private TextField tfDniRepLegal;
    @FXML
    private TextField tfApellidosTL;
    @FXML
    private TextField tfNombreTL;
    @FXML
    private TextField tfDniTutLaboral;
    @FXML
    private TextField tfApellidosRL;
    @FXML
    private TextField tfNombreRL;
    @FXML
    private TextField tfTlfnTL;
    @FXML
    private Button bUpdate;
    @FXML
    private Button bDelete;
    @FXML
    private Button bInsert;
    @FXML
    private Button bOK;
    @FXML
    private TableView<ObservableList> tvTablaEmpresa;
    @FXML
    private Tab tabEmpresa;
    @FXML
    private Tab tabAlumnos;
    @FXML
    private Tab tabTutores;
    @FXML
    private Tab tabAsignacion;
    @FXML
    private AnchorPane marco;
    @FXML
    private Button botonAlumnos;
    @FXML
    private Button bAyuda;
    @FXML
    private Label textoAlumnos;
    @FXML
    private Button botonTut;
    @FXML
    private Label textoTut;
    @FXML
    private ComboBox<String> cbAlumnos;
    @FXML
    private ComboBox<String> cbTutores;
    @FXML
    private ComboBox<String> cbEmpresas;
    @FXML
    private Button bConfirm;
    @FXML
    private Label lMensaje;
    
    ConexionBD cbd;
    @FXML
    private Pane pEmpresa;
    @FXML
    private Pane pEmpleados;
    @FXML
    private Pane pBotones;
    @FXML
    private TableView<?> tvTablaAsig;
    @FXML
    private Pane pEmpresaRepre;
    
    @FXML
    private Pane pBotonesTabla;

    
    private boolean insertUpdate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbd = new ConexionBD();
        cbJornada.setItems(FXCollections.observableArrayList(
            "Partida", "Continua"));
        cbModalidad.setItems(FXCollections.observableArrayList(
            "Presencial", "Semipresencial", "Distancia"));
        cbJornada.getSelectionModel().selectFirst();
        cbModalidad.getSelectionModel().selectFirst();
        

        // Añadir bordes
        pEmpresa.setBorder(new Border(new BorderStroke(Color.GREY, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        // Fotos botones
        Image img1 = new Image("controller/DATaTabla.png");
        ImageView foto1 = new ImageView(img1);
        botonAlumnos.setGraphic(foto1);
        
        Image img2 = new Image("controller/XMLaTabla.png");
        ImageView foto2 = new ImageView(img2);
        botonTut.setGraphic(foto2);
        // Generar tablas
        try {
            vaciaTablas();
        } catch (SQLException ex) {
            Logger.getLogger(ViewEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        datosAlumnos();
        datosEmpresa();
        datosTutores();
             
        buildData("EMPRESAS", tvTablaEmpresa);
        buildData("ASIGNACION", tvTablaAsig);
    }    
       
   /***************************************************
    * 
    *   CONTROLADORES PARA LA VISTA EMPRESA
    * 
    ***************************************************/
    
    @FXML
    private void controllerOK(ActionEvent event) {

        pEmpresaRepre.setVisible(false);
        pBotonesTabla.setVisible(true);
        if (insertUpdate){
            try {
                    Connection conexion = cbd.conectar();
                    try {
                        PreparedStatement ps = conexion.prepareStatement
                                ("INSERT INTO EMPRESAS " +
                                        "(CIF, Nombre, Direccion, CP, Localidad, Jornada, Modalidad" +
                                        ",Mail, DNI_RL, Nombre_RL, Apellidos_RL, DNI_TL" +
                                        ", Nombre_TL, Apellidos_TL, Tlfn_TL) " +
                                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                        setStatement(ps);
                       
                        if (ps.executeUpdate() == 1) {
                            
                            datosEmpresa();
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("INSERTAR EMPRESA");
                            ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                            dialog.setContentText("Empresa insertada con éxito");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                            addRow("EMPRESAS", tvTablaEmpresa);
                         
                            setDefaultTFs();
                        } else {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("INSERTAR EMPRESA");
                            ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                            dialog.setContentText("No se ha podido insertar la empresa");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        }
                        addRow("EMPRESAS", tvTablaEmpresa);
                        ps.close();
                    } catch (Exception ee) {
                        Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("INSERTAR EMPRESA");
                            ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                            dialog.setContentText("No se ha podido insertar la empresa");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                    }
                    conexion.close();

                } catch (SQLException xx) {
                    xx.printStackTrace();
                }

        
        }
        else {
            try {
                Connection conexion = cbd.conectar();
                try {
                    PreparedStatement ps = conexion.prepareStatement
                            ("UPDATE empresas SET CIF = ?, Nombre = ?, Direccion = ?, CP = ?, Localidad = ?, Jornada = ?," +
                                    "Modalidad = ?, Mail = ?, DNI_RL = ?, Nombre_RL = ?, Apellidos_RL = ?, DNI_TL = ?," +
                                    "Nombre_TL = ?, Apellidos_TL = ?, Tlfn_TL = ? WHERE Cod_Empresas = ?");

                    setStatement(ps);
                    ps.setString(16, tfCodEmpresa.getText());
                    System.out.println(ps);

                    if (ps.executeUpdate() == 1) {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("MODIFICAR EMPRESA");
                        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                        dialog.setContentText("Empresa modificada con éxito");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                        addRow("EMPRESAS", tvTablaEmpresa);
                        datosEmpresa();
                        setDefaultTFs();
                    } else {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("MODIFICAR EMPRESA");
                        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                        dialog.setContentText("No se ha podido modificar la empresa");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    addRow("EMPRESAS", tvTablaEmpresa);
                    ps.close();
                } catch (Exception ee) {
                    Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("MODIFICAR EMPRESA");
                        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                        dialog.setContentText("No se ha podido modificar la empresa");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                }
                conexion.close();

            } catch (SQLException xx) {
                xx.printStackTrace();
            }
        }
    }
    @FXML
    private void controllerUpdate(ActionEvent event) {
        pEmpresaRepre.setVisible(true);
        pBotonesTabla.setVisible(false);
        insertUpdate=false;
        
        
    }

    @FXML
    private void controllerDelete(ActionEvent event) {
        try {
                Connection conexion = cbd.conectar();
                try {
                    PreparedStatement ps = conexion.prepareStatement
                            ("DELETE FROM empresas WHERE Cod_Empresas = ?");
                    ps.setString(1, tfCodEmpresa.getText());

                    if (ps.executeUpdate() == 1) {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("ELIMINAR EMPRESA");
                        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                        dialog.setContentText("Empresa eliminada con éxito");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                        addRow("EMPRESAS", tvTablaEmpresa);
                        datosEmpresa();
                        setDefaultTFs();
                    } else {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("ELIMINAR EMPRESA");
                        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                        dialog.setContentText("No se ha podido eliminar la empresa");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    addRow("EMPRESAS", tvTablaEmpresa);
                    ps.close();
                } catch (Exception ee) {
                    Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("ELIMINAR EMPRESA");
                        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                        dialog.setContentText("No se ha podido eliminar la empresa");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                }
                conexion.close();

            } catch (SQLException xx) {
                xx.printStackTrace();
            }
    }
    
    public void setDefaultTFs() {
        Object currentCode = null;
        try {
            Statement s = cbd.conectar().createStatement();
            ResultSet rs = s.executeQuery("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES " +
                    "WHERE TABLE_SCHEMA = 'bdgestionfct' AND   TABLE_NAME   = 'empresas'");
            if (rs.next()) {
                currentCode = rs.getObject("AUTO_INCREMENT");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tfCodEmpresa.setText(currentCode.toString());
        tfCifEmpresa.setText("");
        tfNombreEmpresa.setText("");
        tfDireccionEmpresa.setText("");
        tfCPEmpresa.setText("");
        tfLocalidad.setText("");
        cbJornada.getSelectionModel().selectFirst();
        cbModalidad.getSelectionModel().selectFirst();
        tfMailEmpresa.setText("");
        tfDniRepLegal.setText("");
        tfNombreRL.setText("");
        tfApellidosRL.setText("");
        tfDniTutLaboral.setText("");
        tfNombreTL.setText("");
        tfApellidosTL.setText("");
        tfTlfnTL.setText("");
    }
    
    @FXML
    private void controllerInsert(ActionEvent event) {
        pEmpresaRepre.setVisible(true);
        pBotonesTabla.setVisible(false);
        insertUpdate=true;
        setDefaultTFs();
        
        
    }

    @FXML
    private void cambiarEmpresa(Event event) {
        Main.resizeScene(1015,705);
        marco.setPrefSize(1015, 680);
        marco.setMinSize(1015, 680);
        tPane.setPrefSize(1015, 680);
        marco.resize(1015, 680);
        marco.localToScreen(1015, 680);
        tPane.resize(1015, 680);
        System.out.println("Cambiando a empresa");
    }

    @FXML
    private void cambiaAlumnos(Event event) {
        Main.resizeScene(620,365);
        marco.setPrefSize(620, 340);
        marco.setMinSize(620, 340);
        marco.resize(620, 340);
        marco.localToScreen(620, 340);
        tPane.resize(620, 340);
        tPane.setPrefSize(620, 340);
        tPane.setMinSize(620, 340);
        marco.layout();
        marco.autosize();
        System.out.println("Cambiando a Alumno");
    }

    @FXML
    private void cambiaTutores(Event event) {
        Main.resizeScene(620,365);
        marco.setPrefSize(620, 340);
        marco.setMinSize(620, 340);
        marco.resize(620, 340);
        
        tPane.resize(620, 340);
        tPane.setPrefSize(620, 340);
        tPane.setMinSize(620, 340);
        System.out.println("Cambiando a Tut");
    }

    @FXML
    private void cambiaAsig(Event event) {
        Main.resizeScene(620,365);
         marco.setPrefSize(620, 340);
        marco.setMinSize(620, 340);
        marco.resize(620, 340);
        
        tPane.resize(620, 340);
        tPane.setPrefSize(620, 340);
        tPane.setMinSize(620, 340);
        System.out.println("Cambiando a ASig");
    }


     private void setStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, tfCifEmpresa.getText());
        ps.setString(2, tfNombreEmpresa.getText());
        ps.setString(3,tfDireccionEmpresa.getText());
        ps.setString(4, tfCPEmpresa.getText());
        ps.setString(5, tfLocalidad.getText());
        ps.setShort(6, (short) (cbJornada.getItems().indexOf(cbJornada.getValue()) + 1));
        ps.setShort(7, (short) (cbModalidad.getItems().indexOf(cbModalidad.getValue()) + 1));
        ps.setString(8, tfMailEmpresa.getText());
        ps.setString(9, tfDniRepLegal.getText());
        ps.setString(10, tfNombreRL.getText());
        ps.setString(11, tfApellidosRL.getText());
        ps.setString(12, tfDniTutLaboral.getText());
        ps.setString(13, tfNombreTL.getText());
        ps.setString(14, tfApellidosTL.getText());
        ps.setString(15, tfTlfnTL.getText());
    }
      
     public void addRow(String nomTabla, TableView tabla){
         ObservableList<ObservableList> data;
          Connection c ;
          data = FXCollections.observableArrayList();
          try{
            c = cbd.conectar();
   
            String SQL = "SELECT * from " + nomTabla;
            ResultSet rs = c.createStatement().executeQuery(SQL);
          
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tabla.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
     }
     
     public void buildData(String nomTabla, TableView tabla){
        
          ObservableList<ObservableList> data;
          Connection c ;
          data = FXCollections.observableArrayList();
          try{
            c = cbd.conectar();
   
            String SQL = "SELECT * from " + nomTabla;
            ResultSet rs = c.createStatement().executeQuery(SQL);
 
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory((Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                        param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tabla.getColumns().addAll(col); 
                if(i==0){
                    col.setVisible(false);
                }
                System.out.println("Column ["+i+"] ");
            }

          
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tabla.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }
    

    @FXML
    private void controllerTable(MouseEvent event) {
                ObservableList<String> data;
                Object value = null;
                data = tvTablaEmpresa.getSelectionModel().getSelectedItem();
                value = data.get(0);
                System.out.println(value);
                
                try {
                    Statement s = cbd.conectar().createStatement();
                    ResultSet rs = s.executeQuery("select * from empresas where Cod_Empresas = " + value);
                    if (rs.next()) {

                        tfCodEmpresa.setText(rs.getObject(1).toString());
                        tfCifEmpresa.setText(rs.getObject(2).toString());
                        tfNombreEmpresa.setText(rs.getObject(3).toString());
                        tfDireccionEmpresa.setText(rs.getObject(4).toString());
                        tfCPEmpresa.setText(rs.getObject(5).toString());
                        tfLocalidad.setText(rs.getObject(6).toString());
                        cbJornada.getSelectionModel().select(rs.getObject(7).toString());
                        cbModalidad.getSelectionModel().select(rs.getObject(8).toString());
                        tfMailEmpresa.setText(rs.getObject(9).toString());
                        tfDniRepLegal.setText(rs.getObject(10).toString());
                        tfNombreRL.setText(rs.getObject(11).toString());
                        tfApellidosRL.setText(rs.getObject(12).toString());
                        tfDniTutLaboral.setText(rs.getObject(13).toString());
                        tfNombreTL.setText(rs.getObject(14).toString());
                        tfApellidosTL.setText(rs.getObject(15).toString());
                        tfTlfnTL.setText(rs.getObject(16).toString());
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
    }
    
    private void vaciaTablas() throws SQLException{
        Connection conex = cbd.conectar();
        Statement stmt = conex.createStatement();
        stmt.executeUpdate("DELETE FROM ALUMNOS");
        stmt.executeUpdate("DELETE FROM TUTORES_P");
        stmt.close();
        conex.close();
    }
    
       /***************************************************
    * 
    *   CONTROLADORES PARA LA VISTA ALUMNOS
    * 
    ***************************************************/
    
    ArrayList<Alumno> listAlumnos = new ArrayList<>();
    
    public void leerfichero() {
        // Se declaran las variables de método para guardar lo leído
        //Cod_alumno
        int cod;
        String dni;
        String nombre;
        String apellidos;
        String fechanacimiento;


        // Se genera el objeto en memoria para trasegar con el fichero
        File fich = new File("src/controller/alumnos2cfs.dat");
        // Se crea el amarre para la "manguera base" que permita leer del fichero
        FileInputStream fis = null;
        // Se crea el amarre pensando en aplicar métodos específicos [readXXX()]
        DataInputStream dis = null;
        try {
            // Se crea la "manguera base" para grabar datos en el fichero
            fis = new FileInputStream(fich);
            // Se crea el envoltorio que aplique a la "manguera base" métodos como readXXX()
            dis = new DataInputStream(fis);
            // Extraemos datos del fichero hasta llegar al final del mismo



            while (dis.available() > 0) {
                cod = dis.readInt();
                dni = dis.readUTF();
                nombre = dis.readUTF();
                apellidos = dis.readUTF();
                fechanacimiento = dis.readUTF();

                Alumno alumnos = new Alumno(cod,dni,nombre,apellidos,fechanacimiento);
                listAlumnos.add(alumnos);

            }

        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
            System.out.println(" ");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
                System.out.println(" ");
            }
        }

    }
    
    @FXML
    private void insertarDatosAlum(ActionEvent event)  throws ClassNotFoundException, SQLException {
        botonAlumnos.setDisable(true);
        textoAlumnos.setVisible(true);
        leerfichero();
        
        Connection conexion = cbd.conectar();

        PreparedStatement actualiza = conexion.prepareStatement("INSERT INTO ALUMNOS(DNI, Nombre, Apellidos, Fecha_Nac) VALUES( ?, ?, ?, ?) ");


        for (Alumno alumno:listAlumnos) {
            actualiza.setString(1, alumno.getDni());
            actualiza.setString(2, alumno.getNombre());
            actualiza.setString(3, alumno.getApellidos());

            String fecha = DateTimeFormatter.ofPattern("yyyy,MM,dd").format(LocalDate.parse(alumno.getFechanacimiento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            actualiza.setString(4, fecha);
            actualiza.executeUpdate();

        }
        actualiza.close();
        conexion.close();
        
        datosAlumnos();
    
    }
    
       /***************************************************
    * 
    *   CONTROLADORES PARA LA VISTA TUTORES
    * 
    ***************************************************/
    
    ArrayList<Tutores> listTutores = new ArrayList<>();
    
public void lecturaXML() {


        //Creamos una referencia (fichXML) de tipo File al cargar el fichero XML existente
        File fichXML = new File("src/controller/tutoresdoc.xml");
        {

            //Parsea el fichero y lo convierte de fichero a documento XML recorrible
            DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructDoc = null;
            try {
                constructDoc = factoriaDoc.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            }
            Document docDOM = null;
            try {
                docDOM = constructDoc.parse(fichXML);
            } catch (SAXException | IOException ex) {
                ex.printStackTrace();
            }

            //Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
            docDOM.getDocumentElement().normalize();
            //Localizamos e imprimimos cuál es el elemento raíz

            Element raiz = docDOM.getDocumentElement();

            //Vuelca a una lista los nodos que cuelgan del nodo raíz
            NodeList alumDam = docDOM.getElementsByTagName("tutordoc");
            for (int cont = 0; cont < alumDam.getLength(); cont++) {
                //Se recorre la lista con nodos y se extrae en nodo el que indica cont
                Node nodo = (Node) alumDam.item(cont);
                //Para saber qué nodo está procesando, imprime el nombre

                //Comprueba si se encuentra ante un elemento
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    //Castea el nodo de tipo Node a uno de tipo Element
                    Element element = (Element) nodo;

                    String cod = element.getElementsByTagName("codtut").item(0).getTextContent();
                    String Nombrecompleto = element.getElementsByTagName("nomap").item(0).getTextContent();
                    String CorreoElectronico = element.getElementsByTagName("correo").item(0).getTextContent();
                    String Telefono = element.getElementsByTagName("telefono").item(0).getTextContent();

                    Tutores tutores = new Tutores(Integer.parseInt(cod), Nombrecompleto, CorreoElectronico, Telefono);
                    listTutores.add(tutores);
                }
            }

        }
    }
    @FXML
    private void insertarDatosTut(ActionEvent event) throws SQLException {
        lecturaXML();
        botonTut.setDisable(true);
        textoTut.setVisible(true);
        Connection conexion = cbd.conectar();
        PreparedStatement actualiza = conexion.prepareStatement("INSERT INTO Tutores_P(Nombre, Mail, Tlfn) VALUES( ?, ?, ? ) ");


        for (Tutores tutor : listTutores) {
            actualiza.setString(1, tutor.getNombreCompleto());
            actualiza.setString(2, tutor.getCorreoElectronico());
            actualiza.setString(3, tutor.getTelefono());

            actualiza.executeUpdate();

        }
        actualiza.close();
        conexion.close();
        
        datosTutores();
    }
    
    
    
       /***************************************************
    * 
    *   CONTROLADORES PARA LA VISTA ASIGNACIÓN
    * 
    ***************************************************/
    public void datosAlumnos() {
        try {
            Statement s = cbd.conectar().createStatement();
            ResultSet rs = s.executeQuery("select * from alumnos");

            String auxApellido;
            String auxNombre;
            while (rs.next()) {
                byte[] dataApellido = rs.getBytes("Apellidos");
                auxApellido = new String(dataApellido, StandardCharsets.UTF_8);
                byte[] dataNombre = rs.getBytes("Nombre");
                auxNombre = new String(dataNombre, StandardCharsets.UTF_8);
                cbAlumnos.getItems().add(auxApellido + ", " + auxNombre);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void datosEmpresa() {
        cbEmpresas.getItems().clear();
        try {
            Statement s = cbd.conectar().createStatement();
            ResultSet rs = s.executeQuery("select * from empresas");

            String auxNombre;
            while (rs.next()) {
                byte[] dataNombre = rs.getBytes("Nombre");
                auxNombre = new String(dataNombre, StandardCharsets.UTF_8);
                cbEmpresas.getItems().add(auxNombre);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void datosTutores() {
        try {
            Statement s = cbd.conectar().createStatement();
            ResultSet rs = s.executeQuery("select * from tutores_p");

            String auxNombre;
            while (rs.next()) {
                byte[] dataNombre = rs.getBytes("Nombre");
                auxNombre = new String(dataNombre, StandardCharsets.UTF_8);
                cbTutores.getItems().add(auxNombre);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    
    @FXML
    private void controllerConfirmar(ActionEvent event) {
        try {
                    Connection conexion = cbd.conectar();

                    String alumno = cbAlumnos.getSelectionModel().getSelectedItem();
                    String[] apellidoAux = alumno.split(",");
                    String codAlumno = null;
                    String empresa = cbEmpresas.getSelectionModel().getSelectedItem();
                    String codEmpresa = null;
                    String tutor = cbTutores.getSelectionModel().getSelectedItem();
                    String codTutor = null;

                    try {
                        Statement s = conexion.createStatement();
                        ResultSet rsCodAlumno = s.executeQuery("SELECT Cod_Alumno from Alumnos where Apellidos = '" + apellidoAux[0] + "'");
                        while(rsCodAlumno.next()){
                            codAlumno = rsCodAlumno.getString(1);
                        }

                        ResultSet rsCodEmpresa = s.executeQuery("SELECT Cod_Empresas from Empresas where Nombre = '" + empresa + "'");
                        while(rsCodEmpresa.next()){
                            codEmpresa = rsCodEmpresa.getString(1);
                        }
                        ResultSet rsCodTutor = s.executeQuery("SELECT Cod_Tutor from tutores_p where Nombre = '" + tutor + "'");
                        while(rsCodTutor.next()){
                            codTutor = rsCodTutor.getString(1);
                        }

                        PreparedStatement ps = conexion.prepareStatement
                                ("INSERT INTO Asignacion (Cod_Alumno, Cod_Tutor, Cod_Empresas, Fecha_Asignacion) VALUES (?, ?, ?, ?)");
                        String auxTime = String.valueOf(new Timestamp(System.currentTimeMillis()));
                        auxTime = auxTime.substring(0, auxTime.length()-4);
                        ps.setString(1, codAlumno);
                        ps.setString(2, codTutor);
                        ps.setString(3, codEmpresa);
                        ps.setString(4, auxTime);
                        System.out.println(ps);

                        if (ps.executeUpdate() == 1) {
                            ResultSet rsTutorLaboral = s.executeQuery("SELECT Nombre_TL, Apellidos_TL from " +
                                    "empresas where Nombre = '" + empresa + "'");
                            String nombreTL = null;
                            String apellidoTL = null;
                            while (rsTutorLaboral.next()) {
                                nombreTL = rsTutorLaboral.getString(1);
                                apellidoTL = rsTutorLaboral.getString(2);
                            }

                            String mensaje = "El alumno " + alumno + " queda asignado a la empresa "
                                    + empresa + " supervisado por el tutor docente " + tutor +
                                    " y por el tutor laboral " + nombreTL + " " + apellidoTL;
                            
                            lMensaje.setText(mensaje);
                
                            lMensaje.setWrapText(true);
                            lMensaje.setMaxWidth(500);
                            lMensaje.setTextAlignment(TextAlignment.CENTER);
                            lMensaje.setVisible(true);
                            addRow("ASIGNACION", tvTablaAsig);
                        } else {
                            //JOptionPane.showMessageDialog(viewAsignacion, ("No se ha podido registrar la asignación"));
                        }

                        ps.close();
                    } catch (Exception ee) {
                        System.out.println(ee.getClass());
                    }

                    conexion.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            
    }
    @FXML
    private void controllerAyuda() {                                         
            Help help = new Help();
            help.setVisible(true);
    }    
    
}
