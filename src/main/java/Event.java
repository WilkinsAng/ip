public class Event extends Task {
    protected String startFrom;
    protected String endBy;

    public Event(String description, String startFrom, String endBy) {
        super(description);
        this.startFrom = startFrom.strip();
        this.endBy = endBy.strip();
    }

    public Event(String description, boolean isDone, String startFrom, String endBy) {
        super(description, isDone);
        this.startFrom = startFrom.strip();
        this.endBy = endBy.strip();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: %s, to: %s)".formatted(startFrom, endBy);
    }

    public String toSaveFormat() {
        return "E | %s | %s | %s - %s".formatted(isDone ? "1" : "0", description, startFrom, endBy);
    }
}
