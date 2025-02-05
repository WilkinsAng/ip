import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final File directory = new File("./data");

    public static ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<>(100);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File data = new File(directory, "Mona.txt");
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
                    Task task = new Todo(description, isDone);
                    tasks.add(task);
                    break;
                case "D":
                    String doneBy = splitLine[3];
                    Task deadline = new Deadline(description, isDone, doneBy);
                    tasks.add(deadline);
                    break;
                case "E":
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
            System.out.println("Whoa! Looks like a glitch in the system! I got this message: *" + e.getMessage() + "*. Better check the files, Joker!");
        } catch (MonaException monaException) {
            System.out.println(monaException.getMessage());
            resetFile();
            return new ArrayList<>();
        }
        return tasks;
    }

    private static void resetFile() {
        try {
            File data = new File(directory, "Mona.txt");
            if (data.exists()) {
                data.delete(); // Delete the corrupted file
            }
            data.createNewFile(); // Create a fresh new file
        } catch (IOException e) {
            System.out.println("Gah! I tried resetting, but I got this error: \"" + e.getMessage() + "\"!");
        }
    }
}