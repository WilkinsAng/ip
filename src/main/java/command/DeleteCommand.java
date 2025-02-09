package command;

import exception.MonaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Creates a new DeleteCommand with specified task index.
     *
     * @param index The index of the task to delete (0-based).
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
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
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new MonaException.TaskNotFoundException(index + 1);
        }
        Task task = tasks.deleteTask(index);
        storage.saveData(tasks);
        ui.showDeleteTask(index, task, tasks.getSize());
    }
}