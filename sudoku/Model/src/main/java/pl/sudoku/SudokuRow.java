package pl.sudoku;

import java.util.List;

public class SudokuRow extends SudokuVerify {
    SudokuRow(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public SudokuRow clone() {
        return null;
    }
}
