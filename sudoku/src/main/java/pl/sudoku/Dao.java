package pl.sudoku;

import java.io.IOException;

public interface Dao<T> {
    T read() throws IOException, ClassNotFoundException;

    void write(T obj);
}
