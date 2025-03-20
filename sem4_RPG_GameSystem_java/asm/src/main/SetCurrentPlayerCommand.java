package main;

public class SetCurrentPlayerCommand implements Command {
    private PlayerManager playerManager;
    private String playerId;
    private Player previousPlayer; // to enable undo

    // Constructor
    public SetCurrentPlayerCommand(PlayerManager playerManager, String playerId) {
        this.playerManager = playerManager;
        this.playerId = playerId;
    }

    @Override
    public void execute() {
        boolean success = playerManager.setCurrentPlayerById(playerId);
        if (success) {
            System.out.println("Player with ID " + playerId + " is now the current player.");
        } else {
            System.out.println("Player with ID " + playerId + " not found.");
        }
    }

    @Override
    public void undo() {
        // Revert to the previous current player
        playerManager.setCurrentPlayer(previousPlayer);
        System.out.println("Undo: Reverted to previous current player: " +
                           (previousPlayer != null ? previousPlayer.getPlayerName() : "none"));
    }

    @Override
    public void redo() {
        // Redo setting the current player
        execute();
        previousPlayer = playerManager.getCurrentPlayer();
        System.out.println("Redo: Set current player to: " +
                           (previousPlayer != null ? previousPlayer.getPlayerName() : "none"));
    }
    @Override
    public String getDescription() {
        return "Command set current player";
    }
}
