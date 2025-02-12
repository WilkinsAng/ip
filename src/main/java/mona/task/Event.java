package mona.task;

import java.time.LocalDateTime;

import mona.exception.MonaException;

/**
 * Represents an event task with a start and end date & time.
 */
public class Event extends TimedTask {
    protected LocalDateTime startFrom;
    protected LocalDateTime endBy;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startFrom The start time of the event.
     * @param endBy The end time of the event.
     * @throws MonaException If the start time or end time is invalid.
     */
    public Event(String description, String startFrom, String endBy) throws MonaException {
        super(description);
        this.startFrom = TimedTask.parseDateTime(startFrom);
        this.endBy = TimedTask.parseDateTime(endBy);
    }

    /**
     * Constructs an Event task with the given description, start time, end time, and completion status.
     * Used for loading a saved task.
     *
     * @param description The description of the event.
     * @param isDone The completion status of the event.
     * @param startFrom The start time of the event.
     * @param endBy The end time of the event.
     * @throws MonaException If the start time or end time is invalid.
     */
    public Event(String description, boolean isDone, String startFrom, String endBy) throws MonaException {
        super(description, isDone);
        this.startFrom = TimedTask.parseDateTime(startFrom);
        this.endBy = TimedTask.parseDateTime(endBy);
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return The formatted Event task string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: %s, to: %s)"
                .formatted(TimedTask.formatDateTime(startFrom), TimedTask.formatDateTime(endBy));
    }

    /**
     * Returns a formatted string for saving to a file.
     *
     * @return The save format string for file storage.
     */
    @Override
    public String toSaveFormat() {
        String status = isDone ? "1" : "0";
        String formattedStart = startFrom.format(TimedTask.INPUT_FORMATTER);
        String formattedEnd = endBy.format(TimedTask.INPUT_FORMATTER);
        return "E | %s | %s | %s - %s".formatted(status, description, formattedStart, formattedEnd);
    }
}
