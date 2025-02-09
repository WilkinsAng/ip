package mona.task;

import java.time.LocalDateTime;

import mona.exception.MonaException;

public class Event extends TimedTask {
    protected LocalDateTime startFrom;
    protected LocalDateTime endBy;

    public Event(String description, String startFrom, String endBy) throws MonaException {
        super(description);
        this.startFrom = TimedTask.parseDateTime(startFrom);
        this.endBy = TimedTask.parseDateTime(endBy);
    }

    public Event(String description, boolean isDone, String startFrom, String endBy) throws MonaException {
        super(description, isDone);
        this.startFrom = TimedTask.parseDateTime(startFrom);
        this.endBy = TimedTask.parseDateTime(endBy);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: %s, to: %s)"
                .formatted(TimedTask.formatDateTime(startFrom), TimedTask.formatDateTime(endBy));
    }

    @Override
    public String toSaveFormat() {
        String status = isDone ? "1" : "0";
        String formattedStart = startFrom.format(TimedTask.INPUT_FORMATTER);
        String formattedEnd = endBy.format(TimedTask.INPUT_FORMATTER);
        return "E | %s | %s | %s - %s".formatted(status, description, formattedStart, formattedEnd);
    }
}
