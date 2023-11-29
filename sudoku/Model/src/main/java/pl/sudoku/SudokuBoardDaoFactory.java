package pl.sudoku;

public class SudokuBoardDaoFactory implements Repository<SudokuBoard> {

    private static SudokuBoard PROTOTYPE;

    SudokuBoardDaoFactory() {

    }

    SudokuBoardDaoFactory(SudokuBoard sudokuBoard) {
        PROTOTYPE = sudokuBoard;
    }

    Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    @Override
    public SudokuBoard createInstance() throws CloneNotSupportedException {
        return PROTOTYPE.clone();
    }
}
