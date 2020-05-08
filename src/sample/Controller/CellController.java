package sample.Controller;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.DataBase.DBConnection;
import sample.Model.Task;

import java.io.IOException;
import java.sql.*;

public class CellController extends JFXListCell<Task>  {

    FXMLLoader fxmlLoader;
    @FXML
    private Label descripCell;
    @FXML
    private AnchorPane rootanchor;

    @FXML
    private Label taskcell;

    @FXML
    private Label dateCell;
    @FXML
    private ImageView deleteIcon;



    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);
        if (empty || task == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/View/cell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

                taskcell.setText(task.getTask());
                descripCell.setText(task.getDescription());
                dateCell.setText(task.getDateCreated());

                deleteIcon.setOnMouseClicked(mouseEvent -> {
                    getListView().getItems().remove(getItem());
                    Extension extension=new Extension();

                    extension.deleteTask(task);


                });
                setText(null);
                setGraphic(rootanchor);
            }
        }

    }






