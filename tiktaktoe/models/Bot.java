package models;

import strategies.BotPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Symbol symbol, int id, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, id);
        this.botDifficultyLevel = botDifficultyLevel;
        this.setPlayerType(PlayerType.BOT);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
