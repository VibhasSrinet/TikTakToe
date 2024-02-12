import controllers.GameController;
import models.Game;
import models.GameState;
import models.Player;
import models.Symbol;
import strategies.WinningStrategy.ColumnWinningStrategy;
import strategies.WinningStrategy.DiagonalWinningStrategy;
import strategies.WinningStrategy.RowWinningStrategy;
import strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Board dimensions");
        int dimensions = scanner.nextInt();
        scanner.nextLine();
        List<Player> players= new ArrayList<>();
        for(int i=1;i<dimensions;i++){
            System.out.println("Please enter Player "+i+"'s name");
            String name= scanner.nextLine();
            System.out.println("Please enter Player "+i+"'s symbol");
            char symbol= scanner.next().charAt(0);
            scanner.nextLine();
            players.add(new Player(name, new Symbol(symbol), i));
        }
        List<WinningStrategy> winningStrategies= List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );
        Game game = gameController.startGame(dimensions, players, winningStrategies);
        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            gameController.displayBoard(game);
            System.out.println("Do you want to undo? Press Y for yes");
            String moveType =scanner.next();
            if(moveType.equalsIgnoreCase("Y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }
        gameController.displayBoard(game);
        System.out.println("Game Finished!");
        if(gameController.getGameState(game).equals(GameState.ENDED)){
            System.out.println("Winner is "+ gameController.getWinner(game).getName());
        }
        else{
            System.out.println("Game drawn!");
        }
    }
}