package mona.parser;

import mona.exception.MonaException;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.TaskPriority;
import mona.task.Todo;

/**
 * Parses a line of the save file into a {@link Task} object.
 */
public class StorageParser {

    /**
     * Parses a line of the save file into a {@link Task} object.
     *
     * @param line The line of the storage file to parse.
     * @return The parsed {@link Task} object.
     * @throws MonaException If the line is malformed.
     */
    public static Task parseToTask(String line) throws MonaException {
        String[] splitLine = line.split(" \\| ");
        int priorityLevel = Integer.parseInt(splitLine[0]);
        TaskPriority priority = TaskPriority.fromPriorityLevel(priorityLevel);
        String command = splitLine[1];
        boolean isDone = splitLine[2].equals("1");
        String description = splitLine[3];

        switch (command) {
        case "T":
            return handleTodo(priority, splitLine, description, isDone);
        case "D":
            return handleDeadline(priority, splitLine, description, isDone);
        case "E":
            return handleEvent(priority, splitLine, description, isDone);
        default:
            throw new MonaException.CorruptedFileException();
        }
    }

    /**
     * Parses a line of the storage file into a {@link Todo} object.
     *
     * @param priority The priority of the task.
     * @param splitLine Array of Strings representing [priority, task type, completion status, description].
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @return The parsed {@link Todo} object.
     * @throws MonaException If the line is malformed.
     */
    public static Todo handleTodo(TaskPriority priority, String[] splitLine, String description, boolean isDone)
            throws MonaException {
        if (splitLine.length != 4) {
            throw new MonaException.CorruptedFileException();
        }
        return new Todo(description, isDone, priority);
    }

    /**
     * Parses a line of the storage file into a {@link Deadline} object.
     *
     * @param priority The priority of the task.
     * @param splitLine Array of Strings representing [priority, task type, completion status, description, deadline].
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @return The parsed {@link Deadline} object.
     * @throws MonaException If the line is malformed.
     */
    public static Deadline handleDeadline(TaskPriority priority, String[] splitLine, String description, boolean isDone)
            throws MonaException {
        if (splitLine.length != 5) {
            throw new MonaException.CorruptedFileException();
        }
        String doneBy = splitLine[4];
        return new Deadline(description, isDone, doneBy, priority);
    }

    /**
     * Parses a line of the storage file into an {@link Event} object.
     *
     * @param priority The priority of the task.
     * @param splitLine Array of Strings representing [priority, task type, completion status,
     *                  description, start date - end date].
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @return The parsed {@link Event} object.
     * @throws MonaException If the line is malformed.
     */
    public static Event handleEvent(TaskPriority priority, String[] splitLine, String description, boolean isDone)
            throws MonaException {
        if (splitLine.length != 6) {
            throw new MonaException.CorruptedFileException();
        }
        String[] startEnd = splitLine[4].split(" - ");
        String startFrom = startEnd[0];
        String endBy = startEnd[1];
        return new Event(description, isDone, startFrom, endBy, priority);
    }
}
