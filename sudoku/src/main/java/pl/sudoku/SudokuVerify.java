package pl.sudoku;

public abstract class SudokuVerify {

    protected SudokuField[] sudokuFields;

    public SudokuVerify(SudokuField[] sudokuFields) {
        this.sudokuFields = sudokuFields;
    }

    public boolean verify() {
        for (int w = 0; w < 9; w++) {
            for (int k = w + 1; k < 9; k++) {
                if (sudokuFields[w].getFieldValue() == sudokuFields[k].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
