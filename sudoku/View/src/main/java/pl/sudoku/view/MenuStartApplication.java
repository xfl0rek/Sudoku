package pl.sudoku.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class MenuStartApplication extends Application {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuStartApplication.class
                .getResource("menuStart-view.fxml"), resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();
    }
}
