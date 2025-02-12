package mona;

import mona.command.Command;
import mona.exception.MonaException;
import mona.parser.Parser;
import mona.storage.Storage;
import mona.task.TaskList;
import mona.ui.Ui;

/**
 * The main class that runs the Mona chatbot.
 * Handles user interaction, command execution, and data storage.
 */
public class Mona {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the Mona chatbot with a given file path for task storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Mona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadData());
    }


    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return c.getReply();
        } catch (MonaException e) {
            return ui.showErrorMessage(e);
        }
    }
}
