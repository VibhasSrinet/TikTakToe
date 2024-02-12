package controllers;

import Exceptions.InvalidBotCountException;
import Exceptions.InvalidNumberOfPlayersException;
import models.Game;
import models.Player;
import models.GameState;
import strategies.WinningStrategy.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimensions, List<Player> players, List<WinningStrategy> winningStrategies){
        try{
            return Game.getBuilder()
                    .setDimensions(dimensions)
                    .setPlayers(players)
                    .setWinningStrategies(winningStrategies)
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

    public void  makeMove(Game game){
        try{
            game.makeMove();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void undo(Game game){
        game.undo();
    }

    public void displayBoard(Game game){
        game.getBoard().displayBoard();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }
}
