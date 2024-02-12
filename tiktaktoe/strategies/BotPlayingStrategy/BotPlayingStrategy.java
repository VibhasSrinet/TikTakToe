package strategies.BotPlayingStrategy;

import models.Board;
import models.Move;

public interface BotPlayingStrategy {
    public Move executeMove(Board board);
}
