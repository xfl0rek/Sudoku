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
}