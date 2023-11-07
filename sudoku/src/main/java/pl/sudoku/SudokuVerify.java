package pl.sudoku;

public abstract class SudokuVerify {

    private SudokuField[] sudokuFields;

    public SudokuVerify(SudokuField[] sudokuFields) {
        if (sudokuFields.length != 9) {
            throw new IllegalArgumentException("The sudokuFields array must be a 9-element array.");
        }

        this.sudokuFields = sudokuFields;
    }

    public boolean verify() {
        for (int w = 0; w < sudokuFields.length; w++) {
            for (int k = w + 1; k < sudokuFields.length; k++) {
                if (sudokuFields[w].getFieldValue() == sudokuFields[k].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
