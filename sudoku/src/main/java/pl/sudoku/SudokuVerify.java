package pl.sudoku;

import java.util.Arrays;
import java.util.List;

public abstract class SudokuVerify {

    private List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[9]);

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
}
