import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

/**
 * @author Francisco Rodríguez Espinosa
 */

public class View extends JFrame {

    public JPanel pMain; // Panel principal
    public JPanel pData; // Panel que contiene el formulario
        JPanel pInsert;
            // Norte
            JPanel pName;
                JLabel lName;
                public static JTextField tfName;
                public static JButton bAdd;
            // Centro
            JPanel pSurname;
                JLabel lSurname;
                public static JTextField tfSurname;
            // Sur
            JPanel pAge;
                JLabel lAge;
                public static JTextField tfAge;
                public static JButton bDelete;
        JPanel pInfo;
            // Norte
            JPanel pInfoName;
                JLabel lInfoName;
                public static JLabel lShowName;
            // Centro
            JPanel pInfoSurname;
                JLabel lInfoSurname;
                public static JLabel lShowSurname;
            // Norte
            JPanel pInfoAge;
                JLabel lInfoAge;
                public static JLabel lShowAge;
    public JPanel pList; // Panel que contiene la lista
    public static DefaultListModel model;
    public static JList lNames;




    // Constructor de la vista
    public View() throws IOException {
        // PANEL MAIN
        pMain = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Border borderTransparency = BorderFactory.createLineBorder(Color.decode("#EEEEEE"));

        // PANEL DATA
        pData = new JPanel(new BorderLayout(10,10));
        // INSERT
        pInsert = new JPanel(new BorderLayout(10,10));
        Border borderInsert = BorderFactory.createTitledBorder(borderTransparency, "Agregar Persona:");
        pInsert.setBorder(BorderFactory.createCompoundBorder(borderInsert, BorderFactory.createEmptyBorder(5,5,5,5)));
        // NAME
        pName = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        lName = new JLabel("Nombre:    ");
        pName.add(lName);
        tfName = new JTextField(12);
        pName.add(tfName);
        bAdd = new JButton("Añadir");
        pName.add(bAdd);
        pInsert.add(pName, BorderLayout.NORTH);
        // SURNAME
        pSurname = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        lSurname = new JLabel("Apellidos:  ");
        pSurname.add(lSurname);
        tfSurname = new JTextField(12);
        pSurname.add(tfSurname);
        pInsert.add(pSurname, BorderLayout.CENTER);
        // AGE
        pAge = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        lAge = new JLabel("Edad:         ");
        pAge.add(lAge);
        tfAge = new JTextField(12);
        pAge.add(tfAge);
        bDelete = new JButton("Eliminar");
        pAge.add(bDelete);
        pInsert.add(pAge, BorderLayout.SOUTH);

        //INFO
        pInfo = new JPanel(new BorderLayout(10,10));
        Border borderInfo = BorderFactory.createTitledBorder(borderTransparency, "Información:");
        pInfo.setBorder(BorderFactory.createCompoundBorder(borderInfo, BorderFactory.createEmptyBorder(5,5,5,5)));
        // NAME
        pInfoName = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        lInfoName = new JLabel("Nombre:");
        pInfoName.add(lInfoName);
        lShowName = new JLabel("");
        pInfoName.add(lShowName);
        pInfo.add(pInfoName, BorderLayout.NORTH);
        // SURNAME
        pInfoSurname = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        lInfoSurname = new JLabel("Apellidos:");
        pInfoSurname.add(lInfoSurname);
        lShowSurname = new JLabel("");
        pInfoSurname.add(lShowSurname);
        pInfo.add(pInfoSurname, BorderLayout.CENTER);
        // AGE
        pInfoAge = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        lInfoAge = new JLabel("Nombre:");
        pInfoAge.add(lInfoAge);
        lShowAge = new JLabel("");
        pInfoAge.add(lShowAge);
        pInfo.add(pInfoAge, BorderLayout.SOUTH);

        // PANEL LIST
        pList = new JPanel();
        model = new DefaultListModel();
        lNames = new JList<>(model);
        lNames.setPreferredSize(new Dimension(150,250));
        pList.add(lNames);



        // AÑADIR A UI
        pData.add(pInsert, BorderLayout.NORTH);
        pData.add(pInfo, BorderLayout.SOUTH);

        pMain.add(pData);
        pMain.add(pList);
        // CONFIGURACIÓN FINAL
        add(pMain);
        setTitle("");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
