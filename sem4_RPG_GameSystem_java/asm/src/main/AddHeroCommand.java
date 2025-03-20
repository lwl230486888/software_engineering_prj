
package main;

public class AddHeroCommand implements Command {
    private Player player;
    private Hero hero;
    private boolean isExecuted;

    // Memento for undo/redo functionality
    private PlayerMemento undoMemento;
    private PlayerMemento redoMemento;

    public AddHeroCommand(Player player, Hero hero) {
        this.player = player;
        this.hero = hero;
        this.isExecuted = false;
    }

    @Override
    public void execute() {
        // Check if a valid player and hero exist
        if (player != null && hero != null) {
            // Save the current state before adding the hero for undo purposes
            undoMemento = player.saveStateToMemento();
            player.addHero(hero);
            isExecuted = true;
            System.out.println("Hero added successfully.");
        } else {
            System.out.println("No current player or invalid hero type.");
        }
    }

    @Override
    public void undo() {
        if (isExecuted) {
            // Save state for redo
            player.restoreStateFromMemento(undoMemento);
            player.removeHero(hero); // Remove hero from the player
            isExecuted = false;
        }
    }

    @Override
    public void redo() {
        if (!isExecuted) {
            // Restore the state and add the hero again
            player.restoreStateFromMemento(redoMemento);
            player.addHero(hero);
            isExecuted = true;
        }
    }

    @Override
    public String getDescription() {
        return "Add Hero: " + hero.getHeroName();
    }
}
