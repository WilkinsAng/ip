package mona.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import mona.exception.MonaException;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.TaskList;
import mona.task.Todo;

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
     * 
     * @param filepath The filepath to use for storing tasks.
     */
    public Storage(String filepath) {
        this.directory = DEFAULT_DIRECTORY;
        String[] filepathSplit = filepath.split("/");

        // If the filepath is empty, use the default filename
        String filename = filepathSplit.length > 1 ? filepathSplit[1] : "Mona.txt";
        this.data = new File(DEFAULT_DIRECTORY, filename);
    }

    /**
     * Loads tasks from file.
     * 
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
            Scanner contents = new Scanner(data);

            while (contents.hasNextLine()) {
                String line = contents.nextLine();
                String[] splitLine = line.split(" \\| ");
                String command = splitLine[0];
                boolean isDone = splitLine[1].equals("1");
                String description = splitLine[2];

                switch (command) {
                case "T":
                    if (splitLine.length != 3) {
                        throw new MonaException.CorruptedFileException();
                    }
                    Task task = new Todo(description, isDone);
                    tasks.add(task);
                    break;
                case "D":
                    if (splitLine.length != 4) {
                        throw new MonaException.CorruptedFileException();
                    }
                    String doneBy = splitLine[3];
                    Task deadline = new Deadline(description, isDone, doneBy);
                    tasks.add(deadline);
                    break;
                case "E":
                    if (splitLine.length != 5) {
                        throw new MonaException.CorruptedFileException();
                    }
                    String[] startEnd = splitLine[3].split(" - ");
                    String startFrom = startEnd[0];
                    String endBy = startEnd[1];
                    Task event = new Event(description, isDone, startFrom, endBy);
                    tasks.add(event);
                    break;
                default:
                    throw new MonaException.CorruptedFileException();
                }
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
     * 
     * @param tasks The task list to be saved.
     */
    public void saveData(TaskList tasks) {
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