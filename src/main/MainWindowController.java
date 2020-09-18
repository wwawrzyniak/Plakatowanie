package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.*;

public class MainWindowController extends Application {
    public Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Plakatowanie Weronika Wawrzyniak");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    @FXML
    private void joinAction(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource((((Control)event.getSource()).getId())+".fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
