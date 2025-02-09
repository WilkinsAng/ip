package mona.command;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.Task;
import mona.task.TaskList;
import mona.ui.Ui;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to mark as not done (0-based).
     */
    public UnmarkCommand(int index) {
        super();
        this.taskIndex = index;
    }

    /**
     * Executes the command by marking the specified task as not done,
     * saving the updated list to storage, and displaying feedback to the user.
     *
     * @param tasks   The task list containing the task to unmark.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws MonaException.TaskNotFoundException If the specified task index is out of bounds.
     * @throws MonaException.TaskAlreadyUndoneException If the task is already marked as not done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new MonaException.TaskNotFoundException(taskIndex + 1);
        }

        Task task = tasks.getTask(taskIndex);

        if (task.isDone()){
            task.markAsUndone();
        } else {
            throw new MonaException.TaskAlreadyUndoneException(task);
        }

        storage.saveData(tasks);
        ui.showUnmarkMessage(task);
    }
}
