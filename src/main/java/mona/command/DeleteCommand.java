package mona.command;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.Task;
import mona.task.TaskList;
import mona.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private int taskIndex;

    /**
     * Creates a new DeleteCommand with specified task index.
     *
     * @param index The index of the task to delete (0-based).
     */
    public DeleteCommand(int index) {
        super();
        this.taskIndex = index;
    }

    /**
     * Executes the command by deleting the task at the given index from the task list,
     * saving the updated list to storage, and displaying feedback to the user.
     *
     * @param tasks The task list to delete the task from.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws MonaException If the index is out of bounds or the task is not found.
     */

    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new MonaException.TaskNotFoundException(taskIndex + 1);
        }

        Task task = tasks.deleteTask(taskIndex);

        storage.saveData(tasks);

        setReply(ui.showDeleteTask(taskIndex, task, tasks.getSize()));
    }
}
