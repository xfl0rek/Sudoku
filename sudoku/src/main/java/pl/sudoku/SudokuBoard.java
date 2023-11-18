package pl.sudoku;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
    private final SudokuField[][] board = new SudokuField[9][9];

    private final SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

   public void getBoard(SudokuField[][] tab) {
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, tab[i], 0, 9);
        }
   }

   public int getValue(int x, int y) {
        return board[x][y].getFieldValue();
   }

   public void setValue(int x, int y, int value) {
        board[x][y].setFieldValue(value);
   }

   public SudokuRow getRow(int y) {
       //SudokuField[] sudokuFields = new SudokuField[9];
       List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            sudokuFields.set(i, board[y][i]);

        }
        return new SudokuRow(sudokuFields);
   }

   public SudokuColumn getColumn(int x) {
       List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            sudokuFields.set(i, board[i][x]);
        }
        return new SudokuColumn(sudokuFields);
   }

   public SudokuBox getBox(int x, int y) {
       List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[9]);

        int boxStartRow = x - x % 3;
        int boxStartColumn = y - y % 3;
        int index = 0;

        for (int w = boxStartRow; w < boxStartRow + 3; w++) {
            for (int k = boxStartColumn; k < boxStartColumn + 3; k++) {
                //sudokuFields[index] = new SudokuField(board[w][k].getFieldValue());
                sudokuFields.set(index, board[w][k]);
                index++;
            }
        }
        return new SudokuBox(sudokuFields);
   }

   private boolean checkBoard() {
       for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {
               if (!getRow(i).verify()) {
                   return false;
               }

               if (!getColumn(i).verify()) {
                   return false;
               }

               if (!getBox(i, j).verify()) {
                   return false;
               }
           }
       }

       return true;
   }

   public boolean isBoardValid() {
        return checkBoard();
   }

   @Override
   public String toString() {
       ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);

       for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {
               builder.append(board[i][j].getFieldValue());
           }
       }

       return builder.toString();
   }
}