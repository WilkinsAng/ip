package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command that ends the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command by setting the exit flag to true and displaying
     * a goodbye message to the user.
     *
     * @param tasks   The task list, which is not used in this command.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler, which is not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit();
        ui.bye();
    }
}
