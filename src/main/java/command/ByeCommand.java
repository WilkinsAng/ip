package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit();
        ui.bye();
    }
}
