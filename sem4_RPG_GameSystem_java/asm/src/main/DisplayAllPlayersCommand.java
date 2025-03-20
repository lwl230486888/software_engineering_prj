package main;

public class DisplayAllPlayersCommand implements Command {
    private PlayerManager pm;

    // Constructor that takes GameSystem as a parameter
    public DisplayAllPlayersCommand(PlayerManager pm) {
        this.pm = pm;
    }

    @Override
    public void execute() {
        System.out.println("Displaying all players:");
        for (Player player : pm.getPlayers()) {
            System.out.println(player.getPlayerName() + " (" + player.getPlayerID() + ")");
        }
    }

    @Override
    public void undo() {
        // Undo operation for displaying players might not be necessary, but can be logged
        System.out.println("Undo operation for display is not applicable.");
    }

    @Override
    public void redo() {
        // Re-run the display operation
        execute();
    }
    @Override
    public String getDescription() {
        return "Command (display)";
    }
}
