package pl.sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuRow extends SudokuVerify {
    SudokuRow(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        SudokuField [] temp_sudokuFields = new SudokuField[9];
        for(int i = 0; i < 9; i++) {
            temp_sudokuFields[i] = new SudokuField(sudokuFields.get(i).getFieldValue());
        }
        return new SudokuRow(Arrays.asList(temp_sudokuFields));

    }
}
