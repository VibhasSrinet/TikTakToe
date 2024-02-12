package models;

import Exceptions.InvalidBotCountException;
import Exceptions.InvalidMoveException;
import Exceptions.InvalidNumberOfPlayersException;
import strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextMovePlayersIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(Builder builder){
        this.moves= new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextMovePlayersIndex=0;
        this.winner= null;
        this.winningStrategies = builder.winningStrategies;
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

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col= move.getCell().getCol();

        if(row<0 || row >= board.getSize() || col<0 || col >= board.getSize()){
            return false;
        }
        Cell currentMoveCell = board.getBoard().get(row).get(col);
        return currentMoveCell.getCellState().equals(CellState.EMPTY);
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextMovePlayersIndex);
        System.out.println("It is "+currentPlayer.getName()+"'s Turn!");
        Move currentMove = currentPlayer.executeMove(board);
        if(!validateMove(currentMove)){
            throw new InvalidMoveException("The move made is invalid!");
        }
        nextMovePlayersIndex = (nextMovePlayersIndex+1)%players.size();
        int row= currentMove.getCell().getRow();
        int col= currentMove.getCell().getCol();
        Cell currentMoveCell = board.getBoard().get(row).get(col);
        moves.add(new Move(currentMoveCell, currentPlayer));
        currentMoveCell.setCellState(CellState.FILLED);
        currentMoveCell.setPlayer(currentPlayer);
        if(checkWinner(board, currentMove)){
            winner = currentPlayer;
            setGameState(GameState.ENDED);
        }
        if(moves.size() == board.getSize()*board.getSize()){
            setGameState(GameState.DRAW);
        }

    }

    public void undo(){
        if(moves.size()==0){
            System.out.println("Can not undo as no moves present to undo!");
            return;
        }
        Move lastMove = moves.get(moves.size()-1);
        moves.remove(lastMove);
        Cell lastMoveCell = lastMove.getCell();
        lastMoveCell.setCellState(CellState.EMPTY);
        lastMoveCell.setPlayer(null);
        nextMovePlayersIndex = (nextMovePlayersIndex-1+players.size())%players.size();
        for(WinningStrategy strategy: winningStrategies){
            strategy.handleUndo(lastMove, board);
        }
    }

    boolean checkWinner(Board board, Move move){
        for(WinningStrategy strategy: winningStrategies){
            if(strategy.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }

    public static class Builder {
        private List<Player> players;
        private int dimensions;
        private List<WinningStrategy> winningStrategies;
        private Builder(){
            this.players= new ArrayList<>();
            this.winningStrategies= new ArrayList<>();
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

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
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
