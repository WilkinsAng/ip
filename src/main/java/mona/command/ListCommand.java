package mona.command;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.TaskList;
import mona.ui.Ui;

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
        assert ui != null : "Ui should not be null";
        setReply(ui.showAllTasks(tasks.getTaskList()));
    }
}
