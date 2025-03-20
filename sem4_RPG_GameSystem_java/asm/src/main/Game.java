package main;

import java.util.Scanner;

public class Game {
    private CommandExecutor commandExecutor;
    private Scanner scanner;
    private PlayerManager playerManager = new PlayerManager();

    public Game() {
        commandExecutor = new CommandExecutor();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("c| Create Player");
            System.out.println("g| Set Current Player by Player ID");
            System.out.println("a| Add Hero");
            System.out.println("d| Delete Hero");
            System.out.println("t| Change Player Name");
            System.out.println("m| Call Hero Skill");
            System.out.println("s| Show Player Details");
            System.out.println("u| Undo");
            System.out.println("r| Redo");
            System.out.println("p| Show All Players");
            System.out.println("l| Show Undo/Redo List");
            System.out.println("x| Exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "c":
                    createPlayer();
                    break;
                case "g":
                    setCurrentPlayerById();
                    break;
                case "a":
                    addHero();
                    break;
                case "d":
                    deleteHero();
                    break;
                case "t":
                    changePlayerName();
                    break;
                case "m":
                    callHeroSkill();
                    break;
                case "s":
                    showPlayerDetails();
                    break;
                case "u":
                    undo();
                    break;
                case "r":
                    redo();
                    break;

                case "p":
                    showAllPlayers();
                    break;

                case "l":
                    commandExecutor.showUndoRedoList();
                    break;
                case "x":
                    System.out.println("Exiting the game...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createPlayer() {
        System.out.print("Enter player ID: ");
        String playerID = scanner.nextLine();
        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine();

        // Use the factory to create the command
        ConcreteCommandFactory commandFactory = new ConcreteCommandFactory();
        Command createPlayerCommand = commandFactory.create("CreatePlayer", playerManager, playerID, playerName);

        // Execute the command to create the player
        commandExecutor.executeCommand(createPlayerCommand);

        System.out.println("Player created successfully.");
    }

    private void setCurrentPlayerById() {
        System.out.print("Enter Player ID to set as current: ");
        String playerId = scanner.nextLine();
    
        // Use the factory to create the command
        ConcreteCommandFactory commandFactory = new ConcreteCommandFactory();
        Command setCurrentPlayerCommand = commandFactory.create("SetCurrentPlayer", playerManager, playerId);
    
        // Execute the command
        commandExecutor.executeCommand(setCurrentPlayerCommand);
    }
    

    private void addHero() {
        if (playerManager.getCurrentPlayer() == null) {
            System.out.println("No current player selected.");
            return;
        }
    
        System.out.print("Enter hero details (format: heroID, heroName): ");
        String heroDetails = scanner.nextLine();
        
        // Split the input by comma and trim spaces
        String[] heroInfo = heroDetails.split(",");
        if (heroInfo.length != 2) {
            System.out.println("Invalid input format. Please use the format: heroID, heroName");
            return;
        }
        
        String heroID = heroInfo[0].trim();
        String heroName = heroInfo[1].trim();
    
        System.out.println("Choose hero type:");
        System.out.println("1. Warrior");
        System.out.println("2. Warlock");
        int heroChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
    
        String heroType = (heroChoice == 1) ? "Warrior" : (heroChoice == 2) ? "Warlock" : null;
    
        if (heroType == null) {
            System.out.println("Invalid hero type.");
            return;
        }
    
        // Use HeroFactory to create the hero
        Hero hero = HeroFactory.createHero(heroType, heroID, heroName);
    
        // Use the factory to create the command
        ConcreteCommandFactory commandFactory = new ConcreteCommandFactory();
        Command addHeroCommand = commandFactory.create("AddHero", playerManager.getCurrentPlayer(), hero);
    
        // Execute the command
        commandExecutor.executeCommand(addHeroCommand);
    }
    
    
    private void deleteHero() { 
        if (playerManager.getCurrentPlayer() == null || playerManager.getCurrentPlayer().getHeroes().isEmpty()) {
            System.out.println("No heroes available to delete.");
            return;
        }
    
        System.out.print("Enter hero details to delete (format: heroID, heroName): ");
        String heroDetails = scanner.nextLine();
    
        // Split the input by comma and trim spaces
        String[] heroInfo = heroDetails.split(",");
        if (heroInfo.length < 1) {
            System.out.println("Invalid input format. Please use the format: heroID, heroName");
            return;
        }
    
        String heroID = heroInfo[0].trim();
    
        // Find the Hero object by ID
        Hero heroToDelete = null;
        for (Hero hero : playerManager.getCurrentPlayer().getHeroes()) {
            if (hero.getHeroID().equals(heroID)) {
                heroToDelete = hero;
                break;
            }
        }
    
        if (heroToDelete == null) {
            System.out.println("Hero with ID " + heroID + " not found.");
            return;
        }
    
        // Use the factory to create the RemoveHeroCommand
        ConcreteCommandFactory commandFactory = new ConcreteCommandFactory();
        Command removeHeroCommand = commandFactory.create("RemoveHero", playerManager.getCurrentPlayer(), heroToDelete);
    
        // Execute the command using CommandExecutor
        commandExecutor.executeCommand(removeHeroCommand);
    }
    
        

    private void changePlayerName() {
        System.out.print("Enter player ID to change name: ");
        String playerID = scanner.nextLine();
    
        // Find the player by ID
        Player player = playerManager.getPlayerById(playerID);
    
        if (player == null) {
            System.out.println("Player with ID " + playerID + " not found.");
            return;
        }
    
        // Player found, now ask for the new name
        System.out.print("Enter new player name for " + player.getPlayerName() + ": ");
        String newName = scanner.nextLine();
    
        // Use the factory to create the command
        ConcreteCommandFactory commandFactory = new ConcreteCommandFactory();
        Command changeNameCommand = commandFactory.create("ChangePlayerName", player, newName);
    
        // Execute the command using CommandExecutor
        commandExecutor.executeCommand(changeNameCommand);
    
        System.out.println("Player name changed to " + newName);
    }
    

    private void callHeroSkill() {
        if (playerManager.getCurrentPlayer() == null || playerManager.getCurrentPlayer().getHeroes().isEmpty()) {
            System.out.println("No heroes available to call skill.");
            return;
        }
    
        System.out.print("Enter hero ID to call skill: ");
        String heroID = scanner.nextLine();
    
        // Find the hero by ID
        Hero selectedHero = null;
        for (Hero hero : playerManager.getCurrentPlayer().getHeroes()) {
            if (hero.getHeroID().equals(heroID)) {
                selectedHero = hero;
                break;
            }
        }
    
        if (selectedHero == null) {
            System.out.println("Hero with ID " + heroID + " not found.");
            return;
        }
    
        // Use the factory to create the command
        ConcreteCommandFactory commandFactory = new ConcreteCommandFactory();
        Command callSkillCommand = commandFactory.create("CallHeroSkill", selectedHero);
    
        // Execute the command
        commandExecutor.executeCommand(callSkillCommand);
    
        System.out.println(selectedHero.getHeroName() + " called skill.");
    }
    
    private void showPlayerDetails() {
        if (playerManager.getCurrentPlayer() == null) {
            System.out.println("No current player to show details.");
            return;
        }
    
        // Use the factory to create the ShowPlayerDetailsCommand
        ConcreteCommandFactory commandFactory = new ConcreteCommandFactory();
        Command showPlayerDetailsCommand = commandFactory.create("ShowPlayerDetails", playerManager.getCurrentPlayer());
    
        // Execute the command using CommandExecutor
        commandExecutor.executeCommand(showPlayerDetailsCommand);
    }
    

    private void showAllPlayers() {
        if (playerManager.getPlayers().isEmpty()) {
            System.out.println("No players available.");
            return;
        }
    
        // Use the factory to create the DisplayAllPlayersCommand
        ConcreteCommandFactory commandFactory = new ConcreteCommandFactory();
        Command displayAllPlayersCommand = commandFactory.create("DisplayAllPlayers", playerManager);
    
        // Execute the command using CommandExecutor
        commandExecutor.executeCommand(displayAllPlayersCommand);
    }
    

    private void undo() {
        commandExecutor.undo();
        System.out.println("Undo completed.");
    }

    private void redo() {
        commandExecutor.redo();
        System.out.println("Redo completed.");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
