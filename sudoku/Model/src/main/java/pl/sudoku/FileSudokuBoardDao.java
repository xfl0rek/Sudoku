package pl.sudoku;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private final String fileName;

    FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            return sudokuBoard;
        } catch (IOException | ClassNotFoundException exception) {
            throw new RuntimeException("A problem was encountered while trying to read the file.",
                    exception);
        }
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
        } catch (IOException ioe) {
            throw new RuntimeException("A problem was encountered writing to the file. ", ioe);
        }
    }

    @Override
    public void close() throws Exception {

    }
}
