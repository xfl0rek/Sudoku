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

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(sudokuBoard1.getValue(i, j), tab[i][j].getFieldValue());
            }
        }
    }

    @Test
    public void getRowTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);

        for (int i = 0; i < 9; i++) {
            sudokuBoard.setValue(0, i, i + 1);
        }

        SudokuRow row = sudokuBoard.getRow(0);

        for (int i = 0; i < 9; i++) {
            assertEquals(i + 1, row.sudokuFields[i].getFieldValue());
        }
    }

    @Test
    public void  getColumnTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);

        for (int i = 0; i < 9; i++) {
            sudokuBoard.setValue(i, 0, i + 1);
        }

        SudokuColumn column = sudokuBoard.getColumn(0);

        for (int i = 0; i < 9; i++) {
            assertEquals(i + 1, column.sudokuFields[i].getFieldValue());
        }
    }

    @Test
    public void getBoxTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);

        int boxStartRow = 0;
        int boxStartColumn = 0;
        int value = 1;

        for (int i = boxStartRow; i < boxStartRow + 3; i++) {
            for (int j = boxStartColumn; j < boxStartColumn + 3; j++) {
                sudokuBoard.setValue(i, j, value);
                value++;
            }
        }

        SudokuBox box = sudokuBoard.getBox(boxStartRow, boxStartColumn);

        value = 1;
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(value, box.sudokuFields[index].getFieldValue());
                value++;
                index++;
            }
        }
    }
}