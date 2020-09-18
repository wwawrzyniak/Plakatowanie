package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class FromFileController extends Model{
    @FXML
    AnchorPane ap;

    @FXML
    Label inputResult, outputResult,congratulations;

    File selectedFile;
    FileChooser fileChooser = new FileChooser();
    Stage stage;
    @FXML
    private void loadInput() throws FileNotFoundException {
        congratulations.setText(" ");
        outputResult.setText(" ");
        stage=(Stage) ap.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile!=null) {
            String x = String.valueOf(calculate(selectedFile));
            inputResult.setText(x);
        }
    }

    @FXML
    private void loadOutput() {
        selectedFile = fileChooser.showOpenDialog(stage);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(selectedFile));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            int res = Integer.valueOf(br.readLine());
            outputResult.setText(String.valueOf(res));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (outputResult.getText().equals(inputResult.getText()))
        {
            congratulations.setText("It's a match!");
        }
        else congratulations.setText("Something went wrong :(");
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        super.back(event);
    }
}
