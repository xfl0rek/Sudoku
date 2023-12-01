package pl.sudoku.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.sudoku.GameLevel;

public class MenuStartController {

    private static GameLevel level;
    @FXML
    Button easyButton = new Button("Easy");

    @FXML
    Button mediumButton = new Button("Medium");

    @FXML
    Button hardButton = new Button("Hard");
}
