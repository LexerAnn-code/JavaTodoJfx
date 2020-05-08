package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import sample.Controller.*;
import sample.DataBase.DBConnection;
import sample.DataBase.MySQLConnection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.setResizable(false);
        primaryStage.show();
        DBConnection dbConnection=new SignUpController();
        DBConnection dbConnectionLogin=new LoginController();
        DBConnection dbConnectionTask=new AddItemControllerForm();
        DBConnection dbConnectionTaskCell=new ListController();
        DBConnection dbConnectionTaskCellDelete=new Extension();

        MySQLConnection mySQLConnection=new MySQLConnection();
        mySQLConnection.connectSQL(dbConnectionTask);
        mySQLConnection.connectSQL(dbConnectionTaskCellDelete);

        mySQLConnection.connectSQL(dbConnection);
        mySQLConnection.connectSQL(dbConnectionLogin);
        mySQLConnection.connectSQL(dbConnectionTaskCell);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
