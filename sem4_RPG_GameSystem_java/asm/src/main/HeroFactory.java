package main;

public class HeroFactory {
    public static Hero createHero(String heroType, String heroID, String heroName) {
        switch (heroType) {
            case "Warrior":
                return new Warrior(heroID, heroName);
            case "Warlock":
                return new Warlock(heroID, heroName);
            // Add other hero types here
            default:
                throw new IllegalArgumentException("Unknown hero type");
        }
    }
}
