package main;

public class ChangePlayerNameCommand implements Command {
    private Player player;
    private String newName;
    private String oldName;  // To store the old name for undo

    // Memento for undo/redo functionality
    private String redoName;

    public ChangePlayerNameCommand(Player player, String newName) {
        this.player = player;
        this.newName = newName;
        this.oldName = player.getPlayerName(); // Store the current name before changing
    }

    @Override
    public void execute() {
        // Change the player name to the new name
        player.setPlayerName(newName);
    }

    @Override
    public void undo() {
        // Store the current name for redo
        redoName = player.getPlayerName();
        // Restore the old name
        player.setPlayerName(oldName);
    }

    @Override
    public void redo() {
        // Restore the player name to the new name (since undo reverted it)
        player.setPlayerName(redoName);
    }
    @Override
    public String getDescription() {
        return "Command (Change Player Name: " + player.getPlayerName()+")";
    }
}
