package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardSetUpTest {

    @Test
    public void GameBoardSetUpTest() {
        GameBoardSetUp gameBoardSetUp = new GameBoardSetUp();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        GameLevel easyLevel = GameLevel.Easy;

        SudokuBoard resultBoard = gameBoardSetUp.gameBoardSetUp(sudokuBoard, easyLevel);

        int count = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (resultBoard.getValue(i, j) == 0) {
                    count++;
                }
            }
        }

        assertEquals(easyLevel.getValue(), count);
    }
}