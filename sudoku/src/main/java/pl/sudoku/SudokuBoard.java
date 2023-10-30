package pl.sudoku;

public class SudokuBoard {
    private int[][] board = new int[9][9];

    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
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