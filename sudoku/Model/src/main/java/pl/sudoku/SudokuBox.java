package pl.sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuBox extends SudokuVerify {
    SudokuBox(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public SudokuBox clone() throws CloneNotSupportedException{
            SudokuField [] temp_sudokuFields = new SudokuField[9];
            for(int i = 0; i < 9; i++) {
                temp_sudokuFields[i] = new SudokuField(sudokuFields.get(i).getFieldValue());
            }
            return new SudokuBox(Arrays.asList(temp_sudokuFields));
    };


}
