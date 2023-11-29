package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateInstanceTest {

    @Test
    void createInstance() throws CloneNotSupportedException {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();

        SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory(sudokuBoard);

        SudokuBoard sudokuBoard1 = sudokuBoardDaoFactory.createInstance();

        assertNotNull(sudokuBoard1);
        assertEquals(sudokuBoard, sudokuBoard1);
        assertNotSame(sudokuBoard, sudokuBoard1);
    }
}