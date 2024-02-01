package controllers;

import Exceptions.InvalidBotCountException;
import Exceptions.InvalidNumberOfPlayersException;
import models.Game;
import models.Player;
import models.GameState;
import strategies.WinningStrategy.WinniingStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimensions, List<Player> players, List<WinniingStrategy> winniingStrategies){
        try{
            return Game.getBuilder()
                    .setDimensions(dimensions)
                    .setPlayers(players)
                    .setWinniingStrategies(winniingStrategies)
                    .build();
        }
        catch (InvalidNumberOfPlayersException e){
            System.out.println(e.getMessage());
        }
        catch (InvalidBotCountException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void  makeMove(){

    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public void undo(){

    }

    public void displayBoard(Game game){
        game.getBoard().displayBoard();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }
}
