package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void solveComparisonTest() {
        int[][] testBoard1 = new int[9][9];
        int[][] testBoard2 = new int[9][9];

        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();

        sudokuBoard.getBoard(testBoard1);

        sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();

        sudokuBoard.getBoard(testBoard2);

        boolean areEqual = true;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (testBoard1[i][j] != testBoard2[i][j]) {
                    areEqual = false;
                    break;
                }
            }
        }

        assertFalse(areEqual);
    }
}