package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.Stack;

public class EnterInputController extends Model {
    @FXML
    Label result;

    @FXML
    TextArea inputArea;

    @FXML
    public void back(ActionEvent event) throws IOException {
        super.back(event);
    }

    public void loadInput() {
        result.setText(" ");
        Stack<Integer> stack = new Stack<>();
        int currentHeight, posters=0;
        String[] buildingMeasurements;
        try {
            for (String line : inputArea.getText().split("\\n")) {
                buildingMeasurements = line.split(" ");
                if (buildingMeasurements.length != 1) {
                    currentHeight = Integer.valueOf(buildingMeasurements[1]);
                    while (stack.size() != 0 && stack.peek() > currentHeight) {
                        stack.pop();
                    }
                    if (stack.size() == 0 || stack.peek() < currentHeight) {
                        stack.push(currentHeight);
                        posters++;
                    }
                }
            }
            result.setText(String.valueOf(posters));
        } catch (Exception e)
        {
            result.setText("Incorrect input format");
        }

    }

}
