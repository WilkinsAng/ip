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

    /**
     * Runs the chatbot, continuously processing user commands until exit.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MonaException e) {
                System.out.println(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.nextLine();
                }
            }
        }
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Mona("data/Mona.txt").run();
    }
}
