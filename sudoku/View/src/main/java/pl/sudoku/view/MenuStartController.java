package pl.sudoku.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.sudoku.GameLevel;

import java.io.IOException;
import java.util.Objects;

public class MenuStartController {

    private Stage window;
    private Parent root;
    private static GameLevel level;

    @FXML
    Button easyButton = new Button("Easy");

    @FXML
    Button mediumButton = new Button("Medium");

    @FXML
    Button hardButton = new Button("Hard");

    public void easyGame() throws IOException {
        level = GameLevel.Easy;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("board-view.fxml")));
        window = (Stage) easyButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
