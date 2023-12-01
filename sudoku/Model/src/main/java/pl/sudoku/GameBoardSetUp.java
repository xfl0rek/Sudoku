package pl.sudoku;

import java.util.Random;

public class GameBoardSetUp {
    public SudokuBoard gameBoardSetUp(SudokuBoard sudokuBoard, GameLevel level) {
        Random random = new Random();
        int fieldsToRemove = level.getValue();

        while (fieldsToRemove > 0) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);

            if (sudokuBoard.getValue(x, y) != 0) {
                sudokuBoard.setValue(x, y, 0);
                fieldsToRemove--;
            }
        }

        return sudokuBoard;
    }
}
