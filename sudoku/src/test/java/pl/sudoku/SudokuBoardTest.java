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
    @Test
    void Check_Row_Test(){
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard1.solveGame();



        for(int i = 0; i < 9; i++){
            assertFalse(sudokuBoard1.checkRow(i, 9));
            assertTrue(sudokuBoard1.checkRow(i,0));
        }




    }
    @Test
    void Check_Column_Test(){
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);

        sudokuBoard1.solveGame();

        for(int i = 0; i < 9; i++){

            assertTrue(sudokuBoard1.checkColumn(i, 0));
            assertFalse(sudokuBoard1.checkColumn(i, 9));

        }

    }



    @Test
    void Get_Board_Test(){
    int [][] tab = new int [9][9];

    BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

    SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);

    sudokuBoard1.solveGame();

    sudokuBoard1.getBoard(tab);

    for(int i = 0; i < 9; i++){
        for(int j = 0; j < 9; j++){
            assertEquals(sudokuBoard1.getValue(i,j),tab[i][j]);
        }
    }



}

@Test
    void Check_Box_Test(){
    BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

    SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);

    sudokuBoard1.solveGame();

    for(int w = 0; w < 9; w+=3){
        for(int k = 0; k < 9; k+=3){
            assertTrue(sudokuBoard1.checkBox(w, k, 0));
            assertFalse(sudokuBoard1.checkBox(w, k, 9));
        }
    }
}

}