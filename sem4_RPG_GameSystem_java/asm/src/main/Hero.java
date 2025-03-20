
package main;
public abstract class Hero {
    private String heroID;
    private String heroName;
    private int hp;
    private int damage;
    private int mp; // Add MP if necessary for specific hero types

    public Hero(String heroID, String heroName) {
        this.heroID = heroID;
        this.heroName = heroName;
        this.hp = 200;
    }

    public String getHeroID() {
        return heroID;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public abstract void callSkill();

    public abstract void showHeroStatus();

    // Create memento to save current state
    public HeroMemento saveStateToMemento() {
        return new HeroMemento(hp, damage, mp);
    }

    // Restore state from memento
    public void restoreStateFromMemento(HeroMemento memento) {
        this.hp = memento.getHp();
        this.damage = memento.getDamage();
        this.mp = memento.getMp();
    }
}
