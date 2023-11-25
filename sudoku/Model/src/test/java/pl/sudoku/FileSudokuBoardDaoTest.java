package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    public void readAndWriteTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard1.solveGame();
        SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard2;

        try (Dao<SudokuBoard> sudokuBoardDao = sudokuBoardDaoFactory.getFileDao("testFile1")) {
            sudokuBoardDao.write(sudokuBoard1);
            sudokuBoard2 = sudokuBoardDao.read();

            assertEquals(sudokuBoard1, sudokuBoard2);
            assertTrue(sudokuBoard2.isBoardValid());
            assertNotNull(sudokuBoard2);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void readExceptionTest() {
        SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();

        try (Dao<SudokuBoard> sudokuBoardDao = sudokuBoardDaoFactory.getFileDao("?")) {
            assertThrows(RuntimeException.class, () -> {
               sudokuBoardDao.read();
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void writeExceptionTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();

        SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();

        try (Dao<SudokuBoard> sudokuBoardDao = sudokuBoardDaoFactory.getFileDao("?")) {
            assertThrows(RuntimeException.class, () -> {
               sudokuBoardDao.write(sudokuBoard);
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}