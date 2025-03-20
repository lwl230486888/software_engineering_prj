package test;

import main.Command;
import main.CommandExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandExecutorTest {
    private CommandExecutor commandExecutor;
    private Command command;

    @BeforeEach
    void setUp() {
        commandExecutor = new CommandExecutor();
        command = new Command() {
            @Override
            public void execute() {
                System.out.println("Command executed");
            }

            @Override
            public void undo() {
                System.out.println("Command undone");
            }

            @Override
            public void redo() {
                System.out.println("Command redone");
            }

            @Override
            public String getDescription() {
                return "Test Command";
            }
        };
    }

    @Test
    void testExecuteCommand() {
        commandExecutor.executeCommand(command);

        assertEquals(1, commandExecutor.undoStack.size()); // 确保命令已被添加到 undo 栈中
    }

    @Test
    void testUndo() {
        commandExecutor.executeCommand(command);
        commandExecutor.undo();

        assertEquals(0, commandExecutor.undoStack.size()); // 确保 undo 后 undo 栈为空
        assertEquals(1, commandExecutor.redoStack.size()); // 确保命令被添加到 redo 栈中
    }

    @Test
    void testRedo() {
        commandExecutor.executeCommand(command);
        commandExecutor.undo();
        commandExecutor.redo();

        assertEquals(1, commandExecutor.undoStack.size()); // 确保 redo 后 undo 栈包含该命令
    }

    @Test
    void testShowUndoRedoList() {
        commandExecutor.executeCommand(command);
        commandExecutor.undo();

        commandExecutor.showUndoRedoList();  // 检查输出内容
    }
}
