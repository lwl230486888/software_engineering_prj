package main;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private List<Player> players; // A list to store all players
    private Player currentPlayer;

    public PlayerManager() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player); // Add the new player to the list
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayerById(String playerId) {
        for (Player player : players) {
            if (player.getPlayerID().equals(playerId)) {
                return player;
            }
        }
        return null;
    }

    // Set current player by Player ID
    public boolean setCurrentPlayerById(String playerId) {
        Player player = getPlayerById(playerId);
        if (player != null) {
            setCurrentPlayer(player);
            return true;  // Successfully set the player
        }
        return false;  // Player ID not found
    }
    
    public void deleteHero(String heroID) {
        if (currentPlayer == null || currentPlayer.getHeroes().isEmpty()) {
            System.out.println("No heroes available to delete.");
            return;
        }

        Hero heroToRemove = null;
        for (Hero hero : currentPlayer.getHeroes()) {
            if (hero.getHeroID().equals(heroID)) {
                heroToRemove = hero;
                break;
            }
        }

        if (heroToRemove != null) {
            currentPlayer.getHeroes().remove(heroToRemove);
            System.out.println("Hero " + heroToRemove.getHeroName() + " with ID " + heroID + " has been removed.");
        } else {
            System.out.println("No hero found with ID " + heroID);
        }
    }
}
