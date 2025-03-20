package main;

public class HeroMemento {
    private final int hp;
    private final int damage;
    private final int mp; // Only relevant for Warlock, but you may generalize if needed

    public HeroMemento(int hp, int damage, int mp) {
        this.hp = hp;
        this.damage = damage;
        this.mp = mp;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public int getMp() {
        return mp;
    }
}
