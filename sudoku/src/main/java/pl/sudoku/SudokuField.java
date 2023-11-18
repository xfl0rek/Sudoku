package pl.sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);

        builder.append(value);

        return builder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object.getClass() != getClass()) {
            return false;
        }
        SudokuField rhs = (SudokuField) object;
        return new EqualsBuilder().append(value, rhs.value).isEquals();
    }
}


