package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {




    @Test
    void Getter_Setter_Test() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard1.setValue(0,0, 1);
        assertEquals(sudokuBoard1.getValue(0,0), 1);

        sudokuBoard1.setValue(0,1, 2);

        int suma = sudokuBoard1.getValue(0,0) + sudokuBoard1.getValue(0,1);
        assertEquals(suma,3);

    }

    void Check_Row_Test(){
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard1.solveGame();

        for(int i = 0; i < 9; i++){
             int wynik = sudokuBoard1.getValue(i,0);
             assertFalse(sudokuBoard1.checkRow(i, wynik));

        }
    }

    void Check_Column_Test(){
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard1.solveGame();

        for(int i = 0; i < 9; i++){
            int wynik = sudokuBoard1.getValue(0,i);
            assertFalse(sudokuBoard1.checkColumn(wynik, i));

        }

    }



}