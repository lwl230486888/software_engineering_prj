package main;

public class PlayerMemento {
    private final String playerName;

    public PlayerMemento(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
