package mona.command;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.Task;
import mona.task.TaskList;
import mona.ui.Ui;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int index) {
        super();
        this.taskIndex = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
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
        ui.showMarkMessage(task);
    }
}
