package pl.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    SudokuBoard sudokuBoard;

    @BeforeEach
    void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    @Test
    void fillBoardTest() {
        sudokuBoard.fillBoard();
        int[][] testBoard = new int[9][9];
        sudokuBoard.getBoard(testBoard);

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {

                for (int i = row + 1; i < 9; i++) {
                    if (testBoard[row][column] == testBoard[i][column]) {
                        fail();
                    }
                }

                for (int i = column + 1; i < 9; i++) {
                    if (testBoard[row][column] == testBoard[row][i]) {
                        fail();
                    }
                }

                int boxStartRow = row - row % 3;
                int boxStartColumn = column - column % 3;

                for (int i = boxStartRow; i < boxStartRow + 3; i++) {
                    for (int j = boxStartColumn; j < boxStartColumn + 3; j++) {
                        if (testBoard[row][column] == testBoard[i][j] && (i != row && j != column)) {
                            fail();
                        }
                    }
                }
            }
        }
    }

    @Test
    void fillBoardComparisonTest() {
        sudokuBoard.fillBoard();

        int[][] testBoard1 = new int [9][9];

        sudokuBoard.getBoard(testBoard1);

        sudokuBoard = new SudokuBoard();

        sudokuBoard.fillBoard();
        int[][] testBoard2 = new int [9][9];

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