import java.time.LocalDateTime;

public class Deadline extends TimedTask {
    protected LocalDateTime doneBy;

    public Deadline(String description, String doneBy) throws MonaException {
        super(description);
        this.doneBy = parseDateTime(doneBy);
    }

    public Deadline(String description, boolean isDone, String doneBy) throws MonaException {
        super(description, isDone);
        this.doneBy = parseDateTime(doneBy);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: %s)".formatted(formatDateTime(doneBy));
    }

    @Override
    public String toSaveFormat() {
        return "D | %s | %s | %s".formatted(isDone ? "1" : "0", description, doneBy.format(INPUT_FORMATTER));
    }
}