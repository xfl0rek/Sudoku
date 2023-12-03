package pl.sudoku.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.sudoku.BacktrackingSudokuSolver;
import pl.sudoku.SudokuBoard;

import java.io.IOException;
import java.util.Objects;

public class BoardController {
    private Stage window;

    private Parent root;

    BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

    private SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);

    @FXML
    Button exit = new Button("Exit");

    @FXML
    private GridPane sudokuBoardGrid;

    public void exit() throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menuStart-view.fxml")));
        window = (Stage) exit.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    private void fillBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();

                if (sudokuBoard.getValue(i, j) != 0) {
                    textField.setText(String.valueOf(sudokuBoard.getValue(i, j)));
                }
                textField.setAlignment(Pos.CENTER);
                sudokuBoardGrid.add(textField, i, j);
            }
        }
    }

    public void initialize() {
        sudokuBoard.solveGame();
        sudokuBoard.removeFields(MenuStartController.getLevel());
        fillBoard();
    }
}
