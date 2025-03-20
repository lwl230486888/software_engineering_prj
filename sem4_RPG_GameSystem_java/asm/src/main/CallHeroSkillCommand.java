package main;

public class CallHeroSkillCommand implements Command {
    private Hero hero;

    public CallHeroSkillCommand(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void execute() {
        if (hero instanceof Warrior) {
            Warrior warrior = (Warrior) hero;
            warrior.increaseDamage(250);  // Increase damage by 250
            warrior.decreaseDefence(100); // Decrease defence by 100
            
            System.out.println("Warrior " + warrior.getHeroName() + " called skill! Damage increased by 250 and Defence decreased by 100.");
            System.out.println("Updated status: Hp: " + warrior.getHp() + ", Damage: " + warrior.getDamage() + ", Defence Point: " + warrior.getDefencePoint());
        }
        // Handle other hero types here if needed
    }

    @Override
    public void undo() {
        // Implement undo if necessary
    }

    @Override
    public void redo() {
        // Implement redo if necessary
    }
    public String getDescription() {
        return "Call hero skill, " + hero.getHeroID() + ", " + hero.getHeroName() + ", " + 
               hero.getClass().getSimpleName() + ", Hp: " + hero.getHp() + 
               ", Damage: " + hero.getDamage() + ", Defence Point: " + 
               ((hero instanceof Warrior) ? ((Warrior) hero).getDefencePoint() : "N/A");
    }
}
