package mona.command;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.TaskList;
import mona.ui.Ui;

public abstract class Command {

    private boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException;

    public boolean isExit() {
        return this.isExit;
    }

    public void markAsExit() {
        isExit = true;
    }
}
