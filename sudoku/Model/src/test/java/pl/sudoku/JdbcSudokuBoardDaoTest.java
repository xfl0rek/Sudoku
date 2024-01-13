package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {

    @Test
    void writeTest() {
        SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();

        try (Dao<SudokuBoard> file = sudokuBoardDaoFactory.getJdbcDao("testName")) {
            BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
            SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
            sudokuBoard.solveGame();

            assertDoesNotThrow(() -> {
                file.write(sudokuBoard);
            });

        } catch (Exception e) {

        }
    }
}