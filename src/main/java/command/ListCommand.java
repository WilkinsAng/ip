package command;

import exception.MonaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying all tasks in the task list.
     *
     * @param tasks   The task list to display.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler, which is not used in this command.
     * @throws MonaException If an error occurs while retrieving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        ui.showAllTasks(tasks.getTasks());
    }
}