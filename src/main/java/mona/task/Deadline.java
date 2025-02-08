package mona.task;

import java.time.LocalDateTime;

import mona.exception.MonaException;

public class Deadline extends TimedTask {
    protected LocalDateTime doneBy;

    public Deadline(String description, String doneBy) throws MonaException {
        super(description);
        this.doneBy = TimedTask.parseDateTime(doneBy);
    }

    public Deadline(String description, boolean isDone, String doneBy) throws MonaException {
        super(description, isDone);
        this.doneBy = TimedTask.parseDateTime(doneBy);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: %s)".formatted(TimedTask.formatDateTime(doneBy));
    }

    @Override
    public String toSaveFormat() {
        return "D | %s | %s | %s".formatted(isDone ? "1" : "0", description, doneBy.format(TimedTask.INPUT_FORMATTER));
    }
}