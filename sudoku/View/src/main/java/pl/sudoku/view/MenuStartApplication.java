package pl.sudoku.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;


public class MenuStartApplication extends Application {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
    private static final Logger logger = Logger.getLogger(MenuStartApplication.class);



    @Override
    public void start(Stage stage) throws IOException {
        logger.info(resourceBundle.getString("startInfo"));
        FXMLLoader fxmlLoader = new FXMLLoader(MenuStartApplication.class
                .getResource("menuStart-view.fxml"), resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();
    }
}
