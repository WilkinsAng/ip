package mona.command;

import mona.storage.Storage;
import mona.task.TaskList;
import mona.ui.Ui;

/**
 * Represents a command that ends the program.
 */
public class ByeCommand extends Command {

    private String reply;

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
        assert ui != null : "Ui should not be null";
        setReply(ui.bye());
    }
}
