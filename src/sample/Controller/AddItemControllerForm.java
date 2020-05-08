package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataBase.DBConnection;
import sample.Model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;


public class AddItemControllerForm implements Initializable, DBConnection {
    static private PreparedStatement preparedStatement;
    static private PreparedStatement preparedStatement2;
    static private Connection connection;
    ResultSet resultSets;
    Parent root;
    Stage stage;
    Scene scene;

    int val;
    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private JFXButton taskTodo;

    @FXML
    private TextField taskTitle;

    @FXML
    private TextField taskDescription;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Form->>" + AddItemController.id);

        saveTaskButton.setOnAction(actionEvent ->{
            Task tasker=new Task();
            Calendar calendar=Calendar.getInstance();
            java.sql.Timestamp timestamp=new java.sql.Timestamp(calendar.getTimeInMillis());

            tasker.setTask(taskTitle.getText());
            tasker.setDescription(taskDescription.getText());
            tasker.setDateCreated(timestamp.toString());

            insertTask(tasker);
});
taskTodo.setOnAction(actionEvent -> {
    try {
        root = FXMLLoader.load(getClass().getResource("/sample/View/list.fxml"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    scene = new Scene(root, 600, 475);
    stage = (Stage) taskTodo.getScene().getWindow();
    stage.setScene(scene);
    stage.show();

});
    }

    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");
        System.out.println("Connection " + connection.getCatalog());

    }


    public void insertTask(Task tasker){


String query="INSERT INTO task(userid,datecreated,description,task)" + "VALUES(?,?,?,?)";
        try {
            preparedStatement=(PreparedStatement) connection.prepareStatement(query);
            Task tasky=new Task();
            preparedStatement.setInt(1,AddItemController.id);
            preparedStatement.setString(2,tasker.getDateCreated());
            preparedStatement.setString(3,tasker.getDescription());
            preparedStatement.setString(4,tasker.getTask());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
