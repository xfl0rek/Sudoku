package pl.sudoku;

public class SudokuField {
    private int value;

    public SudokuField(int value) {
        if (value >= 1 && value <= 9) {
            this.value = value;
        }
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        if (newValue >= 1 && newValue <= 9) {
            value = newValue;
        }
    }
}
