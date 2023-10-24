package pl.sudoku;

public class SudokuBoard {
    private int[][] board = new int[9][9];

    private SudokuSolver sudokuSolver;

    //public SudokuBoard() {}

    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public boolean checkRow(int row, int value) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        return true;
    }

    public boolean checkColumn(int column, int value) {
        for (int i = 0; i < 9; i++) {
            if (board[i][column] == value) {
                return false;
            }
        }

        return true;
    }

    public boolean checkBox(int row, int column, int value) {
        int boxStartRow = row - row % 3;
        int boxStartColumn = column - column % 3;

        for (int i = boxStartRow; i < boxStartRow + 3; i++) {
            for (int j = boxStartColumn; j  < boxStartColumn + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

   public void getBoard(int[][] tab) {
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, tab[i], 0, 9);
        }
   }

   public int getValue(int x, int y) {
        return board[x][y];
   }

   public void setValue(int x, int y, int value) {
        board[x][y] = value;
   }



}