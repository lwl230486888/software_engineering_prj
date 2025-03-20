package main;

public class ConcreteCommandFactory implements AbstractFactory<Command> {

    @Override
    public Command create(String type, Object... params) {
        switch (type) {
            case "CreatePlayer":
                // Check parameters length and types before casting
                if (params.length == 3 && params[0] instanceof PlayerManager && params[1] instanceof String && params[2] instanceof String) {
                    return new CreatePlayerCommand((PlayerManager) params[0], (String) params[1], (String) params[2]);
                }
                break;

            case "SetCurrentPlayer":
                if (params.length == 2 && params[0] instanceof PlayerManager && params[1] instanceof String) {
                    return new SetCurrentPlayerCommand((PlayerManager) params[0], (String) params[1]);
                }
                break;

            case "AddHero":
                if (params.length == 2 && params[0] instanceof Player && params[1] instanceof Hero) {
                    return new AddHeroCommand((Player) params[0], (Hero) params[1]);
                }
                break;

            case "CallHeroSkill":
                if (params.length == 1 && params[0] instanceof Hero) {
                    return new CallHeroSkillCommand((Hero) params[0]);
                }
                break;

            case "ChangePlayerName":
                if (params.length == 2 && params[0] instanceof Player && params[1] instanceof String) {
                    return new ChangePlayerNameCommand((Player) params[0], (String) params[1]);
                }
                break;

            case "RemoveHero":
                if (params.length == 2 && params[0] instanceof Player && params[1] instanceof Hero) {
                    return new RemoveHeroCommand((Player) params[0], (Hero) params[1]);
                }
                break;

            case "ShowPlayerDetails":
                if (params.length == 1 && params[0] instanceof Player) {
                    return new ShowPlayerDetailsCommand((Player) params[0]);
                }
                break;

            case "DisplayAllPlayers":
                if (params.length == 1 && params[0] instanceof PlayerManager) {
                    return new DisplayAllPlayersCommand((PlayerManager) params[0]);
                }
                break;

            // Additional command cases as needed
            default:
                throw new IllegalArgumentException("Unknown command type: " + type);
        }

        // If parameters do not match expected values
        throw new IllegalArgumentException("Invalid parameters for command type: " + type);
    }
}

