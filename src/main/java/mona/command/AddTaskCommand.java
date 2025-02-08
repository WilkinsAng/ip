package mona.command;

import mona.storage.Storage;
import mona.task.Task;
import mona.task.TaskList;
import mona.ui.Ui;

public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addList(task);
        storage.saveData(tasks);
        ui.showAddTask(task, tasks.getSize());
    }
}
