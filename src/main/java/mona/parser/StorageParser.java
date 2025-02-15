package mona.parser;

import mona.exception.MonaException;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.Todo;

/**
 * Parses a line of the save file into a {@link Task} object.
 */
public class StorageParser {

    /**
     * Parses a line of the save file into a {@link Task} object.
     * @param line The line of the storage file to parse.
     * @return The parsed {@link Task} object.
     * @throws MonaException If the line is malformed.
     */
    public static Task parseToTask(String line) throws MonaException {
        String[] splitLine = line.split(" \\| ");
        String command = splitLine[0];
        boolean isDone = splitLine[1].equals("1");
        String description = splitLine[2];

        switch (command) {
        case "T":
            return handleTodo(splitLine, description, isDone);
        case "D":
            return handleDeadline(splitLine, description, isDone);
        case "E":
            return handleEvent(splitLine, description, isDone);
        default:
            throw new MonaException.CorruptedFileException();
        }
    }

    /**
     * Parses a line of the storage file into a {@link Todo} object.
     * @param splitLine Array of Strings representing [task type, completion status, description].
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @return The parsed {@link Todo} object.
     * @throws MonaException If the line is malformed.
     */
    public static Todo handleTodo(String[] splitLine, String description, boolean isDone)
            throws MonaException {
        if (splitLine.length != 3) {
            throw new MonaException.CorruptedFileException();
        }
        return new Todo(description, isDone);
    }

    /**
     * Parses a line of the storage file into a {@link Deadline} object.
     * @param splitLine Array of Strings representing [task type, completion status, description, deadline].
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @return The parsed {@link Deadline} object.
     * @throws MonaException If the line is malformed.
     */
    public static Deadline handleDeadline(String[] splitLine, String description, boolean isDone)
            throws MonaException {
        if (splitLine.length != 4) {
            throw new MonaException.CorruptedFileException();
        }
        String doneBy = splitLine[3];
        return new Deadline(description, isDone, doneBy);
    }

    /**
     * Parses a line of the storage file into a {@link Event} object.
     * @param splitLine Array of Strings representing [task type, completion status,
     *                  description, start date, end date].
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @return The parsed {@link Event} object.
     * @throws MonaException If the line is malformed.
     */
    public static Event handleEvent(String[] splitLine, String description, boolean isDone)
            throws MonaException {
        if (splitLine.length != 5) {
            throw new MonaException.CorruptedFileException();
        }
        String[] startEnd = splitLine[3].split(" - ");
        String startFrom = startEnd[0];
        String endBy = startEnd[1];
        return new Event(description, isDone, startFrom, endBy);
    }
}
