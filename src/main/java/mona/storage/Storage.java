package mona.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import mona.exception.MonaException;
import mona.parser.StorageParser;
import mona.task.Task;
import mona.task.TaskList;
import mona.ui.Ui;

/**
 * Handles loading and saving tasks to a local file for persistence.
 */
public class Storage {

    private static final File DEFAULT_DIRECTORY = new File("./data");
    private static final File DEFAULT_DATA = new File(DEFAULT_DIRECTORY, "Mona.txt");

    private final File directory;
    private final File data;
    private final Ui ui;

    /**
     * Constructor for Storage object using the default file path.
     */
    public Storage() {
        ui = new Ui();
        this.directory = DEFAULT_DIRECTORY;
        this.data = DEFAULT_DATA;
    }

    /**
     * Constructor for Storage object with a custom file path.
     * @param ui The user interface to use for displaying messages.
     * @param filepath The filepath to use for storing tasks.
     */
    public Storage(Ui ui, String filepath) {
        this.ui = ui;
        assert filepath != null && !filepath.isBlank() : "Storage filepath cannot be null or empty";

        this.directory = DEFAULT_DIRECTORY;
        String[] filepathSplit = filepath.split("/");

        // If the filepath is empty, use the default filename
        String filename = filepathSplit.length > 1 ? filepathSplit[1] : "Mona.txt";
        this.data = new File(DEFAULT_DIRECTORY, filename);
    }

    /**
     * Loads tasks from file.
     * @return A list of tasks retrieved from storage.
     */
    public ArrayList<Task> loadData() {
        try {
            ensureStorageExist();

            assert data.exists() : "Storage file does not exist";
            assert directory.exists() : "Storage directory does not exist";

            return loadTasksFromFile();
        } catch (IOException e) {
            ui.showLoadingError(e);
            return new ArrayList<>();
        } catch (MonaException monaException) {
            ui.showErrorMessage(monaException);
            resetFile();
            return new ArrayList<>();
        }
    }

    /**
     * Saves the current list of tasks to the file.
     * @param tasks The task list to be saved.
     */
    public void saveData(TaskList tasks) {
        assert tasks != null : "TaskList should not be null";

        try {
            FileWriter writer = new FileWriter(data);

            for (Task task : tasks.getTaskList()) {
                writer.write(task.toSaveFormat() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            ui.showSavingError(e);
        }

    }

    /**
     * Resets the file to a blank state in case of corruption.
     */
    private void resetFile() {
        try {
            Files.deleteIfExists(data.toPath());
            data.createNewFile(); // Create a fresh new file
        } catch (IOException e) {
            System.out.println("Gah! I tried resetting, but I got this error: \"" + e.getMessage() + "\"!");
        }
    }

    /**
     * Checks if the storage directory exists and creates it if not.
     * Ensures the file exists and creates it if it does not.
     * @throws IOException if unable to create the file or directory
     */
    private void ensureStorageExist() throws IOException {
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!data.exists()) {
            data.createNewFile();
        }
    }

    /**
     * Loads the tasks stored in the file.
     * @return the list of tasks loaded from the file
     * @throws IOException if unable to read the file
     * @throws MonaException if the file is corrupted
     */
    private ArrayList<Task> loadTasksFromFile() throws IOException, MonaException {
        ArrayList<Task> tasks = new ArrayList<>(100);
        Scanner contents = new Scanner(data);

        while (contents.hasNextLine()) {
            String line = contents.nextLine();
            Task taskToAdd = StorageParser.parseToTask(line);
            tasks.add(taskToAdd);
        }
        contents.close();
        return tasks;
    }
}
