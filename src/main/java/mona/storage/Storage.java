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

/**
 * Handles loading and saving tasks to a local file for persistence.
 */
public class Storage {

    private static final File DEFAULT_DIRECTORY = new File("./data");
    private static final File DEFAULT_DATA = new File(DEFAULT_DIRECTORY, "Mona.txt");

    private final File directory;
    private final File data;

    /**
     * Constructor for Storage object using the default file path.
     */
    public Storage() {
        this.directory = DEFAULT_DIRECTORY;
        this.data = DEFAULT_DATA;
    }

    /**
     * Constructor for Storage object with a custom file path.
     * @param filepath The filepath to use for storing tasks.
     */
    public Storage(String filepath) {
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
        ArrayList<Task> tasks = new ArrayList<>(100);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!data.exists()) {
                data.createNewFile();
            }

            assert data.exists() : "Storage file does not exist";
            assert directory.exists() : "Storage directory does not exist";

            Scanner contents = new Scanner(data);

            while (contents.hasNextLine()) {
                String line = contents.nextLine();
                Task taskToAdd = StorageParser.parseToTask(line);
                tasks.add(taskToAdd);
            }
            contents.close();
        } catch (IOException e) {
            System.out.println("Whoa! Looks like a glitch in the system! I got this message: *"
                    + e.getMessage() + "*. Better check the files, Joker!");
        } catch (MonaException monaException) {
            System.out.println(monaException.getMessage());
            resetFile();
            return new ArrayList<>();
        }
        return tasks;
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
            System.out.println("Whoa! Looks like a something went wrong while saving, Joker! I got this message: *"
                    + e.getMessage());
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
}
