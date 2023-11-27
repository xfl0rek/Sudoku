package pl.sudoku;

import java.util.List;

public class SudokuBox extends SudokuVerify {
    SudokuBox(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public SudokuBox clone() {
        return null;
    }
}
