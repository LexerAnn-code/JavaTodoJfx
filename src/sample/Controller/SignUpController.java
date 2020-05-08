package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.DataBase.DBConnection;
import sample.Model.User;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable, DBConnection {
    static private PreparedStatement preparedStatement;
    static private Connection connection;

    @FXML
    private TextField PasswordTextSignUp;

    @FXML
    private JFXButton SignUpButton;

    @FXML
    private JFXCheckBox maleCheckBox;

    @FXML
    private JFXCheckBox femaleCheckButton;

    @FXML
    private TextField userNameTextSignUp;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField firstNameText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            SignUpButton.setOnAction(events->{
                String firstName=firstNameText.getText();
                String lastName=lastNameText.getText();
                String userName=userNameTextSignUp.getText();
                String password=PasswordTextSignUp.getText();
                String gender;

                if(maleCheckBox.isSelected()){
                    gender="Male";
                }
                else{
                    gender="Female";
                }
                User user=new User(firstName,lastName,userName,password,gender);
                try {

                    writeToDB(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    @Override
    public void connect() throws SQLException {
        ControllerConnect();
    }
    private void ControllerConnect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }
    private void writeToDB(User user) throws SQLException {
        String insert = "INSERT INTO users(firstname,lastname,username,password,gender)" + "VALUES(?,?,?,?,?)";
        preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getUserName());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getGender());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
