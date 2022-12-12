package controller;



import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;




/**
 *
 * @author SAFA
 */
public class Main extends Application{
    public static Stage stage;
     @Override
     public void start(Stage primaryStage) throws SQLException {
         
        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/ViewEmpresa.fxml"));
            Pane ventana =(Pane) loader.load();
            
            Scene scene =new Scene(ventana);
            primaryStage.getIcons().add(new Image("/view/logo.png"));
            primaryStage.setScene(scene);
            primaryStage.show();
            stage = primaryStage;
            
           
        }catch (IOException e){
            System.out.println(e.getMessage());
          
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
        
    }
    public static void resizeScene(double width, double height) {
        stage.setWidth(width);
        stage.setHeight(height);
}
    
}
