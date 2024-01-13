package pl.sudoku;

import pl.sudoku.exceptions.DaoException;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/sudokuDB";
    private String username = "postgres";
    private String password = "name";
    private String fileName;

    JdbcSudokuBoardDao(String fileName) throws DaoException {
        this.fileName = fileName;
        createConnection();
    }

    private void createConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
            throw new DaoException(resourceBundle.getString("connectionException"), exception);
        }
    }

    @Override
    public SudokuBoard read() {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {

    }

    @Override
    public void close() throws Exception {

    }
}
