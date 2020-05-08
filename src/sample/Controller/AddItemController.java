package sample.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.print.PrinterException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {
Stage stage;
Parent root;
Scene scene;
  private int userIDPass;
  public static int id;


    public void setUserIDPass(int userIDPass) {
        this.userIDPass = userIDPass;
    }
    public int getUserIDPass() {
        System.out.println("GET"+ userIDPass);
        return userIDPass;
    }


    @FXML
    private ImageView Image;
    @FXML
    private JFXButton addButtonTask4;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

            addButtonTask4.setOnAction(event->{
            try{
                root= FXMLLoader.load(getClass().getResource("/sample/View/AddItemForm.fxml"));
                scene=new Scene(root,600,475);
                stage=(Stage) addButtonTask4.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                id=getUserIDPass();
                System.out.println("ID IN ADDITEM->>"+getUserIDPass());
                System.out.println("ID IN ADDITEMssss->>"+id);
            }   catch (Exception e){
                e.printStackTrace();
            }


            });

    }


    public void setUserIDNew(int userID) {
        this.userIDPass=userID;
    }
}
