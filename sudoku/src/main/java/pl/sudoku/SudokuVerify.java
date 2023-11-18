package pl.sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public abstract class SudokuVerify {

    private final List<SudokuField> sudokuFields;

    public SudokuVerify(List<SudokuField> sudokuFields) {
        if (sudokuFields.size() != 9) {
            throw new IllegalArgumentException("The sudokuFields array must be a 9-element array.");
        }

        this.sudokuFields = sudokuFields;
    }

    public boolean verify() {
        for (int w = 0; w < sudokuFields.size(); w++) {
            for (int k = w + 1; k < sudokuFields.size(); k++) {
                if (sudokuFields.get(w).getFieldValue() == sudokuFields.get(k).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("sudokuFields", sudokuFields).toString();
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

        SudokuVerify rhs = (SudokuVerify) object;

        return new EqualsBuilder()
                .append(sudokuFields, rhs.sudokuFields)
                .isEquals();
    }
}
