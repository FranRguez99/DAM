package Actividad1;

import javax.swing.*;
import javax.swing.event.*;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.UnknownHostException;

class Actividad1 extends JFrame {
    private static final long serialVersionUID = 1L;

    //campos de cabecera parte superior
    static JTextField cab = new JTextField();
    static JTextField cab2 = new JTextField();
    static JTextField cab3 = new JTextField();

    //campos de mensajes parte inferior
    private static JTextField campo = new JTextField();
    private static JTextField campo2 = new JTextField();

    //botones
    JButton botonConectar = new JButton("Conectar");
    JButton botonCargar = new JButton("Subir fichero");
    JButton botonDescargar = new JButton("Descargar fichero");
    JButton botonBorrar = new JButton("Eliminar fichero");
    JButton botonCreaDir = new JButton("Crear carpeta");
    JButton botonDelDir = new JButton("Eliminar carpeta");
    JButton botonSalir = new JButton("Salir");

    //lista para los datos del directorio
    static JList listaDirec = new JList();

    //contenedor
    private final Container c = getContentPane();

    //Datos del servidor FTP
    static FTPClient cliente = new FTPClient();//cliente FTP
    static String servidor = "";
    static String user = "";
    static String pasw = "";
    boolean login;
    static boolean loginOK = false;
    static String direcInicial = "/";

    //para saber directorio y fichero seleccionado
    static String direcSelec = direcInicial;
    static String ficheroSelec ="";

