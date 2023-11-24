package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    public void SetterGetterTest(){
        SudokuField sudokuField = new SudokuField();
        sudokuField.setFieldValue(1);
        assertEquals(sudokuField.getFieldValue(), 1);

        sudokuField.setFieldValue(10);
        assertEquals(sudokuField.getFieldValue(), 1);

        sudokuField.setFieldValue(-1);
        assertEquals(sudokuField.getFieldValue(), 1);

    }

    @Test
    public void toStringTest() {
        SudokuField sudokuField = new SudokuField(9);

        assertNotNull(sudokuField.toString());
        assertNotEquals(sudokuField.toString().length(), 0);
    }

    @Test
    public void EqualsTest() {
        SudokuField [] sudokuFields = new SudokuField[9];
        for(int i = 0; i < 9; i++) {
            sudokuFields[i] = new SudokuField(i);
        }
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        assertFalse(sudokuFields[1].equals(sudokuFields[2]));

        sudokuFields[1].setFieldValue(0);

        assertTrue(sudokuFields[0].equals(sudokuFields[1]));

        assertFalse(sudokuFields[0].equals(null));

        assertFalse(sudokuFields[8].equals(backtrackingSudokuSolver));

        assertTrue(sudokuFields[1].equals(sudokuFields[1]));
    }

    @Test
    public void hashCodeTest() {
        SudokuField[] sudokuFields = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            sudokuFields[i] = new SudokuField(i);
        }

        assertEquals(sudokuFields[0].hashCode(), sudokuFields[0].hashCode());

        assertNotEquals(sudokuFields[0].hashCode(), sudokuFields[1].hashCode());
    }
}