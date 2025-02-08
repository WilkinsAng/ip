import command.Command;
import exception.MonaException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;


public class Mona {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Mona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadData());
    }

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

    public static void main(String[] args) {
        new Mona("data/Mona.txt").run();
    }
}