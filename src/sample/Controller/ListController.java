package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.DataBase.DBConnection;
import sample.Model.Task;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ListController implements Initializable, DBConnection {
    static private PreparedStatement preparedStatement;
    static private Connection connection;

    @FXML
    private JFXTextField taskTitle;

    @FXML
    private JFXTextField taskDescription;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private JFXListView<Task> listView;
    ObservableList<Task> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        list = FXCollections.observableArrayList();


        try {

            ResultSet userRes = getTaskByID();
            while (userRes.next()) {
                Task task1 = new Task();
                task1.setTaskid(userRes.getInt("taskid"));
                task1.setDateCreated(userRes.getString("datecreated"));
                task1.setDescription(userRes.getString("description"));
                task1.setTask(userRes.getString("task"));

                list.addAll(task1);
            }
                listView.setItems(list);
                listView.setCellFactory(CellController -> new CellController());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getTaskByID() throws SQLException {

        ResultSet resultSet = null;
        String query = "SELECT * FROM task WHERE userid=?";
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setInt(1, AddItemController.id);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public static void main(String[] args) {

    }

    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }
}
