package pl.sudoku.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BoardController {
    private Stage window;

    private Parent root;
    @FXML
    Button Exit = new Button("exit");

    public void exit() throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menuStart-view.fxml")));
        window = (Stage) Exit.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
