package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.*;
import java.util.Stack;

public class Model {

    @FXML
    public void back(ActionEvent event) throws IOException {
        String name = ((Control) event.getSource()).getId() + ".fxml";
        Parent tableViewParent = FXMLLoader.load(getClass().getResource(name));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public Integer calculate(File file) throws FileNotFoundException {
        Stack<Integer> stack = new Stack<Integer>();
        String line;
        String[] buildingMeasurements = new String[2];
        int currentHeight, posters = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            while ((line = br.readLine()) != null) {
                buildingMeasurements = line.split(" ");
                currentHeight = Integer.valueOf(buildingMeasurements[1]);
                while (stack.size() != 0 && stack.peek() > currentHeight) {
                    stack.pop();
                }
                if (stack.size() == 0 || stack.peek() < currentHeight) {
                    stack.push(currentHeight);
                    posters++;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            return posters;
        }
    }

}
