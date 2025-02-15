package mona.command;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.Task;
import mona.task.TaskList;
import mona.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to mark as done (0-based).
     */
    public MarkCommand(int index) {
        super();
        this.taskIndex = index;
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
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new MonaException.TaskNotFoundException(taskIndex + 1);
        }

        Task task = tasks.getTask(taskIndex);

        if (!task.isDone()) {
            task.markAsDone();
        } else {
            throw new MonaException.TaskAlreadyDoneException(task);
        }
        storage.saveData(tasks);
        setReply(ui.showMarkMessage(task));
    }
}
