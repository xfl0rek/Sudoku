package pl.sudoku;

import pl.sudoku.exceptions.DaoException;
import pl.sudoku.exceptions.JdbcReadException;
import pl.sudoku.exceptions.JdbcWriteException;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/sudokuDB";
    private String username = "postgres";
    private String password = "123";
    private String boardName;
    private String boardTable = "board";
    private String fieldTable = "field";

    JdbcSudokuBoardDao(String boardName) throws DaoException {
        this.boardName = boardName;
        createConnection();
    }

    private void createConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
            throw new DaoException(resourceBundle.getString("connectionException"), exception);
        }
    }

    @Override
    public SudokuBoard read() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select f.value, f.row, f.col " +
                     "from " + fieldTable + " f join " + boardTable +
                     " b on f.id_board = b.id_board where b.name = '" + boardName + "'")) {

            BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
            SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);

            if (!resultSet.next()) {
                throw new RuntimeException();
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudokuBoard.setValue(resultSet.getInt(2), resultSet.getInt(3),
                            resultSet.getInt(1));

                    if (resultSet.getInt(1) == 0) {
                        sudokuBoard.getField(i, j).setEditable(true);
                    }

                    resultSet.next();
                }
            }
            connection.commit();
            return sudokuBoard;
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
                throw new JdbcReadException(resourceBundle.getString("DBReadException"), sqlException);
            } catch (SQLException exception) {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
                throw new JdbcReadException(resourceBundle.getString("RollbackError"), exception);
            }
        }
    }

    @Override
    public void write(SudokuBoard obj) {
        try (Statement statement1 = connection.createStatement();
             Statement statement2 = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement1.execute("create table " + boardTable +
                    " (id_board serial primary key not null, name varchar unique not null)");
            statement2.execute("create table " + fieldTable +
                    " (id_field serial primary key not null, " +
                    "value integer not null, row integer not null, col integer not null, " +
                    "id_board integer references " + boardTable + "(id_board))");
            connection.commit();
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
                throw new JdbcWriteException(resourceBundle.getString("RollbackError"), exception);
            }
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into " + boardTable +
                    "(name) values " + "('" + boardName + "')");
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
                throw new JdbcWriteException(resourceBundle.getString("DBWriteError"), sqlException);
            } catch (SQLException e) {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
                throw new JdbcWriteException(resourceBundle.getString("RollbackError"), e);
            }
        }

        try (Statement statement1 = connection.createStatement();
             Statement statement2 = connection.createStatement();
             ResultSet resultSet = statement1.executeQuery("select * from "
                       + boardTable + " where name like '" + boardName + "'")) {

            StringBuilder boardToWrite = new StringBuilder();
            String id = null;

            while (resultSet.next()) {
                id = String.valueOf(resultSet.getInt("id_board"));
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boardToWrite.append("(").append(i).append(",").append(j).append(",")
                            .append(obj.getValue(i, j)).append(",").append(id).append(")");
                    if (i != 8 || j != 8) {
                        boardToWrite.append(",\n");
                    }
                }
            }

            statement2.execute("insert into " + fieldTable +
                    "(row, col, value, id_board) values " + boardToWrite);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
                throw new JdbcWriteException(resourceBundle.getString("DBWriteError"), sqlException);
            } catch (SQLException exception) {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang");
                throw new JdbcWriteException(resourceBundle.getString("RollbackError"), exception);
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
