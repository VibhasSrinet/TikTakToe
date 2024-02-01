package models;

public class Player     {
    private String name;

    private Symbol symbol;

    private int id;

    public Player(String name, Symbol symbol, int id) {
        this.name = name;
        this.symbol = symbol;
        this.id = id;
        this.playerType = PlayerType.HUMAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    private PlayerType playerType;



}
