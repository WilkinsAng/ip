package mona.command;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.Task;
import mona.task.TaskList;
import mona.ui.Ui;

public class DeleteCommand extends Command {

    private int taskIndex;

    public DeleteCommand(int index) {
        super();
        this.taskIndex = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new MonaException.TaskNotFoundException(taskIndex + 1);
        }

        Task task = tasks.deleteTask(taskIndex);

        storage.saveData(tasks);

        ui.showDeleteTask(taskIndex, task, tasks.getSize());
    }
}
