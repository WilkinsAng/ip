package mona.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import mona.exception.MonaException;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.TaskList;
import mona.task.Todo;

public class Storage {

    private static final File DEFAULT_DIRECTORY = new File("./data");
    private static final File DEFAULT_DATA = new File(DEFAULT_DIRECTORY, "Mona.txt");

    private final File directory;
    private final File data;

    public Storage() {
        this.directory = DEFAULT_DIRECTORY;
        this.data = DEFAULT_DATA;
    }

    public Storage(String filepath) {
        this.directory = DEFAULT_DIRECTORY;
        String[] filepathSplit = filepath.split("/");
        this.data = new File(DEFAULT_DIRECTORY, filepathSplit[1]);
    }
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

    private void resetFile() {
        try {
            if (data.exists()) {
                data.delete(); // Delete the corrupted file
            }
            data.createNewFile(); // Create a fresh new file
        } catch (IOException e) {
            System.out.println("Gah! I tried resetting, but I got this error: \"" + e.getMessage() + "\"!");
        }
    }
}