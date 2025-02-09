package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command that adds a task to the task list.
 */
public class AddTaskCommand extends Command {
    private Task task;

    /**
     *Creates a new command that adds the given task to the task list.
     *
     * @param task the task to be added to the task list
     */
    public AddTaskCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     *Executes the command by adding the task to the task list,
     * saving the updated list to storage, and displaying feedback to the user.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addList(task);
        storage.saveData(tasks);
        ui.showAddTask(task, tasks.getSize());
    }
}
