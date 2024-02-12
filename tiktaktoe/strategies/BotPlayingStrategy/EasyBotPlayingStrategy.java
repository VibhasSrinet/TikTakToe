package strategies.BotPlayingStrategy;

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move executeMove(Board board) {
        for(List<Cell> row: board.getBoard()){
            for(Cell cell: row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell, null);
                }
            }
        }
        return null;
    }
}
