package pl.sudoku;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String fileName;

    FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            return sudokuBoard;
        } catch (IOException | ClassNotFoundException classNotFoundException) {
            return null;
        }
    }

    @Override
    public void write(SudokuBoard obj) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

    }
}
