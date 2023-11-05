package pl.sudoku;

import org.junit.jupiter.api.Test;

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
}