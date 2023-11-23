package pl.sudoku;

import java.io.IOException;

public interface Dao<T> extends AutoCloseable {
    T read();

    void write(T obj);
}
