package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.*;

public class PlayerManagerTest {
    private PlayerManager playerManager;
    private Player player1;
    private Player player2;
    private Hero hero1;
    private Hero hero2;

    @Before
    public void setUp() {
        playerManager = new PlayerManager();

        player1 = new Player("P001", "Player1");
        player2 = new Player("P002", "Player2");

        hero1 = new Warrior("H001", "Hero1");
        hero2 = new Warlock("H002", "Hero2");

        player1.addHero(hero1);
        player1.addHero(hero2);
    }

    @Test
    public void testAddPlayer() {
        playerManager.addPlayer(player1);
        assertEquals(1, playerManager.getPlayers().size());
        assertTrue(playerManager.getPlayers().contains(player1));
    }

    @Test
    public void testGetPlayerById() {
        playerManager.addPlayer(player1);
        playerManager.addPlayer(player2);

        Player foundPlayer = playerManager.getPlayerById("P001");
        assertNotNull(foundPlayer);
        assertEquals("P001", foundPlayer.getPlayerID());
        assertEquals("Player1", foundPlayer.getPlayerName());
    }

    @Test
    public void testGetPlayerByIdNotFound() {
        playerManager.addPlayer(player1);
        Player foundPlayer = playerManager.getPlayerById("NonExistentID");
        assertNull(foundPlayer);
    }

    @Test
    public void testSetCurrentPlayerById() {
        playerManager.addPlayer(player1);

        boolean result = playerManager.setCurrentPlayerById("P001");
        assertTrue(result);
        assertNotNull(playerManager.getCurrentPlayer());
        assertEquals("P001", playerManager.getCurrentPlayer().getPlayerID());
    }

    @Test
    public void testSetCurrentPlayerByIdNotFound() {
        playerManager.addPlayer(player1);

        boolean result = playerManager.setCurrentPlayerById("NonExistentID");
        assertFalse(result);
        assertNull(playerManager.getCurrentPlayer());
    }

    @Test
    public void testDeleteHeroSuccess() {
        playerManager.addPlayer(player1);
        playerManager.setCurrentPlayer(player1);

        assertEquals(2, player1.getHeroes().size());
        playerManager.deleteHero("H001");
        assertEquals(1, player1.getHeroes().size());

        boolean heroFound = false;
        for (Hero hero : playerManager.getCurrentPlayer().getHeroes()) {
            if (hero.getHeroID().equals("H001")) {
                heroFound = true;
                break;
            }
        }
        assertFalse(heroFound);
    }
    @Test
    public void testDeleteHeroNotFound() {
        playerManager.addPlayer(player1);
        playerManager.setCurrentPlayer(player1);

        assertEquals(2, player1.getHeroes().size());
        playerManager.deleteHero("NonExistentID");
        assertEquals(2, player1.getHeroes().size());
    }
    @Test
    public void testDeleteHeroWithNoCurrentPlayer() {
        playerManager.deleteHero("H001");
        assertNull(playerManager.getCurrentPlayer());
    }
}