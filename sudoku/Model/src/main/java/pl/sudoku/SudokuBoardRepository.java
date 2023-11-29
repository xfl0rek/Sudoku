package pl.sudoku;

public class SudokuBoardRepository {
    private final SudokuBoard PROTOTYPE;

    SudokuBoardRepository(SudokuBoard sudokuBoard) {
        this.PROTOTYPE = sudokuBoard;
    }

    public SudokuBoard createInstance() throws CloneNotSupportedException {
        return PROTOTYPE.clone();
    }
}
