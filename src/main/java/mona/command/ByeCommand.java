package mona.command;

import mona.storage.Storage;
import mona.task.TaskList;
import mona.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.markAsExit();
        ui.bye();
    }
}
