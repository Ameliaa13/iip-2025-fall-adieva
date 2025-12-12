package impl;

public abstract class AbstractPlayer {

    protected GameService gameService;

    protected Symbol symbol;

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    abstract public void doTurn();
}
