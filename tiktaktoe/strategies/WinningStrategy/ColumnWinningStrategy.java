package strategies.WinningStrategy;

import models.Board;
import models.Game;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> colMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if (!colMaps.containsKey(col)) {
            colMaps.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> colMap = colMaps.get(col);
        if (!colMap.containsKey(symbol)) {
            colMap.put(symbol, 0);
        }

        colMap.put(symbol, colMap.get(symbol) + 1);
        return colMap.get(symbol) == board.getSize();
    }

    @Override
    public void handleUndo(Move move, Board board) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> colMap = colMaps.get(col);
        colMap.put(symbol, colMap.get(symbol)-1);
    }
}
