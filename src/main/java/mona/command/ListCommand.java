package mona.command;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.TaskList;
import mona.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showAllTasks(tasks.getTaskList());
    }
}