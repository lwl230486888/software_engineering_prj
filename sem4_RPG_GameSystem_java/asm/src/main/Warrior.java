package main;

public class Warrior extends Hero {
    private int defencePoint;
    private int damage;

    public Warrior(String heroID, String heroName) {
        super(heroID, heroName);
        this.defencePoint = 500;  // Initial defense points
        this.setHp(500);          // Set initial health points (assumed from superclass)
        this.damage = 0;          // Initial damage
    }

    public int getDefencePoint() {
        return defencePoint;
    }

    public void increaseDamage(int amount) {
        this.damage += amount;  // Increase damage by the given amount
    }

    public void decreaseDefence(int value) {
        this.defencePoint -= value;
    }
    
    public void setDefencePoint(int defencePoint) {
        this.defencePoint = defencePoint;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Call the warrior's skill: increases damage and decreases defence
    @Override
    public void callSkill() {
        // Ensure defence point doesn't go negative
        if (defencePoint < 0) {
            defencePoint = 0;
        }

        // Increase damage by 250 and decrease defense point by 100
        this.setDamage(this.getDamage() + 250);  
        this.defencePoint -= 100; 

        // Ensure defence point doesn't go below 0
        if (defencePoint < 0) {
            defencePoint = 0;
        }

        // Display the updated status
        System.out.println(getHeroName() + " called skill. Damage increased, defense decreased.");
    }

    @Override
    public void showHeroStatus() {
        System.out.println(getHeroID() + ", " + getHeroName() + ", Hp: " + getHp() +
                ", Damage: " + getDamage() + ", Defence Point: " + getDefencePoint());

        System.out.println("");
    }
}
