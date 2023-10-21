package pl.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public void solve(SudokuBoard sudokuBoard) {
        int[] tab = findEmptyCell(sudokuBoard);
        int row;
        int column;

        if (tab[0] != -1) {
            row = tab[0];
            column = tab[1];
        } else {
            return;
        }

        List<Integer> valuesToTry = new ArrayList<>();
        for (int value = 1; value <= 9; value++) {
            valuesToTry.add(value);
        }

        Collections.shuffle(valuesToTry);

        for (int value : valuesToTry) {
            if (sudokuBoard.checkRow(row, value) && sudokuBoard.checkColumn(column, value)
                    && sudokuBoard.checkBox(row, column, value)) {
                sudokuBoard.setValue(row, column, value);
                solve(sudokuBoard);

                if (isBoardFilled(sudokuBoard)) {
                    return;
                }

                sudokuBoard.setValue(row, column, 0);
            }
        }
    }

    private boolean isBoardFilled(SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.getValue(i, j) == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[] findEmptyCell(SudokuBoard sudokuBoard) {
        int[] tab = {-1, -1};

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.getValue(i, j) == 0) {
                    tab[0] = i;
                    tab[1] = j;
                }
            }
        }

        return tab;
    }
}