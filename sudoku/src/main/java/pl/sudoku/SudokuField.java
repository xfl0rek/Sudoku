package pl.sudoku;

public class SudokuField {
    private int value;

    SudokuField(int value) {
        this.value = value;
    }

    SudokuField() {

    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        if (newValue >= 0 && newValue <= 9) {
            value = newValue;
        }
    }
}
