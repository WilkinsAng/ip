import java.time.LocalDateTime;

public class Event extends TimedTask {
    protected LocalDateTime startFrom;
    protected LocalDateTime endBy;

    public Event(String description, String startFrom, String endBy) throws MonaException {
        super(description);
        this.startFrom = parseDateTime(startFrom);
        this.endBy = parseDateTime(endBy);
    }

    public Event(String description, boolean isDone, String startFrom, String endBy) throws MonaException {
        super(description, isDone);
        this.startFrom = parseDateTime(startFrom);
        this.endBy = parseDateTime(endBy);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: %s, to: %s)"
                .formatted(formatDateTime(startFrom), formatDateTime(endBy));
    }

    @Override
    public String toSaveFormat() {
        return "E | %s | %s | %s - %s".formatted(isDone ? "1" : "0", super.description,
                startFrom.format(INPUT_FORMATTER), endBy.format(INPUT_FORMATTER));
    }
}
