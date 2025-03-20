package main;

public class CreatePlayerCommand implements Command {
    private PlayerManager playerManager;
    private String playerID;
    private String playerName;

    // Constructor to initialize PlayerManager, player ID, and player name
    public CreatePlayerCommand(PlayerManager playerManager, String playerID, String playerName) {
        this.playerManager = playerManager;
        this.playerID = playerID;
        this.playerName = playerName;
    }

    @Override
    public void execute() {
        // Create a new player and add it to the player manager
        Player newPlayer = new Player(playerID, playerName);
        playerManager.addPlayer(newPlayer);
        playerManager.setCurrentPlayer(newPlayer); // Set the newly created player as the current player
        System.out.println("Player " + playerName + " has been created.");
        System.out.println("Current Player " + playerID + " has been set as the current player.");
        
    }

    @Override
    public void undo() {
        // Undo the creation of the player by removing it from the player manager
        Player player = playerManager.getPlayerById(playerID);
        if (player != null) {
            playerManager.getPlayers().remove(player);
            System.out.println("Player " + playerID + " has been removed.");
        }
    }

    @Override
    public void redo() {
        execute(); // Re-create the player
    }

    @Override
    public String getDescription() {
        return "Command(Create Player: " + playerName+", "+playerID+")";
    }
}
