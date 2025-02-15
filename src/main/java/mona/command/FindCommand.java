package mona.command;

import java.util.ArrayList;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.Task;
import mona.task.TaskList;
import mona.ui.Ui;

/**
 * Represents a command to search for tasks that match the given search query.
 */
public class FindCommand extends Command {

    private final String[] queries;

    /**
     * Constructor.
     *
     * @param queries The search query to match.
     */
    public FindCommand(String ... queries) {
        this.queries = queries;
    }

    /**
     * Executes the command by displaying the search results.
     *
     * @param tasks   The task list to search.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler, which is not used in this command.
     * @throws MonaException If an error occurs while searching.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";

        ArrayList<Task> results = tasks.findResults(queries);
        setReply(ui.showFindResults(results, queries));
    }
}
