package pl.sudoku;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);

        builder.append("value", value);

        return builder.toString();
    }
}
