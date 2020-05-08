package sample.Controller;

import org.w3c.dom.ls.LSOutput;
import sample.DataBase.DBConnection;
import sample.Model.Task;

import java.sql.*;


public class Extension implements DBConnection {
    static private PreparedStatement preparedStatement;
    static private Connection connection;


    public void deleteTask(Task task){
        int id=task.getTaskid();
        System.out.println("ID DEL->>" + id);
        ResultSet resultSet = null;
        String query="DELETE FROM task WHERE userid = ? AND taskid=?";
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setInt(1, AddItemController.id);
            preparedStatement.setInt(2, task.getTaskid());
           preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }
}
