package pl.sudoku;

import java.io.IOException;

public interface Dao<T> {
    T read();

    void write(T obj);
}
