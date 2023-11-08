package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuVerifyTest {
    @Test
    public void VerifyTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();

        for (int i = 0; i < 9; i++) {
            assertTrue(sudokuBoard.getRow(i).verify());
        }

        for (int i = 0; i < 9; i++) {
            assertTrue(sudokuBoard.getColumn(i).verify());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(sudokuBoard.getBox(i, j).verify());
            }
        }

        sudokuBoard.setValue(0,0,1);
        sudokuBoard.setValue(0,1,1);

        assertFalse(sudokuBoard.getRow(0).verify());

        SudokuField [] sudokuFields = new SudokuField[5];
        for(int i = 0; i < 5; i++) {
            sudokuFields[i] = new SudokuField(i);
        }
        Exception exception = assertThrows(Exception.class, () -> {
            new SudokuRow(sudokuFields);
        });



       assertEquals( exception.getMessage(), "The sudokuFields array must be a 9-element array.");


    }
}