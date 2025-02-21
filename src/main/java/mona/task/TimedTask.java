package mona.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mona.exception.MonaException;

/**
 * Represents a task with a date and time component.
 * Provides utility methods for parsing and formatting date-time values.
 */
public abstract class TimedTask extends Task {
    protected static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy h:mma");
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy MMM d, h:mma");


    /**
     * Constructs a new TimedTask with the given description.
     *
     * @param description The description of the task.
     */
    public TimedTask(String description) {
        super(description);
    }


    /**
     * Constructs a new TimedTask with the given description and priority.
     *
     * @param description The description of the task.
     * @param priority The priority of the task.
     */
    public TimedTask(String description, TaskPriority priority) {
        super(description, priority);
    }

    /**
     * Constructs a new TimedTask with the given description, priority and completion status.
     * Used for loading a saved task.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is initially marked as done.
     * @param priority The priority of the task.
     */
    public TimedTask(String description, boolean isDone, TaskPriority priority) {
        super(description, isDone, priority);
    }

    /**
     * Parses a date-time string into a {@code LocalDateTime} object.
     *
     * @param date The string to parse.
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     * @throws MonaException.InvalidDateFormatException If the date format is invalid.
     */
    public static LocalDateTime parseDateTime(String date) throws MonaException {
        try {
            return LocalDateTime.parse(date, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MonaException.InvalidDateFormatException();
        }
    }

    /**
     * Formats a {@code LocalDateTime} object into a human-readable string.
     *
     * @param date The {@code LocalDateTime} object to format.
     * @return A formatted date-time string in the format {@code "yyyy MMM d, h:mma"} (e.g., "2024 Jul 15, 11:30PM"),
     *         or "No date provided" if {@code date} is {@code null}.
     */
    public static String formatDateTime(LocalDateTime date) {
        assert date != null;
        return date.format(OUTPUT_FORMATTER);
    }
}
