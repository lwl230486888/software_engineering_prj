package main;

public class ShowPlayerDetailsCommand implements Command {
    private Player player;

    public ShowPlayerDetailsCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        // Display the player's details
        showPlayerDetails();
    }

    @Override
    public void undo() {
        // Since displaying details doesn't modify state, this could be empty.
        // Alternatively, clear the displayed details in the UI if needed.
    }

    @Override
    public void redo() {
        // Re-display the player's details (similar to execute)
        showPlayerDetails();
    }

    private void showPlayerDetails() {
        // Display details of the player (for example, print to the console or UI)
        System.out.println("Player: " + player.getPlayerName() + " (" + player.getPlayerID() + ")");
        System.out.println();
        // If you want to show heroes, you could iterate over the heroes list.
        for (Hero hero : player.getHeroes()) {
            hero.showHeroStatus(); // Assuming Hero class has showHeroStatus() method
        }
    }

    @Override
    public String getDescription() {
        return "Command (display player detail)";
    }
}
