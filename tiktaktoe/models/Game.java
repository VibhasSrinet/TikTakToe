package models;

import Exceptions.InvalidBotCountException;
import Exceptions.InvalidNumberOfPlayersException;
import strategies.WinningStrategy.WinniingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextMovePlayersIndex;
    private List<WinniingStrategy> winniingStrategies;

    private Game(Builder builder){
        this.moves= new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextMovePlayersIndex=0;
        this.winner= null;
        this.winniingStrategies = builder.winniingStrategies;
        this.players = builder.players;
        board = new Board(builder.dimensions);
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextMovePlayersIndex() {
        return nextMovePlayersIndex;
    }

    public void setNextMovePlayersIndex(int nextMovePlayersIndex) {
        this.nextMovePlayersIndex = nextMovePlayersIndex;
    }

    public List<WinniingStrategy> getWinniingStrategies() {
        return winniingStrategies;
    }

    public void setWinniingStrategies(List<WinniingStrategy> winniingStrategies) {
        this.winniingStrategies = winniingStrategies;
    }

    public static class Builder {
        private List<Player> players;
        private int dimensions;
        private List<WinniingStrategy> winniingStrategies;
        private Builder(){
            this.players= new ArrayList<>();
            this.winniingStrategies= new ArrayList<>();
            this.dimensions =0;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setWinniingStrategies(List<WinniingStrategy> winniingStrategies) {
            this.winniingStrategies = winniingStrategies;
            return this;
        }

        private boolean validateBotCount(){
            int botCount = 0;
            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                    if(botCount>1){
                        return false;
                    }
                }
            }
            return true;
        }

        private void validate() throws InvalidNumberOfPlayersException, InvalidBotCountException {
            if(players.size()!= dimensions-1){
                throw new InvalidNumberOfPlayersException("Number of players should be one less than he dimensions");
            }

            if(!validateBotCount()){
                throw new InvalidBotCountException("Bot count should be less than or equall to 1");
            }
        }

        public Game build() throws InvalidBotCountException, InvalidNumberOfPlayersException{
            validate();
            return new Game(this);
        }

    }
}
