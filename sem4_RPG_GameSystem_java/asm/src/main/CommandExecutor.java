package main;

import java.util.Stack;

public class CommandExecutor {
    public Stack<Command> undoStack = new Stack<>();
    public Stack<Command> redoStack = new Stack<>();

    // Execute the command and store it in the undo stack
    public void executeCommand(Command command) {
        command.execute(); // Execute the command
        undoStack.push(command); // Push it onto the undo stack
        redoStack.clear(); // Clear the redo stack since a new command was executed
    }

    // Undo the last command
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("No commands to undo.");
            return;
        }

        Command lastCommand = undoStack.pop(); // Pop the last command from the undo stack
        lastCommand.undo(); // Undo the command's action
        redoStack.push(lastCommand); // Push it onto the redo stack
        System.out.println(lastCommand.getDescription());
    }

    // Redo the last undone command
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("No commands to redo.");
            return;
        }

        Command lastUndoneCommand = redoStack.pop(); // Pop the last command from the redo stack
        lastUndoneCommand.execute(); // Re-execute the command
        undoStack.push(lastUndoneCommand); // Push it back onto the undo stack
        System.out.println("Command (" + lastUndoneCommand.getDescription() + ") is redone.");
    }

    // Show the list of commands in undo and redo stacks
    public void showUndoRedoList() {
        System.out.println("---- Undo List ----");
        for (Command command : undoStack) {
            System.out.println(command.getDescription());
        }

        System.out.println("---- Redo List ----");
        for (Command command : redoStack) {
            System.out.println(command.getDescription());
        }
    }

}
