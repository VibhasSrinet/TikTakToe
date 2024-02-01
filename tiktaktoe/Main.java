import controllers.GameController;
import models.Game;
import models.Player;
import models.Symbol;
import strategies.WinningStrategy.WinniingStrategy;

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
        List<WinniingStrategy> winniingStrategies= new ArrayList<>();
        Game game = gameController.startGame(dimensions, players, winniingStrategies);
        gameController.displayBoard(game);
    }
}