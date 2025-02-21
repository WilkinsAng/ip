package mona.command;

import mona.storage.Storage;
import mona.task.Task;
import mona.task.TaskList;
import mona.task.TaskPriority;
import mona.ui.Ui;

/**
 * Represents a command to prioritize a task in the task list.
 */
public class PrioritizeCommand extends Command {
    private int taskIndex;
    private TaskPriority priority;

    /**
     * Creates a new prioritize command.
     *
     * @param index    The index of the task to prioritize.
     * @param priority The priority level to set for the task.
     */
    public PrioritizeCommand(int index, TaskPriority priority) {
        this.taskIndex = index;
        this.priority = priority;
    }

    /**
     * Executes the command by setting the priority of the task at the specified index,
     * sorting the task list to reflect the new priority order, saving the updated list
     * to storage, and displaying feedback to the user.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        Task task = tasks.getTask(taskIndex);
        task.setPriority(priority);
        tasks.sortTasks();
        storage.saveData(tasks);
        setReply(ui.showPriorityChange(task, priority));
    }
}
