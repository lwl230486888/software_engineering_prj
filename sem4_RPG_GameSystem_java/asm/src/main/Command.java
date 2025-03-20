package main;

public interface Command {
    void execute();
    void undo();
    void redo(); // Add redo method to interface
    String getDescription();
}
