package pl.sudoku;

public class SudokuBoard {
    private SudokuField[][] board = new SudokuField[9][9];

    private SudokuSolver sudokuSolver;

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

   public void getBoard(int[][] tab) {
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
        SudokuField[] sudokuFields = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            sudokuFields[i] = new SudokuField();
            sudokuFields[i].setFieldValue(board[y][i].getFieldValue());
        }
        return new SudokuRow(sudokuFields);
   }
}