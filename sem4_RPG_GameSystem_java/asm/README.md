Fantastic World (FW) RPG Game System

This project is a Java-based system designed for the RPG game Fantastic World (FW). The system allows players to manage their heroes (e.g., Warrior, Warlock) and provides functionalities such as creating players, adding/removing heroes, calling hero skills, and managing player information. The system is designed to be extensible, adhering to the Open Closed Principle, and incorporates several design patterns to ensure flexibility and maintainability.

Features
Core Functionalities
Player Management:

Create a new player.

Set the current player by ID.

Change the name of the current player.

Display details of the current player.

Display a list of all players.

Hero Management:

Add a hero (Warrior or Warlock) to the current player.

Remove a hero from the current player.

Call a hero's skill by hero ID.

Undo/Redo Operations:

Undo the last command.

Redo the last undone command.

Display the undo/redo list.

Extensibility:

Designed to easily support new hero types (e.g., Healer, Ranger) in the future.

Design Patterns Used
1. Command Pattern
Used to encapsulate actions such as:

Creating a player.

Setting the current player.

Adding/removing a hero.

Calling a hero's skill.

Changing a player's name.

Displaying player details.

Undo/redo operations.

2. Factory Pattern
Used to create:

Different types of heroes (e.g., Warrior, Warlock).

Different types of commands (e.g., CreatePlayerCommand, AddHeroCommand).

3. Memento Pattern
Used to support undo/redo functionality for:

Calling a hero's skill.

Changing a player's name.

How to Use
Compilation
Compile the program using the following command:

javac *.java
Execution
Run the program:

java Main
Functionality
The system provides a menu-driven interface with the following options:

Create Player: Create a new player.

Add Hero: Add a hero (Warrior or Warlock) to the current player.

Remove Hero: Remove a hero from the current player.

Set Current Player: Set the current player by ID.

Call Hero Skill: Call a hero's skill by hero ID.

Show Current Player: Display details of the current player.

Change Player Name: Change the name of the current player.

Show All Players: Display a list of all players.

Undo: Undo the last command.

Redo: Redo the last undone command.

Show Undo/Redo List: Display the undo/redo list.

Exit: Exit the system.