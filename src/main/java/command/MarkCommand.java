package command;

import exception.MonaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to mark as done (0-based).
     */
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command by marking the specified task as done,
     * saving the updated list to storage, and displaying feedback to the user.
     *
     * @param tasks   The task list containing the task to mark.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws MonaException.TaskNotFoundException If the specified task index is out of bounds.
     * @throws MonaException.TaskAlreadyDoneException If the task is already marked as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new MonaException.TaskNotFoundException(index + 1);
        }

        Task task = tasks.getTask(index);

        if (!task.getIsDone()) {
            task.markAsDone();
        } else {
            throw new MonaException.TaskAlreadyDoneException(task);
        }
        storage.saveData(tasks);
        ui.showMarkMessage(task);
    }
}