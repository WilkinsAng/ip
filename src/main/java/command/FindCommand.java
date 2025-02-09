package command;

import java.lang.reflect.Array;
import java.util.ArrayList;

import exception.MonaException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

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