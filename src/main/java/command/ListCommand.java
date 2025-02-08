package command;

import exception.MonaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        ui.showAllTasks(tasks.getTasks());
    }
}
