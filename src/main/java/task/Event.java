package task;

import java.time.LocalDateTime;

import exception.MonaException;

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
        return "E | %s | %s | %s - %s".formatted(isDone ? "1" : "0", super.description,
                startFrom.format(TimedTask.INPUT_FORMATTER), endBy.format(TimedTask.INPUT_FORMATTER));
    }
}
