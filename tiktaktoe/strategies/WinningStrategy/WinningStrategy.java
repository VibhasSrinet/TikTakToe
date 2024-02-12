package strategies.WinningStrategy;

import models.Board;
import models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    void handleUndo(Move move, Board board);
}
