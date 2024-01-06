package pl.sudoku.view;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import org.apache.log4j.Logger;
import pl.sudoku.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class BoardController {
    private static final Logger logger = Logger.getLogger(MenuStartApplication.class);

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
    private Stage window;

    private Parent root;

    BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

    private SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);

    private final SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();

    private final List<IntegerProperty> integerPropertyList = new ArrayList<>();

    private final StringConverter<Number> converter = new NumberStringConverter();

    @FXML
    Button exit = new Button("Exit");

    @FXML
    private GridPane sudokuBoardGrid;

    @FXML
    Button saveGame = new Button("Save game");

    @FXML
    Button loadGame = new Button("Load game");

    public void exit() throws IOException {
        logger.info(resourceBundle.getString("exitInfo"));
        root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("menuStart-view.fxml")), resourceBundle);
        window = (Stage) exit.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    private void fillBoard() throws NoSuchMethodException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();

                TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("[1-9]")) {
                        return change;
                    } else if (change.isDeleted()) {
                        return change;
                    } else {
                        return null;
                    }
                });

                textField.setTextFormatter(textFormatter);

                SudokuField sudokuField = sudokuBoard.getField(i, j);

                if (!sudokuField.isEditable()) {
                    textField.setDisable(true);
                }

                IntegerProperty integerProperty = JavaBeanIntegerPropertyBuilder
                        .create()
                        .bean(sudokuField)
                        .name("fieldValue")
                        .build();

                this.integerPropertyList.add(integerProperty);

                textField.textProperty().bindBidirectional(integerProperty, converter);

                integerProperty.addListener(observable -> {
                    Platform.runLater(() -> {
                        if (isBoardSolved()) {
                            if (sudokuBoard.isBoardValid()) {
                                System.out.println("solved");
                            } else {
                                System.out.println("not solved");
                            }
                        }
                    });
                });

                textField.setMaxWidth(180);
                textField.setMaxHeight(180);
                textField.setAlignment(Pos.CENTER);
                sudokuBoardGrid.add(textField, i, j);
            }
        }
    }

    private boolean isBoardSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.getValue(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void saveGame() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser
                .ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        String fileName = file.getAbsolutePath();
        logger.info(resourceBundle.getString("saveInfo"));

        try (Dao<SudokuBoard> sudokuBoardDao = sudokuBoardDaoFactory.getFileDao(fileName)) {
            sudokuBoardDao.write(sudokuBoard);
            System.out.println(sudokuBoard);
        } catch (Exception exception) {
            logger.info(resourceBundle.getString("savingError"));
            throw new RuntimeException();
        }
    }

    public void loadGame() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser
                .ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        String fileName = file.getAbsolutePath();
        logger.info(resourceBundle.getString("loadInfo"));

        try (Dao<SudokuBoard> sudokuBoardDao = sudokuBoardDaoFactory.getFileDao(fileName)) {
            sudokuBoard = sudokuBoardDao.read();
            sudokuBoardGrid.getChildren().clear();
            fillBoard();
        } catch (Exception exception) {
            logger.info(resourceBundle.getString("loadingError"));
            throw new RuntimeException();
        }
    }

    public void initialize() throws NoSuchMethodException {
        sudokuBoard.solveGame();
        sudokuBoard.removeFields(MenuStartController.getLevel());
        fillBoard();
    }
}
