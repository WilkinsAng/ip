package mona.command;

import java.util.ArrayList;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.Task;
import mona.task.TaskList;
import mona.ui.Ui;

public class FindCommand extends Command {

    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        ArrayList<Task> results = tasks.findResults(query);
        ui.showFindResults(results, query);
    }
}