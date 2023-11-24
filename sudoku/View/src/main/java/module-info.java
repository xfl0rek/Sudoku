module pl.sudoku.view {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.sudoku.view to javafx.fxml;
    exports pl.sudoku.view;
}