
package main;
public class RemoveHeroCommand implements Command {
    private Player player;
    private Hero hero;
    private boolean isExecuted;

    // Memento to store the state for undo/redo functionality
    private Hero removedHero;

    public RemoveHeroCommand(Player player, Hero hero) {
        this.player = player;
        this.hero = hero;
        this.isExecuted = false;
    }

    @Override
    public void execute() {
        if (!isExecuted) {
            // Remove the hero and save the state of the removed hero for undo
            removedHero = hero;
            player.getHeroes().remove(hero);
            isExecuted = true;
            System.out.println("Hero: "+ hero.getHeroID() + " "+ hero.getHeroName() + " removed successfully.");
        }
    }

    @Override
    public void undo() {
        if (isExecuted) {
            // Restore the removed hero back to the player's heroes list
            player.getHeroes().add(removedHero);
            isExecuted = false; // Reset execution status after undo
        }
    }

    @Override
    public void redo() {
        if (!isExecuted) {
            // Re-remove the hero (this is essentially repeating the execute logic)
            player.getHeroes().remove(removedHero);
            isExecuted = true; // Set to true after redoing
        }
    }
    @Override
    public String getDescription() {
        return "Command removing hero";
    }
}
