import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Francisco Rodríguez Espinosa
 */

public class Controller {

    // Inicializa el constructor
    public Controller() throws IOException {
        readFile();
        insert();
        select();
        delete();
    }

    public List<String> listNames; // Lista de nombres guardados
    File file = new File("src/fichero.txt"); // Fichero donde se encuentran los nombres

    /**
     * Lee el fichero con los nombres
     */
    public void readFile() throws IOException {
        View.model.clear();
        BufferedReader br = new BufferedReader(new FileReader(file)); // Conexión con el fichero
        String stAux;
        listNames = new ArrayList<>();
        // Bucle para leer todos los nombres del fichero
        while ((stAux = br.readLine()) != null){
            listNames.add(stAux);
        }
        String[] items = new String[listNames.size()];
        List<String> listAux = new ArrayList<>();
        listNames.forEach(name -> listAux.add(name.split(",")[0]));
        listAux.toArray(items);
        for (int i = 0; i < items.length; i++) {
            View.model.add(i, items[i]);
        }
    }

    /**
     * Escribe los nuevos nombres en el fichero
     */
    public void writeFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, false)); // Conexión con el fichero
        // Bucle que escribe todos los nombres de la lista en nuestro fichero
        listNames.forEach(name -> {
            try {
                System.out.println(name);
                bw.write(name+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inserta un nuevo nombre en el fichero cuando pulsamos sobre el botón de insertar
     */
    public void insert(){
        View.bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Extrae los datos del campo de texto y los introduce en el fichero
                listNames.add(View.tfName.getText()+","+View.tfSurname.getText()
                +","+View.tfAge.getText());
                View.tfName.setText("");
                View.tfSurname.setText("");
                View.tfAge.setText("");
                try {
                    writeFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    readFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    /**
     * Muestra la información de las personas registradas en el fichero tras seleccionarlo
     */
    public void select(){
        View.lNames.addListSelectionListener(e -> {
            for(String person :listNames){
                String aux[] = person.split(",");
                // Escribe todos los datos en los campos de texto de la interfaz
                if(aux[0].equals(View.lNames.getSelectedValue())){
                    View.tfName.setText(aux[0]);
                    View.lShowName.setText(aux[0]);
                    View.tfSurname.setText(aux[1]);
                    View.lShowSurname.setText(aux[1]);
                    View.tfAge.setText(aux[2]);
                    View.lShowAge.setText(aux[2]);
                }
            }
        });
    }

    /**
     * Borra una persona del fichero en caso de pulsar el botón de eliminar
     */
    public void delete(){
        View.bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aux = View.tfName.getText()+","+View.tfSurname.getText()
                        +","+View.tfAge.getText();
                for (int i=0; i<listNames.size(); i++){
                    if (listNames.get(i).equals(aux)){
                        listNames.remove(i);
                        break;
                    }
                }
                try {
                    writeFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    readFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                View.tfName.setText("");
                View.tfSurname.setText("");
                View.tfAge.setText("");
            }
        });

    }

}
