package pl.sudoku;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String fileName;

    FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        SudokuBoard sudokuBoard;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) {

    }
}
