package pl.sudoku;

import pl.sudoku.exceptions.DaoException;

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
                throw new DaoException("message", exception);
            }
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into " + boardTable +
                    "(name) values " + "('" + boardName + "')");
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
                throw new DaoException("message", sqlException);
            } catch (SQLException exception) {
                throw new DaoException("message", exception);
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
                throw new DaoException("message", sqlException);
            } catch (SQLException exception) {
                throw new DaoException("message", exception);
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
