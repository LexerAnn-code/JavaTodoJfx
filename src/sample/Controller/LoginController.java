package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sample.DataBase.DBConnection;
import sample.Model.User;
import sample.anim.Shaker;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Handler;

public class LoginController implements Initializable, DBConnection {
    Stage stage;
    Scene scene;
    Parent root;
    Stage stageTask;
    Scene sceneTask;
    Parent rootTask;
    static private PreparedStatement preparedStatement;
    static private Connection connection;
    int userID;
    @FXML
    private JFXButton signUpButton;



    @FXML
    private JFXButton loginButton;


    @FXML
    private Label toastError;
    @FXML
    private TextField userNameText;

    @FXML
    private TextField PasswordText;

    @Override
    public void connect() throws SQLException {
        ControllerConnect();
    }

    private void ControllerConnect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpButton.setOnAction(event -> {
            System.out.println("passed");
            try {

                root = FXMLLoader.load(getClass().getResource("/sample/View/signUp.fxml"));
                scene = new Scene(root, 600, 475);
                stage = (Stage) signUpButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        loginButton.setOnAction(event->{
            String userName=userNameText.getText();
            String password=PasswordText.getText();
            User user=new User();
            user.setUserName(userName);
            user.setPassword(password);
            try {
                ResultSet userRes=getUsers(user);
                int counter=0;
                while(userRes.next()){
                    String userName_res=userRes.getString("userName");
                    String password_res=userRes.getString("password");
                    System.out.println("Welcome" + userName_res + "password->" + password_res );
                   userID=userRes.getInt("userid");

                    counter++;
                }
                if(counter==1){
                    loginButton.getScene().getWindow().hide();
                    System.out.println("Login Successful");
                    FXMLLoader fxmlLoader=new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/sample/View/addTask.fxml"));

                    try {
                        fxmlLoader.setRoot(fxmlLoader.getRoot());
                        fxmlLoader.load();
                    }catch (IOException e){

                    }
                    Parent root=fxmlLoader.getRoot();
                    Stage stage=new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    AddItemController controller=fxmlLoader.getController();
                    System.out.println("USERID->>"+ userID);
                    controller.setUserIDPass(userID);

                }
                else {

                    Shaker shakerUserName=new Shaker(userNameText);
                    Shaker shakerPassword=new Shaker(PasswordText);
                    toastError.setVisible(true);
                    Shaker shakerToastM=new Shaker(toastError);

                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    public ResultSet getUsers(User user) throws SQLException {
        ResultSet resultset=null;
        if(!userNameText.getText().equals("") || !PasswordText.getText().equals("")){
            String query="SELECT * FROM users WHERE username=? AND password=?";
            try {
                preparedStatement=(PreparedStatement) connection.prepareStatement(query);
                preparedStatement.setString(1,user.getUserName());
                preparedStatement.setString(2,user.getPassword());
                resultset= (ResultSet) preparedStatement.executeQuery();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return resultset;
    }
}
