package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    @Test
    void Getter_Setter_Test() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard1.setValue(0,0, 1);
        assertEquals(sudokuBoard1.getValue(0,0), 1);

        sudokuBoard1.setValue(0,1, 2);

        int suma = sudokuBoard1.getValue(0,0) + sudokuBoard1.getValue(0,1);
        assertEquals(suma,3);

    }

    @Test
    void Get_Board_Test() {
        SudokuField[][] tab = new SudokuField[9][9];

        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard1.solveGame();

        sudokuBoard1.getBoard(tab);
    }

    @Test
    public void checkBoardTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();

        assertTrue(sudokuBoard.checkBoard());

        int testValue = sudokuBoard.getValue(0,1);
        sudokuBoard.setValue(0, 0, testValue);
        assertFalse(sudokuBoard.checkBoard());
    }
}