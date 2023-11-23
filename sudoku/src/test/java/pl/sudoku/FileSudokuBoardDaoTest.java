package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class FileSudokuBoardDaoTest {

    boolean readAndWriteTestHelper(SudokuBoard sudokuBoard1, SudokuBoard sudokuBoard2) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard1.getValue(i, j) != sudokuBoard2.getValue(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void readAndWriteTest() throws Exception {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard1.solveGame();
        SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard2;

        Dao<SudokuBoard> sudokuBoardDao = sudokuBoardDaoFactory.getFileDao("testFile");
        sudokuBoardDao.write(sudokuBoard1);

        sudokuBoard2 = sudokuBoardDao.read();

        assertNotNull(sudokuBoard2);

        assertTrue(readAndWriteTestHelper(sudokuBoard1, sudokuBoard2));

        SudokuBoard sudokuBoard3;

        Dao<SudokuBoard> sudokuBoardDao1 = sudokuBoardDaoFactory.getFileDao("newFile");

        sudokuBoard3 = sudokuBoardDao1.read();

        assertNull(sudokuBoard3);

    }
}