    // constructor
    public Actividad1() throws IOException
    {
        super("Actividad 1 - PJRG");

        System.out.println("Conectandose a " + servidor);
        campo.setBounds(new Rectangle(3, 485, 485, 30));
        campo.setForeground(Color.blue);
        campo.setFont(new Font("Verdana", Font.BOLD, 12));
        campo.setText("<<   ARBOL DE DIRECTORIOS CONSTRUIDO    >>");
        campo2.setBounds(new Rectangle(3, 515, 485, 30));
        campo2.setForeground(Color.blue);
        campo2.setFont(new Font("Verdana", Font.BOLD, 12));
        campo2.setText(" ");

        cab.setBounds(new Rectangle(5, 5, 200, 30));
        cab.setBorder(null);
        cab.setForeground(Color.blue);
        cab.setFont(new Font("Arial", Font.BOLD, 14));
        cab.setText("Servidor FTP: " + servidor);

        cab2.setBounds(new Rectangle(350, 5, 140, 30));
        cab2.setBorder(null);
        cab2.setFont(new Font("Arial", Font.BOLD, 14));
        cab2.setForeground(Color.blue);
        cab2.setText("Usuario: " + user);

        cab3.setBounds(new Rectangle(5, 34, 145, 30));
        cab3.setBorder(null);
        cab3.setFont(new Font("Arial", Font.BOLD, 14));
        cab3.setForeground(Color.blue);
        cab3.setText("DIRECTORIO RAIZ: " + direcInicial);

        botonConectar.setBounds(new Rectangle(346, 65, 140, 30));
        botonCargar.setBounds(new Rectangle(346, 115, 140, 30));
        botonCargar.setEnabled(false);
        botonDescargar.setBounds(new Rectangle(346, 165, 140, 30));
        botonDescargar.setEnabled(false);
        botonBorrar.setBounds(new Rectangle(346, 215, 140, 30));
        botonBorrar.setEnabled(false);
        botonCreaDir.setBounds(new Rectangle(346, 265, 140, 30));
        botonCreaDir.setEnabled(false);
        botonDelDir.setBounds(new Rectangle(346, 315, 140, 30));
        botonDelDir.setEnabled(false);
        botonSalir.setBounds(new Rectangle(346, 365, 140, 30));


        //PREPARACION DE LA LISTA
        listaDirec.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane barraDesplazamiento = new JScrollPane(listaDirec);
        barraDesplazamiento.setPreferredSize(new Dimension(335, 420));
        barraDesplazamiento.setBounds(new Rectangle(5, 65, 335, 420));
        c.add(barraDesplazamiento);
        c.setLayout(null);

        c.add(campo);
        campo.setEditable(false);
        c.add(campo2);
        campo2.setEditable(false);
        c.add(botonConectar);
        c.add(botonCargar);
        c.add(botonDescargar);
        c.add(botonBorrar);
        c.add(botonSalir);
        c.add(botonCreaDir);
        c.add(botonDelDir);
        c.add(cab);
        c.add(cab2);
        c.add(cab3);
        cab.setEditable(false);
        cab2.setEditable(false);
        cab3.setEditable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(510, 600);
        setVisible(true);

        //--clic en un elemento de la lista
        listaDirec.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent lse) {
                if (lse.getValueIsAdjusting()) {

                    ficheroSelec = "";

                    //elemento seleccionado de la lista
                    String fic = listaDirec.getSelectedValue().toString();

                    if (listaDirec.getSelectedIndex() == 0) {
                        //Se hace clic en el primer elemento del JList
                        if (!fic.equals(direcInicial)) {
                            //si no estamos en el dictorio inicial, hay que
                            //subir al directorio padre
                            try {
                                cliente.changeToParentDirectory();
                                direcSelec = cliente.printWorkingDirectory();
                                cliente.changeWorkingDirectory(direcSelec);
                                FTPFile[] ff2 = cliente.listFiles();
                                campo.setText("");
                                //se llena la lista con fich. del directorio padre
                                llenarLista(ff2, direcSelec);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    //No se hace clic en el primer elemento del JList
                    //Puede ser un fichero o un directorio
                    else {
                        if (fic.substring(0, 6).equals("(DIR) ")) {
                            //SE TRATA DE UN DIRECTORIO
                            try {
                                fic = fic.substring(6);
                                String direcSelec2 = "";
                                if (direcSelec.equals("/"))
                                    direcSelec2 = direcSelec + fic;
                                else
                                    direcSelec2 = direcSelec + "/" + fic;
                                FTPFile[] ff2 = null;
                                cliente.changeWorkingDirectory(direcSelec2);
                                ff2 = cliente.listFiles();
                                campo.setText("DIRECTORIO:  " + fic + ", " + ff2.length + " elementos");
                                direcSelec = direcSelec2;
                                llenarLista(ff2, direcSelec);
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            // SE TRATA DE UN FICHERO
                            ficheroSelec = direcSelec;
                            if (direcSelec.equals("/"))
                                ficheroSelec += fic;
                            else
                                ficheroSelec += "/" + fic;
                            campo.setText("FICHERO seleccionado:" +
                                ficheroSelec);
                            ficheroSelec = fic;
                        }//fin else
                    }//else de fichero o directorio
                    campo2.setText("DIRECTORIO ACTUAL: " + direcSelec);
                }//fin if inicial
            }
        });// fin lista

        // --al hacer clic en el botón Conectar
        botonConectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                while (!loginOK) {
                    if(iniciarSesion()){
                        try{
                            cliente.addProtocolCommandListener(new PrintCommandListener (new PrintWriter (System.out) ));
                            cliente.connect(servidor);
                            cliente.enterLocalPassiveMode();
                            login = cliente.login(user, pasw);
                            if(login){
                                cliente.changeWorkingDirectory(direcInicial);
                                FTPFile[] files = cliente.listFiles();
                                //Construyendo arbol de directorios, espere un momento
                                llenarLista(files, direcInicial);
                                loginOK = true;
                                JOptionPane.showMessageDialog(null, "Conexión realizada con éxito");
                                botonCargar.setEnabled(true);
                                botonDescargar.setEnabled(true);
                                botonBorrar.setEnabled(true);
                                botonCreaDir.setEnabled(true);
                                botonDelDir.setEnabled(true);
                                cab.setText("Servidor FTP: " + servidor);
                                cab2.setText("Usuario: " + user);
                                cab3.setText("DIRECTORIO RAIZ: " + direcInicial);
                            }else{
                                throw new UnknownHostException("Conexión fallida");
                            }

                        } catch (UnknownHostException ex) {
                            JOptionPane.showMessageDialog(null, "Conexión fallida. Datos erróneos");
                            //System.exit(0);
                        } catch (IOException ex){

                        }
                    }else{

                        System.exit(0);
                    }
                }
            }
        });
        // --al hacer clic en el botón Salir
        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente.disconnect();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });

        // Botón crear carpeta
        botonCreaDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreCarpeta = JOptionPane.showInputDialog(null,
                    "Introduce el nombre del directorio",
                    "carpeta");
                if (!(nombreCarpeta==null)) {
                    String directorio=direcSelec;
                    if (!direcSelec.equals("/"))
                        directorio =directorio +"/";
                    directorio+=nombreCarpeta.trim() ;

                    try {
                        if (cliente.makeDirectory(directorio)) {
                            String m= nombreCarpeta.trim()	+ " => Se ha creado correctamente ...";
                            JOptionPane.showMessageDialog(null, m);
                            campo.setText(m);
                            cliente.changeWorkingDirectory(direcSelec);
                            FTPFile[] ff2 = cliente.listFiles();
                            llenarLista(ff2, direcSelec);

                        } else
                            JOptionPane.showMessageDialog(null, nombreCarpeta.trim()
                                + " => No se ha podido crear ...");

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        botonDelDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreCarpeta = JOptionPane.showInputDialog(null,
                    "Introduce el nombre del directorio a eliminar",
                    "carpeta");
                if (!(nombreCarpeta==null)) {
                    String directorio=direcSelec;
                    if (!direcSelec.equals("/"))
                        directorio =directorio +"/";
                    directorio+=nombreCarpeta.trim() ;

                    try {
                        if (cliente.removeDirectory(directorio)) {
                            String m= nombreCarpeta.trim()	+ " => Se ha eliminado correctamente ...";
                            JOptionPane.showMessageDialog(null, m);
                            campo.setText(m);
                            cliente.changeWorkingDirectory(direcSelec);
                            FTPFile[] ff2 = cliente.listFiles();
                            llenarLista(ff2, direcSelec);

                        } else
                            JOptionPane.showMessageDialog(null, nombreCarpeta.trim()
                                + " => No se ha podido eliminar ...");

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // Botón subir
        botonCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.FILES_ONLY);
                f.setDialogTitle("Selecciona el Fichero a SUBIR AL SERVIDOR FTP");
                int returnVal = f.showDialog(f, "Cargar");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = f.getSelectedFile();
                    String archivo = file.getAbsolutePath();
                    String nombreArchivo = file.getName();
                    try {
                        SubirFichero(archivo, nombreArchivo);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // --al hacer clic en el botón Descargar
        botonDescargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String directorio=direcSelec;
                if (!direcSelec.equals("/"))
                    directorio =directorio +"/";
                if(!ficheroSelec.equals(""))
                {
                    DescargarFichero(directorio + ficheroSelec ,ficheroSelec);
                }
            }
        });// Fin boton descargar

        // Botón borrar
        botonBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("borrar");
                String directorio=direcSelec;
                if (!direcSelec.equals("/"))
                    directorio =directorio +"/";
                if(!ficheroSelec.equals(""))
                    BorrarFichero(directorio + ficheroSelec ,ficheroSelec);

            }
        });

    }// ..FIN CONSTRUCTOR

    // -----------------------------------------------------------------------------

    private static void llenarLista(FTPFile[] files, String direc2) {
        if (files == null)	return;
        DefaultListModel modeloLista = new DefaultListModel();

        listaDirec.setForeground(Color.blue);
        Font fuente = new Font("Courier", Font.PLAIN, 12);
        listaDirec.setFont(fuente);
        listaDirec.removeAll();

        try {
            cliente.changeWorkingDirectory(direc2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        direcSelec = direc2;
        modeloLista.addElement(direc2);
        for (int i = 0; i < files.length; i++) {
            if (!(files[i].getName()).equals(".")
                && !(files[i].getName()).equals("..")) {
                String f = files[i].getName();
                if (files[i].isDirectory())
                    f = "(DIR) " + f;
                modeloLista.addElement(f);
            }//if
        }// fin for
        try {
            listaDirec.setModel(modeloLista);
        } catch (NullPointerException n) {
            //Al llegar al último aparece excepcion
            ;System.out.println("linea 334 - llega al ultimo");
        }
    }//Fin llenarLista

    private void DescargarFichero(String NombreCompleto, String nombreFichero) {

        String archivoyCarpetaDestino = "";
        String carpetaDestino = "";
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.setDialogTitle("Selecciona el Directorio donde DESCARGAR el fichero");

        int returnVal = f.showDialog(null, "Descargar");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = f.getSelectedFile();

            carpetaDestino = (file.getAbsolutePath()).toString();
            //System.out.println("carpeta destino " + carpetaDestino);
            archivoyCarpetaDestino = carpetaDestino + File.separator + nombreFichero;

            try {
                cliente.setFileType(FTP.BINARY_FILE_TYPE);
                BufferedOutputStream out = null;
                out = new BufferedOutputStream(new FileOutputStream(archivoyCarpetaDestino));


                if (cliente.retrieveFile(NombreCompleto, out))
                    JOptionPane.showMessageDialog(null, nombreFichero + " => Se ha descargado correctamente ...");
                else
                    JOptionPane.showMessageDialog(null, nombreFichero + " => No se ha podido descargar ...");

                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }// ..DescargarFichero

    private void BorrarFichero(String NombreCompleto, String nombreFichero) {
        //pide confirmacion
        int seleccion = JOptionPane.showConfirmDialog(null,
            "¿Desea eliminar el fichero seleccionado?");
        if(seleccion==JOptionPane.OK_OPTION) {
            try {
                if (cliente.deleteFile (NombreCompleto)) {
                    String m= nombreFichero + " => Eliminado correctamente... ";
                    JOptionPane.showMessageDialog(null, m);
                    campo.setText(m);
                    cliente.changeWorkingDirectory(direcSelec);
                    FTPFile[] ff2 = cliente.listFiles();
                    llenarLista(ff2, direcSelec);
                } else
                    JOptionPane.showMessageDialog(null, nombreFichero
                        + " => No se ha podido eliminar ...");

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }// ..BorrarFichero

    private  boolean SubirFichero(String archivo, String nombreArchivo)
        throws IOException {

        System.out.println("Archivo : " +archivo);

        cliente.setFileType(FTP.BINARY_FILE_TYPE);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
            archivo));
        boolean ok = false;
        // System.out.println("Directorio => " +direcSelec);
        cliente.changeWorkingDirectory(direcSelec);
        if (cliente.storeFile(nombreArchivo, in)) {
            String s = "  " + nombreArchivo + " => Subido correctamente... ";
            campo.setText(s);
            campo2.setText("Se va a actualizar el árbol de directorios...");
            JOptionPane.showMessageDialog(null, s);
            FTPFile[] ff2 = cliente.listFiles();
            llenarLista(ff2, direcSelec);
            ok = true;
        } else
            campo.setText("No se ha podido subir... " + nombreArchivo);
        return ok;
    }// SubirFichero



    public static boolean iniciarSesion()
    {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Servidor", SwingConstants.RIGHT));
        label.add(new JLabel("Usuario", SwingConstants.RIGHT));
        label.add(new JLabel("Contraseña", SwingConstants.RIGHT));

        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField txtservidor = new JTextField();
        controls.add(txtservidor);
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        int n = JOptionPane.showConfirmDialog(frame, panel, "Datos conexion", JOptionPane.OK_CANCEL_OPTION);

        if(n == JOptionPane.OK_OPTION)
        {
            user = username.getText();
            pasw = new String(password.getPassword());
            servidor = txtservidor.getText();
            return true;
        }
            else if(n == JOptionPane.CANCEL_OPTION)
            {
                return false;
            }

        return false;
    }


    public static void main(String[] args) throws IOException
    {
        new Actividad1();
    }
}