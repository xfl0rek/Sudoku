package pl.sudoku;

public interface Dao<T> {
    T read();

    void write(T obj);
}
