package pl.sudoku;

import java.util.List;

public class SudokuColumn extends SudokuVerify {
    SudokuColumn(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public SudokuColumn clone() {
        return null;
    }
}
