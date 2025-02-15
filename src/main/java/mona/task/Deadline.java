package mona.task;

import java.time.LocalDateTime;

import mona.exception.MonaException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends TimedTask {
    protected LocalDateTime doneBy;

    /**
     * Constructs a Deadline Task with the specified description and due date.
     *
     * @param description The task description.
     * @param doneBy      The due date in string format.
     * @throws MonaException If the date format is invalid.
     */
    public Deadline(String description, String doneBy) throws MonaException {
        super(description);
        this.doneBy = TimedTask.parseDateTime(doneBy);
    }

    /**
     * Constructs a Deadline with the specified description, completion status, and due date.
     * Used for loading a saved task.
     *
     * @param description The task description.
     * @param isDone      The completion status of the task.
     * @param doneBy      The due date in string format.
     * @throws MonaException If the date format is invalid.
     */
    public Deadline(String description, boolean isDone, String doneBy) throws MonaException {
        super(description, isDone);
        this.doneBy = TimedTask.parseDateTime(doneBy);
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        assert doneBy != null : "Done by date should not be null";
        return "[D]" + super.toString() + " (by: %s)".formatted(TimedTask.formatDateTime(doneBy));
    }

    /**
     * Returns the formatted string for saving to a file.
     *
     * @return The save format string of the deadline task.
     */
    @Override
    public String toSaveFormat() {
        assert doneBy != null : "Done by date should not be null";
        String status = isDone ? "1" : "0";
        String formattedDate = doneBy.format(TimedTask.INPUT_FORMATTER);
        return "D | %s | %s | %s".formatted(status, description, formattedDate);
    }
}
