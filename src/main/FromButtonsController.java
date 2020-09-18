package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FromButtonsController extends Model{
    @FXML
    AnchorPane ap;
    @FXML
    Label incorrect,actual, inputResult, outputResult, expected;

    Stage stage;
    @FXML
    private void loadInput(ActionEvent event) throws FileNotFoundException {
        incorrect.setText(" ");
        actual.setText("ACTUAL");
        expected.setText("EXPECTED");
        stage = (Stage) ap.getScene().getWindow();
        String fileName = "src/main/resources/inputData/" + ((Control) event.getSource()).getId() + ".in";
        File file = new File(fileName);
        if (file != null) {
            String x = String.valueOf(calculate(file));
            inputResult.setText(x);
            loadOutput(event);
        }
    }

    @FXML
    private void loadOutput(ActionEvent event) {
        stage = (Stage) ap.getScene().getWindow();
        String fileName = "src/main/resources/outData/" + ((Control) event.getSource()).getId() + ".out";
        File file = new File(fileName);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            int res = Integer.valueOf(br.readLine());
            outputResult.setText(String.valueOf(res));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        super.back(event);
    }
    int incorrectAnswers = 0;
    public void loadAll() throws IOException {
        inputResult.setText(" ");
        outputResult.setText(" ");
        incorrect.setText(" ");
        actual.setText("");
        expected.setText("");
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/inputData/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(p -> {
                        File fIn = new File(String.valueOf(p));
                        int inAnswer = 0, outAsnwer=0;
                        try {
                            inAnswer = calculate(fIn);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        String foutName = fIn.getName().replaceFirst("[.][^.]+$", "");
                        File fOut = new File("src/main/resources/outData/" + foutName+ ".out");
                        BufferedReader br = null;
                        try {
                            br = new BufferedReader(new FileReader(fOut));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            outAsnwer = Integer.valueOf(br.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (inAnswer!=outAsnwer) incorrectAnswers++;
                    });
            incorrect.setText("Incorrect solutions : "+incorrectAnswers);
        }
    }
}
